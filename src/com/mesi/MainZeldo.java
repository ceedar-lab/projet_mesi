package com.mesi;

import com.mesi.display.GameTitle;
import com.mesi.display.StartMenu;
import com.mesi.animation.Animation;
import com.mesi.animation.WhiteCharacterAnimation;
import com.mesi.equipement.*;
import com.mesi.params.KeyMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainZeldo extends JPanel {

    /**********  Attributes  **********/

    private Integer offsetX = 0, offsetY = 0;
    private static Integer stride = 4, strideX = 0, strideY = 0;
    private static ArrayList direction = new ArrayList() {{ add(40); }};
    private Integer originX = 5, originY = 5;
    private static boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown;
    private static boolean isHiting = false;
    private Integer animX = 0, animY = 2;
    private Integer clock = 0, fps = 0;

    static Animation test;

    static {
        try {
            test = new WhiteCharacterAnimation(Hair.BLOND, Head.ROBE_HOOD, Torso.TSHIRT, Hands.NONE, Legs.SKIRT, Feet.LEATHER_BOOTS, RightHand.DAGGER, LeftHand.NONE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Animation test = new BrownCharacterAnimation(Hair.BROWN, Head.LEATHER_HAT, Torso.LEATHER_ARMOR, Hands.NONE, Legs.LEATHER_PANTS, Feet.LEATHER_BOOTS, RightHand.SPEAR, LeftHand.SHIELD);*/
    /*Animation test = new WhiteCharacterAnimation(Hair.BLACK, Head.METAL_HELMET, Torso.METAL_ARMOR, Hands.METAL_GLOVES, Legs.METAL_PANTS, Feet.METAL_BOOTS, RightHand.SWORD, LeftHand.SHIELD);*/
    static BufferedImage[] anim;

    static {
        try {
            anim = test.stand((Integer)direction.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static final Integer FRAME_WIDTH = 1280;
    private static final Integer FRAME_HEIGHT = 768;
    private static final Integer TILE_SIZE = 64;

    BufferedImage trunk = ImageIO.read(new File("res/images/tree-trunk.png"));
    BufferedImage foliage = ImageIO.read(new File("res/images/tree-foliage.png"));

    public static enum GameState {
        GAME_TITLE, START_MENU, NEW_GAME
    }

    public static GameState state = GameState.GAME_TITLE;
    public static Integer onStateChange = 1;

    public JPanel p = new JPanel();

    /**********  Constructors  **********/

    public MainZeldo() throws IOException {
        setLayout(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if (onStateChange == 1) {
                        try {
                            add(displayedPanel());
                            revalidate();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000/60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        }).start();
    }

    /**********  Methods  **********/

    public JPanel displayedPanel() throws IOException {

        onStateChange = 0;
        removeAll();

        switch (state) {
            case GAME_TITLE:
                return new GameTitle();

            case START_MENU:
                return new StartMenu();

            case NEW_GAME:
                return new JPanel();

            default:
                return new GameTitle();
        }
    }

    /*private void gameLoop(){
    }*/

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (state) {
            case NEW_GAME:

                /*JPanel pan = null;
                try {
                    pan = new TestPan();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pan.setOpaque(false);
                add(pan);*/

                offsetX+=strideX;
                offsetY+=strideY;

                // Carreaux
                for (int ax = 0; ax < 40; ax++) {
                    for (int ay = 0; ay < 24; ay++) {
                        g.setColor(Color.BLACK);
                        g.drawRect(ax*TILE_SIZE/2, ay*TILE_SIZE/2, TILE_SIZE/2, TILE_SIZE/2);
                    }
                }

                g.drawImage(trunk, 2*TILE_SIZE, 6*TILE_SIZE, this);

                /*g.setColor(Color.GREEN.darker().darker());
                g.fillRect(2*TILE_SIZE, 6*TILE_SIZE + TILE_SIZE/2, TILE_SIZE/2,TILE_SIZE/2);*/

                g.setColor(new Color(0, 0, 0, .5f));
                g.fillOval(TILE_SIZE*originX + TILE_SIZE/4 + offsetX, TILE_SIZE*(originX+1) - TILE_SIZE/5 + offsetY, 32, 16);

                if (isHiting) {
                    System.out.println("hit / clock : " +clock+ " / fps : " +fps);

                    try {
                        anim = test.hit((Integer)direction.get(0), test.getRightHand());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (test.getRightHand() == RightHand.DAGGER || test.getRightHand() == RightHand.SWORD) {
                        g.drawImage(anim[fps], TILE_SIZE * originX + offsetX, TILE_SIZE * originY + offsetY, this);

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
                        g.drawImage(anim[fps], TILE_SIZE * originX + offsetX, TILE_SIZE * originY + offsetY, this);

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
                        g.drawImage(anim[animX], TILE_SIZE * originX + offsetX, TILE_SIZE * originY + offsetY, this);
                    } else {
                        g.drawImage(anim[animY], TILE_SIZE * originX + offsetX, TILE_SIZE * originY + offsetY, this);
                    }
                } else {
                    g.drawImage(anim[0], TILE_SIZE * originX + offsetX, TILE_SIZE * originY + offsetY, this);
                }

                if (offsetX%16 == 0) animX+=1;
                if (offsetY%16 == 0) animY+=1;
                if (animX == 9) animX = 0;
                if (animY == 9) animY = 2;

                g.drawImage(foliage, TILE_SIZE + TILE_SIZE/2, 4*TILE_SIZE + TILE_SIZE/2, this);
        }
    }

    public static void main(String[] args) throws IOException {
        JFrame f = new JFrame("MyGame");
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f.setLocationRelativeTo(null);
        f.setUndecorated(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.add(new MainZeldo());
        f.setVisible(true);

        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Touche pressée : " +e.getKeyCode());

                if (state == GameState.GAME_TITLE && e.getKeyCode() == KeyMap.ESCAPE.getKeyCode()) {
                    onStateChange = 1;
                    state = GameState.START_MENU;
                }

                if (state == GameState.NEW_GAME) {
                    if (e.getKeyCode() == 37 && !isMovingLeft) {
                        strideX-=stride;
                        setDirection(e.getKeyCode());
                        isMovingLeft = true;
                    }
                    if (e.getKeyCode() == 39 && !isMovingRight) {
                        strideX+=stride;
                        setDirection(e.getKeyCode());
                        isMovingRight = true;
                    }
                    if (e.getKeyCode() == 38 && !isMovingUp) {
                        strideY-=stride;
                        setDirection(e.getKeyCode());
                        isMovingUp = true;
                    }
                    if (e.getKeyCode() == 40 && !isMovingDown) {
                        strideY+=stride;
                        setDirection(e.getKeyCode());
                        isMovingDown = true;
                    }
                    if (e.getKeyCode() == 32) {
                        isHiting = true;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (state == GameState.NEW_GAME) {
                    if (e.getKeyCode() == 37) {
                        strideX+=stride;
                        removeDirection(e.getKeyCode());
                        isMovingLeft = false;
                    }
                    if (e.getKeyCode() == 39) {
                        strideX-=stride;
                        removeDirection(e.getKeyCode());
                        isMovingRight = false;
                    }
                    if (e.getKeyCode() == 38) {
                        strideY+=stride;
                        removeDirection(e.getKeyCode());
                        isMovingUp = false;
                    }
                    if (e.getKeyCode() == 40) {
                        strideY-=stride;
                        removeDirection(e.getKeyCode());
                        isMovingDown = false;
                    }
                    if (!isMovingLeft && !isMovingRight && !isMovingUp && !isMovingDown) {
                        try {
                            anim = test.stand((Integer)direction.get(0));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public static void setDirection(Integer keyCode) {
        if (!isMovingLeft && !isMovingRight && !isMovingUp && !isMovingDown) {
            direction.clear();
            direction.add(keyCode);
        } else {
            direction.add(0, keyCode);
        }
    }

    public static void removeDirection(Object keyCode) {
        if (direction.size() > 1)
            direction.remove(keyCode);
    }
}
