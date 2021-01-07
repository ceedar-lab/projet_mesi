package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.panels.maps.MapModel;
import com.mesi.animation.*;
import com.mesi.equipement.*;
import com.mesi.params.Constant;
import com.mesi.params.KeyMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel {

    /**********  Attributes  **********/

    public static Integer teleportPositionX = 10;
    public static Integer teleportPositionY = 10;
    public static ArrayList direction = new ArrayList() {{ add(KeyMap.DOWN); }};

    private MapModel map;

    private BufferedImage treeFoliage;
    private BufferedImage[] sprites;

    private Integer[] characterCoordinates;
    private Rectangle charBounds;
    private Integer offsetX = 0, offsetY = 0;
    //private ArrayList direction;

    private boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown;
    private boolean isHiting = false;
    private boolean isStanding = true;
    private boolean isBlocked = false;
    private boolean isTeleport = false;

    private Integer walkSpriteX = 0, walkSpriteY = 2;
    private Integer count = 0, hitSprite = 0;

    private BufferedImage backgroundImage;

    private boolean rightEdge = false;
    private boolean lowerEdge = false;
    private Integer translateBoundRight = (Constant.FRAME_WIDTH / 2) - Constant.TILE_SIZE;
    private Integer translateBoundDown = Constant.FRAME_HEIGHT / 2 - (Constant.TILE_SIZE * 2);



    Animation character = new WhiteCharacterAnimation(Hair.BLOND, Head.ROBE_HOOD, Torso.TSHIRT, Hands.NONE, Legs.SKIRT, Feet.LEATHER_BOOTS, RightHand.DAGGER, LeftHand.NONE);
    /*Animation character = new BrownCharacterAnimation(Hair.BROWN, Head.LEATHER_HAT, Torso.LEATHER_ARMOR, Hands.NONE, Legs.LEATHER_PANTS, Feet.LEATHER_BOOTS, RightHand.SPEAR, LeftHand.SHIELD);*/
    /*Animation character = new WhiteCharacterAnimation(Hair.BLACK, Head.METAL_HELMET, Torso.METAL_ARMOR, Hands.METAL_GLOVES, Legs.METAL_PANTS, Feet.METAL_BOOTS, RightHand.SWORD, LeftHand.SHIELD);*/

    /**********  Constructors  **********/

    public ArrayList getDirection() {
        return direction;
    }

    /**
     * Le moteur de jeu.
     * Il contient le moteur de déplacement et d'actions du personnage.
     * Il contient le moteur de collision.
     * Les caractéristiques de la map (blocs de collision, téléportation) sont ajoutées par dessus.
     * Une dernière couche graphique est ajoutée pour les éléments au premier plan.
     * @param map
     * @throws IOException
     */
    public Game(MapModel map) throws IOException {
        treeFoliage = ImageIO.read(new File("res/images/tree-foliage.png"));
        this.map = map;
        //characterCoordinates = new Integer[] { map.getStartingPositionX() * Constant.TILE_SIZE, map.getStartingPositionY() * Constant.TILE_SIZE };
        characterCoordinates = new Integer[] { teleportPositionX * Constant.TILE_SIZE, teleportPositionY * Constant.TILE_SIZE };
        charBounds = new Rectangle(characterCoordinates[0] + Constant.TILE_SIZE/2, characterCoordinates[1] + Constant.TILE_SIZE, Constant.TILE_SIZE, Constant.TILE_SIZE);
        sprites  = character.stand(map.getStartingDirection());
        //sprites  = character.stand(KeyMap.DOWN);
        backgroundImage = map.getBackgroundImage();
        setOpaque(false);
        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);

        /** Lancement du thread **/
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

    /**********  Methods  **********/

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        /** Mise à jour des coordonnées du personnage principal et de sa zone de collision **/
        if (!isBlocked) {
            characterCoordinates[0]+=offsetX;
            characterCoordinates[1]+=offsetY;
            System.out.println(characterCoordinates[0]+","+characterCoordinates[1]);
        }
        charBounds.setBounds(characterCoordinates[0] + Constant.TILE_SIZE/2, characterCoordinates[1] + Constant.TILE_SIZE, Constant.TILE_SIZE, Constant.TILE_SIZE);

        /** Arrête les mouvement du personnage si il arrive au bord de l'écran, sauf s'il est sur une case téléportation **/
        if (!isTeleport) {
            if (characterCoordinates[0] < 0 - Constant.TILE_SIZE/2) characterCoordinates[0] = 0 - Constant.TILE_SIZE/2;
            else if (characterCoordinates[0] > map.getWidth() * Constant.TILE_SIZE - Constant.SPRITE_SIZE + Constant.TILE_SIZE/2) characterCoordinates[0] = map.getWidth() * Constant.TILE_SIZE - Constant.SPRITE_SIZE + Constant.TILE_SIZE/2;
            else if (characterCoordinates[1] < 0) characterCoordinates[1] = 0;
            else if (characterCoordinates[1] > map.getHeight() * Constant.TILE_SIZE - Constant.SPRITE_SIZE) characterCoordinates[1] = map.getHeight() * Constant.TILE_SIZE - Constant.SPRITE_SIZE;
        } else {
            if (characterCoordinates[0] < 0 - Constant.TILE_SIZE/2) {
                teleportPositionX = map.getWidth();
                teleportPositionY = characterCoordinates[1] / Constant.TILE_SIZE;
                MainZeldo.onStateChange = true;
                MainZeldo.state = MainZeldo.GameState.MAP_0_1;
            }
            else if (characterCoordinates[0] > map.getWidth() * Constant.TILE_SIZE - Constant.SPRITE_SIZE + Constant.TILE_SIZE/2) {
                teleportPositionX = - Constant.TILE_SIZE / 2;
                teleportPositionY = characterCoordinates[1] / Constant.TILE_SIZE;
                MainZeldo.onStateChange = true;
                MainZeldo.state = MainZeldo.GameState.MAP_0_0;
            }
            /*else if (characterCoordinates[1] < 0) characterCoordinates[1] = 0;
            else if (characterCoordinates[1] > map.getHeight() * Constant.TILE_SIZE - Constant.SPRITE_SIZE) characterCoordinates[1] = map.getHeight() * Constant.TILE_SIZE - Constant.SPRITE_SIZE;*/
        }

        collisionChecker();
        teleportChecker();

        /** Fait défiler la map en fonction des mouvements du personnage **/
        if (map.isScrollable()) {
            rightEdge = (characterCoordinates[0] > map.getWidth() * Constant.TILE_SIZE - Constant.FRAME_WIDTH + translateBoundRight) ? true : false;
            lowerEdge = (characterCoordinates[1] > map.getHeight() * Constant.TILE_SIZE - Constant.FRAME_HEIGHT + translateBoundDown) ? true : false;
            if (!rightEdge) {
                if (characterCoordinates[0] > translateBoundRight) g.translate(0 - (characterCoordinates[0] - translateBoundRight), 0);
            } else g.translate(0 - map.getWidth() * Constant.TILE_SIZE + Constant.FRAME_WIDTH, 0);
            if (!lowerEdge) {
                if (characterCoordinates[1] > translateBoundDown) g.translate(0, 0 - (characterCoordinates[1] - translateBoundDown));
            } else g.translate(0, 0 - map.getHeight() * Constant.TILE_SIZE + Constant.FRAME_HEIGHT);
        }

        /** Affichage de la grille **/
        /*for (int x = 0; x < Constant.MAP_WIDTH; x++) {
            for (int y = 0; y < Constant.MAP_HEIGHT; y++) {
                g.setColor(Color.BLACK);
                g.drawRect(x * Constant.TILE_SIZE, y * Constant.TILE_SIZE, Constant.TILE_SIZE, Constant.TILE_SIZE);
            }
        }*/

        g.drawImage(backgroundImage, 0, 0, this);

        /** Ombre du personnage **/
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(characterCoordinates[0] + 16, characterCoordinates[1] + 48, 32, 14);

        /** Mise à jour des mouvements du personnage **/
        if (isHiting) { // Attaque
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

        } else if (isMovingRight || isMovingLeft || isMovingUp || isMovingDown) { // Déplacement
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
        } else { // Position debout
            g.drawImage(sprites[0], characterCoordinates[0], characterCoordinates[1], this);
            isStanding = true;
        }

        if (characterCoordinates[0]%16 == 0) walkSpriteX+=1;
        if (characterCoordinates[1]%16 == 0) walkSpriteY+=1;
        if (walkSpriteX == 9) walkSpriteX = 0;
        if (walkSpriteY == 9) walkSpriteY = 2;

        switch(MainZeldo.state) {
            case MAP_0_0:
                g.drawImage(treeFoliage, Constant.TILE_SIZE * 8, Constant.TILE_SIZE * 13, this);
                g.drawImage(treeFoliage, Constant.TILE_SIZE * 4, Constant.TILE_SIZE * 11, this);
                g.drawImage(treeFoliage, Constant.TILE_SIZE * 7, Constant.TILE_SIZE * 8, this);
                g.drawImage(treeFoliage, Constant.TILE_SIZE * 12, Constant.TILE_SIZE * 10, this);
                break;
        }
    }

    /**
     * Action a effectuer lorsqu'une touche est pressée.
     * @param keyCode
     */
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

    /**
     * Action a effectuer lorsqu'une touche est relachée.
     * @param keyCode
     */
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

    /**
     * Ajoute une nouvelle direction à la file d'attente lorsqu'une touche est pressée.
     * @param keyCode
     */
    public void setDirection(Integer keyCode) {
        if (!isMovingLeft && !isMovingRight && !isMovingUp && !isMovingDown) {
            direction.clear();
            direction.add(keyCode);
        } else {
            direction.add(0, keyCode);
        }
    }

    /**
     * Supprime la direction de la file d'attente.
     * @param keyCode
     */
    public void removeDirection(Object keyCode) {
        if (direction.size() > 1)
            direction.remove(keyCode);
    }

    /**
     * Teste si le personnage entre en collision avec un des blocs de collision de la map.
     */
    public void collisionChecker() {
        boolean collision = false;
        if (isBlocked) {
            charBounds.setBounds(characterCoordinates[0] + Constant.TILE_SIZE/2 + 2, characterCoordinates[1] + Constant.TILE_SIZE + 2, Constant.TILE_SIZE - 4, Constant.TILE_SIZE - 4);
        }
        for (Rectangle block : map.getLeftBounds()) {
            if (charBounds.intersects(block)) {
                isBlocked = true;
                characterCoordinates[0] -= Constant.STRIDE;
                collision = true;
                break;
            }
        }
        if (!collision) {
            for (Rectangle block : map.getRightBounds()) {
                if (charBounds.intersects(block)) {
                    isBlocked = true;
                    characterCoordinates[0] += Constant.STRIDE;
                    collision = true;
                    break;
                }
            }
        }
        if (!collision) {
            for (Rectangle block : map.getUpperBounds()) {
                if (charBounds.intersects(block)) {
                    isBlocked = true;
                    characterCoordinates[1] -= Constant.STRIDE;
                    collision = true;
                    break;
                }
            }
        }
        if (!collision) {
            for (Rectangle block : map.getLowerBounds()) {
                if (charBounds.intersects(block)) {
                    isBlocked = true;
                    characterCoordinates[1] += Constant.STRIDE;
                    break;
                }
            }
        }
        if (!collision) isBlocked = false;
    }

    /**
     * Teste si le personnage entre sur une case de téléportation de la map.
     */
    public void teleportChecker() {
        boolean teleport = false;
        for (Rectangle block : map.getTeleport()) {
            if (!teleport) {
                if (charBounds.intersects(block)) {
                    isTeleport = true;
                    teleport = true;
                }
                else isTeleport = false;
            }
        }
    }
}
