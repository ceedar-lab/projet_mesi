package com.mesi.display;

import com.mesi.animation.*;
import com.mesi.equipement.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Panel extends JPanel {

    private final Integer SIZE = 64;

    private Integer offsetX = 0, offsetY = 0;
    private Integer stride = 4, strideX = 0, strideY = 0;
    private ArrayList direction = new ArrayList() {{ add(40); }};
    private Integer originX = 5, originY = 5;
    private boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown;
    private boolean isHiting = false;
    private Integer animX = 0, animY = 2;
    private Integer clock = 0, fps = 0;

    Animation test = new WhiteCharacterAnimation(Hair.BLOND, Head.ROBE_HOOD, Torso.TSHIRT, Hands.NONE, Legs.SKIRT, Feet.LEATHER_BOOTS, RightHand.DAGGER, LeftHand.NONE);
    /*Animation test = new BrownCharacterAnimation(Hair.BROWN, Head.LEATHER_HAT, Torso.LEATHER_ARMOR, Hands.NONE, Legs.LEATHER_PANTS, Feet.LEATHER_BOOTS, RightHand.SPEAR, LeftHand.SHIELD);*/
    /*Animation test = new WhiteCharacterAnimation(Hair.BLACK, Head.METAL_HELMET, Torso.METAL_ARMOR, Hands.METAL_GLOVES, Legs.METAL_PANTS, Feet.METAL_BOOTS, RightHand.SWORD, LeftHand.SHIELD);*/
    BufferedImage[] anim  = test.stand((Integer)direction.get(0));

    //Perso perso = new Perso();

    public Panel() throws IOException {
        setBackground(new Color(0,0,0,0));
        setBounds(0, 0, 1280, 768);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    repaint();
                    try {
                        Thread.sleep(1000/60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        offsetX+=strideX;
        offsetY+=strideY;

        // Carreaux
        for (int ax = 0; ax < 40; ax++) {
            for (int ay = 0; ay < 24; ay++) {
                g.setColor(Color.BLACK);
                g.drawRect(ax*SIZE/2, ay*SIZE/2, SIZE/2, SIZE/2);
            }
        }

        g.setColor(Color.GREEN.darker().darker());
        g.fillRect(2*SIZE, 6*SIZE + SIZE/2, SIZE/2,SIZE/2);

        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(SIZE*originX + SIZE/4 + offsetX, SIZE*(originX+1) - SIZE/5 + offsetY, 32, 16);

        if (isHiting) {
            System.out.println("hit / clock : " +clock+ " / fps : " +fps);

            try {
                anim = test.hit((Integer)direction.get(0), test.getRightHand());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (test.getRightHand() == RightHand.DAGGER || test.getRightHand() == RightHand.SWORD) {
                g.drawImage(anim[fps], SIZE * originX + offsetX, SIZE * originY + offsetY, this);

                clock++;

                if (clock == 4) {
                    fps++;
                    clock = 0;
                }
                if (fps == 6) {
                    fps = 0;
                    isHiting = false;
                }
            } else {
                g.drawImage(anim[fps], SIZE * originX + offsetX, SIZE * originY + offsetY, this);

                clock++;

                if (clock == 4) {
                    fps++;
                    clock = 0;
                }
                if (fps == 8) {
                    fps = 0;
                    isHiting = false;
                }
            }

        } else if (isMovingRight || isMovingLeft || isMovingUp || isMovingDown) {
            try {
                anim = test.walkCycle((Integer)direction.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (direction.get(0).equals(37) || direction.get(0).equals(39)) {
                g.drawImage(anim[animX], SIZE * originX + offsetX, SIZE * originY + offsetY, this);
            } else {
                g.drawImage(anim[animY], SIZE * originX + offsetX, SIZE * originY + offsetY, this);
            }
        } else {
            g.drawImage(anim[0], SIZE * originX + offsetX, SIZE * originY + offsetY, this);
        }

        /*clock++;

        if (clock == 4) {
            animX++;
            animY++;
            clock = 0;
        }
        if (animX == 9) animX = 0;
        if (animY == 9) animY = 2;*/

        if (offsetX%16 == 0) animX+=1;
        if (offsetY%16 == 0) animY+=1;
        if (animX == 9) animX = 0;
        if (animY == 9) animY = 2;
    }

    public void setDirection(Integer keyCode) {
        if (!isMovingLeft && !isMovingRight && !isMovingUp && !isMovingDown) {
            direction.clear();
            direction.add(keyCode);
        } else {
            direction.add(0, keyCode);
        }
    }

    public void removeDirection(Object keyCode) {
        if (direction.size() > 1)
            direction.remove(keyCode);
    }

    public void onKeyPressed(int keyCode) throws IOException {
        System.out.println("panel key");
        if (keyCode == 37 && !isMovingLeft) {
            strideX-=stride;
            setDirection(keyCode);
            isMovingLeft = true;
        }
        if (keyCode == 39 && !isMovingRight) {
            strideX+=stride;
            setDirection(keyCode);
            isMovingRight = true;
        }
        if (keyCode == 38 && !isMovingUp) {
            strideY-=stride;
            setDirection(keyCode);
            isMovingUp = true;
        }
        if (keyCode == 40 && !isMovingDown) {
            strideY+=stride;
            setDirection(keyCode);
            isMovingDown = true;
        }
        if (keyCode == 32) {
            isHiting = true;
        }
    }

    public void onKeyReleased(int keyCode) throws IOException {
        if (keyCode == 37) {
            strideX+=stride;
            removeDirection(keyCode);
            isMovingLeft = false;
        }
        if (keyCode == 39) {
            strideX-=stride;
            removeDirection(keyCode);
            isMovingRight = false;
        }
        if (keyCode == 38) {
            strideY+=stride;
            removeDirection(keyCode);
            isMovingUp = false;
        }
        if (keyCode == 40) {
            strideY-=stride;
            removeDirection(keyCode);
            isMovingDown = false;
        }
        if (!isMovingLeft && !isMovingRight && !isMovingUp && !isMovingDown) anim = test.stand((Integer)direction.get(0));
    }
}
