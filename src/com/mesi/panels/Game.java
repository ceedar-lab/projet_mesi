package com.mesi.panels;

import com.mesi.panels.maps.MapModel;
import com.mesi.panels.maps.Tile;
import com.mesi.animation.*;
import com.mesi.equipement.*;
import com.mesi.params.Constant;
import com.mesi.params.KeyMap;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel {
    
    private Integer offsetX = 0, offsetY = 0;
    private ArrayList direction;
    private boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown;
    private boolean isHiting = false;
    private Integer walkSpriteX = 0, walkSpriteY = 2;
    private Integer count = 0, hitSprite = 0;
    private Integer[] characterCoordinates;

    private static boolean isStanding = true;
    private static boolean isBlocked = false;

    static Rectangle charBounds;

    BufferedImage[] sprites;

    private MapModel map;

    Animation character = new WhiteCharacterAnimation(Hair.BLOND, Head.ROBE_HOOD, Torso.TSHIRT, Hands.NONE, Legs.SKIRT, Feet.LEATHER_BOOTS, RightHand.DAGGER, LeftHand.NONE);
    /*Animation character = new BrownCharacterAnimation(Hair.BROWN, Head.LEATHER_HAT, Torso.LEATHER_ARMOR, Hands.NONE, Legs.LEATHER_PANTS, Feet.LEATHER_BOOTS, RightHand.SPEAR, LeftHand.SHIELD);*/
    /*Animation character = new WhiteCharacterAnimation(Hair.BLACK, Head.METAL_HELMET, Torso.METAL_ARMOR, Hands.METAL_GLOVES, Legs.METAL_PANTS, Feet.METAL_BOOTS, RightHand.SWORD, LeftHand.SHIELD);*/

    public Game(MapModel map) throws IOException {
        this.map = map;
        characterCoordinates = new Integer[] { map.getStartingPositionX() * Constant.TILE_SIZE, map.getStartingPositionY() * Constant.TILE_SIZE };
        charBounds = new Rectangle(characterCoordinates[0] + Constant.TILE_SIZE/2, characterCoordinates[1] + Constant.TILE_SIZE, Constant.TILE_SIZE, Constant.TILE_SIZE);
        direction = new ArrayList() {{ add(map.getStartingDirection()); }};
        sprites  = character.stand(map.getStartingDirection());
        //setOpaque(false);
        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        if (!isStanding) {
                            repaint();
                        }
                        Thread.sleep(Constant.FPS);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isBlocked) {
            characterCoordinates[0]+=offsetX;
            characterCoordinates[1]+=offsetY;
        }

        collisionChecker();

        g.setColor(Color.RED);
        g.fillRect(32*3, 32*2, Constant.TILE_SIZE, Constant.TILE_SIZE*2);
        g.fillRect(32*3, 32*5, Constant.TILE_SIZE, Constant.TILE_SIZE*4);
        g.fillRect(32*4, 32*2, Constant.TILE_SIZE, Constant.TILE_SIZE);
        g.fillRect(32*5, 32*2, Constant.TILE_SIZE, Constant.TILE_SIZE*2);

        // Carreaux
        for (int x = 0; x < Constant.MAP_WIDTH; x++) {
            for (int y = 0; y < Constant.MAP_HEIGHT; y++) {
                g.setColor(Color.BLACK);
                g.drawRect(x * Constant.TILE_SIZE, y * Constant.TILE_SIZE, Constant.TILE_SIZE, Constant.TILE_SIZE);
            }
        }

        g.setColor(Color.GREEN.darker().darker());
        g.fillRect(4*Constant.TILE_SIZE, 11*Constant.TILE_SIZE, Constant.TILE_SIZE,Constant.TILE_SIZE);

        // Ombre du personnage
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(characterCoordinates[0] + 16, characterCoordinates[1] + 51, 32, 14);

        if (isHiting) {
            try {
                sprites = character.hit((Integer)direction.get(0), character.getRightHand());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (character.getRightHand() == RightHand.DAGGER || character.getRightHand() == RightHand.SWORD) {
                g.drawImage(sprites[hitSprite], characterCoordinates[0], characterCoordinates[1], this);

                count++;

                if (count == 4) {
                    hitSprite++;
                    count = 0;
                }
                if (hitSprite == 6) {
                    hitSprite = 0;
                    isHiting = false;
                }
            } else {
                g.drawImage(sprites[hitSprite], characterCoordinates[0], characterCoordinates[1], this);

                count++;

                if (count == 4) {
                    hitSprite++;
                    count = 0;
                }
                if (hitSprite == 8) {
                    hitSprite = 0;
                    isHiting = false;
                }
            }

        } else if (isMovingRight || isMovingLeft || isMovingUp || isMovingDown) {
            try {
                sprites = character.walkCycle((Integer)direction.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (direction.get(0).equals(KeyMap.LEFT) || direction.get(0).equals(KeyMap.RIGHT)) {
                g.drawImage(sprites[walkSpriteX], characterCoordinates[0], characterCoordinates[1], this);
            } else {
                g.drawImage(sprites[walkSpriteY], characterCoordinates[0], characterCoordinates[1], this);
            }
        } else {
            g.drawImage(sprites[0], characterCoordinates[0], characterCoordinates[1], this);
            isStanding = true;
        }

        if (characterCoordinates[0]%16 == 0) walkSpriteX+=1;
        if (characterCoordinates[1]%16 == 0) walkSpriteY+=1;
        if (walkSpriteX == 9) walkSpriteX = 0;
        if (walkSpriteY == 9) walkSpriteY = 2;
    }

    public void onKeyPressed(int keyCode) {
        isStanding = false;
        if (keyCode == KeyMap.LEFT && !isMovingLeft) {
            offsetX -= Constant.STRIDE;
            setDirection(keyCode);
            isMovingLeft = true;
        }
        if (keyCode == KeyMap.RIGHT && !isMovingRight) {
            offsetX += Constant.STRIDE;
            setDirection(keyCode);
            isMovingRight = true;
        }
        if (keyCode == KeyMap.UP && !isMovingUp) {
            offsetY -= Constant.STRIDE;
            setDirection(keyCode);
            isMovingUp = true;
        }
        if (keyCode == KeyMap.DOWN && !isMovingDown) {
            offsetY += Constant.STRIDE;
            setDirection(keyCode);
            isMovingDown = true;
        }
        if (keyCode == KeyMap.ATTACK) {
            isHiting = true;
        }
    }

    public void onKeyReleased(int keyCode) {
        if (keyCode == KeyMap.LEFT) {
            offsetX += Constant.STRIDE;
            removeDirection(keyCode);
            isMovingLeft = false;
        }
        if (keyCode == KeyMap.RIGHT) {
            offsetX -= Constant.STRIDE;
            removeDirection(keyCode);
            isMovingRight = false;
        }
        if (keyCode == KeyMap.UP) {
            offsetY += Constant.STRIDE;
            removeDirection(keyCode);
            isMovingUp = false;
        }
        if (keyCode == KeyMap.DOWN) {
            offsetY -= Constant.STRIDE;
            removeDirection(keyCode);
            isMovingDown = false;
        }
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

    public void collisionChecker() {
        boolean collision = false;
        charBounds = new Rectangle(characterCoordinates[0] + Constant.TILE_SIZE/2, characterCoordinates[1] + Constant.TILE_SIZE, Constant.TILE_SIZE, Constant.TILE_SIZE);
        if (isBlocked) {
            charBounds = new Rectangle(characterCoordinates[0] + Constant.TILE_SIZE/2 + 2, characterCoordinates[1] + Constant.TILE_SIZE + 2, Constant.TILE_SIZE - 4, Constant.TILE_SIZE - 4);
        }
        for (Rectangle element : map.getLeftBounds()) {
            if (charBounds.intersects(element)) {
                isBlocked = true;
                characterCoordinates[0] -= Constant.STRIDE;
                collision = true;
                break;
            }
        }
        if (!collision) {
            for (Rectangle element : map.getRightBounds()) {
                if (charBounds.intersects(element)) {
                    isBlocked = true;
                    characterCoordinates[0] += Constant.STRIDE;
                    collision = true;
                    break;
                }
            }
        }
        if (!collision) {
            for (Rectangle element : map.getUpperBounds()) {
                if (charBounds.intersects(element)) {
                    isBlocked = true;
                    characterCoordinates[1] -= Constant.STRIDE;
                    collision = true;
                    break;
                }
            }
        }
        if (!collision) {
            for (Rectangle element : map.getLowerBounds()) {
                if (charBounds.intersects(element)) {
                    isBlocked = true;
                    characterCoordinates[1] += Constant.STRIDE;
                    break;
                }
            }
        }
        if (!collision) isBlocked = false;
    }

    /*public void collisionChecker2() {
        charBounds = new Rectangle(startingPositionX + Constant.TILE_SIZE/2 + offsetX, startingPositionY + Constant.TILE_SIZE + offsetY, Constant.TILE_SIZE, Constant.TILE_SIZE);
        if (isBlocked) {
            charBounds = new Rectangle(startingPositionX + Constant.TILE_SIZE/2 + 2 + offsetX, startingPositionY + Constant.TILE_SIZE + 2 + offsetY, Constant.TILE_SIZE - 4, Constant.TILE_SIZE - 4);
        }
        for (Rectangle element : map.getLeftBounds()) {
            if (charBounds.intersects(element)) {
                rightMoveDisabled = true;
                offsetX = 0;
                isMovingRight = false;
            } else rightMoveDisabled = false;
        }
        for (Rectangle element : map.getRightBounds()) {
            if (charBounds.intersects(element)) {
                offsetX = 0;
            } else {
                leftMoveDisabled = false;
            }
        }
        for (Rectangle element : map.getUpperBounds()) {
            if (charBounds.intersects(element)) {
                downMoveDisabled = true;
                offsetY = 0;
                isMovingDown = false;
            } else downMoveDisabled = false;
        }
        for (Rectangle element : map.getLowerBounds()) {
            if (charBounds.intersects(element)) {
                upMoveDisabled = true;
                offsetY = 0;
                isMovingUp = false;
            } else upMoveDisabled = false;
        }
    }*/
}
