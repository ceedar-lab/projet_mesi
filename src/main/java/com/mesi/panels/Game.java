package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.animation.*;
import com.mesi.decor.Bush;
import com.mesi.decor.Chest;
import com.mesi.decor.DecorObject;
import com.mesi.equipement.*;
import com.mesi.panels.maps.MapModel;
import com.mesi.panels.maps.Tile;
import com.mesi.params.Constant;
import com.mesi.params.Images;
import com.mesi.params.KeyMap;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel {

    /**********  Attributes  **********/

    private MapModel map;

    private String teleportMap;
//    public static Integer startingPositionX = 11;
//    public static Integer startingPositionY = 11;
    public static Integer[] characterCoordinates = new Integer[] { 11 * Constant.TILE_SIZE, 11 * Constant.TILE_SIZE };
    public static ArrayList<Integer> direction = new ArrayList() {{
        add(KeyMap.DOWN);
    }};

    private BufferedImage[] sprites;

    //private Integer[] characterCoordinates;
    private Rectangle charBounds;
    private Rectangle charActionArea;

    private static boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown;
    private static boolean isStanding = true;
    private boolean isHiting = false;
    private boolean isActionning = false;
    private boolean isBlocked = false;
    private boolean isTeleport = false;
    private boolean stopDebug = false;

    private Integer walkSpriteX = 0, walkSpriteY = 2;
    private Integer count = 0, hitSprite = 0;

    private BufferedImage backgroundImage;
    private BufferedImage foregroundImage;

    public static boolean pause = false;
    public static boolean killThread = false;

    private boolean rightEdge = false;
    private boolean lowerEdge = false;
    private Integer translateBoundRight = (Constant.FRAME_WIDTH / 2) - Constant.TILE_SIZE;
    private Integer translateBoundDown = Constant.FRAME_HEIGHT / 2 - (Constant.TILE_SIZE * 2);

    Animation character = new WhiteCharacterAnimation(Hair.BLOND, Head.NONE, Torso.NONE, Hands.NONE, Legs.NONE, Feet.NONE, RightHand.DAGGER, LeftHand.NONE);
//    Animation character = new BrownCharacterAnimation(Hair.BROWN, Head.LEATHER_HAT, Torso.LEATHER_ARMOR, Hands.NONE, Legs.LEATHER_PANTS, Feet.LEATHER_BOOTS, RightHand.SPEAR, LeftHand.SHIELD);
//    Animation character = new WhiteCharacterAnimation(Hair.BLACK, Head.METAL_HELMET, Torso.METAL_ARMOR, Hands.METAL_GLOVES, Legs.METAL_PANTS, Feet.METAL_BOOTS, RightHand.SWORD, LeftHand.SHIELD);

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                while (true) {
                    if (killThread) Thread.currentThread().stop();
                    if (!isStanding && !pause) {
                        setCharCoordinates();
                        charBounds.setBounds(characterCoordinates[0], characterCoordinates[1], Constant.TILE_SIZE, Constant.TILE_SIZE);
                        getActionArea();
                        teleportChecker();
                        if (isHiting || isActionning) {
                            hitChecker(charActionArea);
                        }
                        repaint();

                        if (stopDebug) {
                            System.out.println("petite pause");
                        }
                    }
                    Thread.sleep(Constant.FPS);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    /**********  Constructors  **********/

    public Game() throws IOException {}

    /**
     * Le moteur de jeu.
     * Il contient le moteur de déplacement et d'actions du personnage.
     * Il contient le moteur de collision.
     * Les caractéristiques de la map (blocs de collision, téléportation) sont ajoutées par dessus.
     * Une dernière couche graphique est ajoutée pour les éléments au premier plan.
     *
     * @param map
     * @throws IOException
     */
    public Game(MapModel map) throws IOException {
        new Images();
        this.map = map;
//        characterCoordinates = new Integer[] { startingPositionX * Constant.TILE_SIZE, startingPositionY * Constant.TILE_SIZE };
//        charBounds = new Rectangle(characterCoordinates[0] + Constant.TILE_SIZE/2, characterCoordinates[1] + Constant.TILE_SIZE, Constant.TILE_SIZE, Constant.TILE_SIZE);
        charBounds = new Rectangle(characterCoordinates[0], characterCoordinates[1], Constant.TILE_SIZE, Constant.TILE_SIZE);
        getActionArea();
        sprites = character.stand(direction.get(0));
        backgroundImage = map.getBackgroundImage();
        foregroundImage = map.getForegroundImage();
        setOpaque(false);
        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);

        /** Lancement du thread **/
        thread.start();
    }

    /**********  Methods  **********/

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        /** Fait défiler la map en fonction des mouvements du personnage **/
        if (map.isScrollable()) {
            rightEdge = (characterCoordinates[0] > map.getWidth() * Constant.TILE_SIZE - Constant.FRAME_WIDTH + translateBoundRight) ? true : false;
            lowerEdge = (characterCoordinates[1] > map.getHeight() * Constant.TILE_SIZE - Constant.FRAME_HEIGHT + translateBoundDown) ? true : false;
            if (!rightEdge) {
                if (characterCoordinates[0] > translateBoundRight) {
                    g.translate(0 - (characterCoordinates[0] - translateBoundRight), 0);
                }
            } else {
                g.translate(0 - map.getWidth() * Constant.TILE_SIZE + Constant.FRAME_WIDTH, 0);
            }
            if (!lowerEdge) {
                if (characterCoordinates[1] > translateBoundDown) {
                    g.translate(0, 0 - (characterCoordinates[1] - translateBoundDown));
                }
            } else {
                g.translate(0, 0 - map.getHeight() * Constant.TILE_SIZE + Constant.FRAME_HEIGHT);
            }
            g.drawImage(backgroundImage, 0, 0,this);
        } else {
            g.drawImage(backgroundImage,
                    -((int)Math.floor((backgroundImage.getWidth() - Constant.FRAME_WIDTH) / 64)) * 32,
                    -((int)Math.floor((backgroundImage.getHeight() - Constant.FRAME_HEIGHT) / 64)) * 32,
                    this);
        }

        /** Affiche les objets de décor en arrière plan **/
        for (DecorObject decorObject : map.getDecorObjectArraylist()) {
            if (decorObject.getBackgroundImage() != null) {
                g.drawImage(decorObject.getBackgroundImage(), decorObject.getX() + decorObject.getBackgroundOffsetX(), decorObject.getY() + decorObject.getBackgroundOffsetY(), this);
            }
        }

        /** Ombre du personnage **/
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(characterCoordinates[0], characterCoordinates[1] + 16, 32, 14);

        Integer offsetX = -Constant.TILE_SIZE / 2;
        Integer offsetY = -Constant.TILE_SIZE;

        /** Mise à jour des mouvements du personnage **/
        if (isHiting) { // Attaque
            try {
                sprites = character.hit(direction.get(0), character.getRightHand());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (character.getRightHand() == RightHand.DAGGER || character.getRightHand() == RightHand.SWORD) {
                g.drawImage(sprites[hitSprite], characterCoordinates[0] + offsetX, characterCoordinates[1] + offsetY, this);

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
                g.drawImage(sprites[hitSprite], characterCoordinates[0] + offsetX, characterCoordinates[1] + offsetY, this);

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
                sprites = character.walkCycle(direction.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (direction.get(0).equals(KeyMap.LEFT) || direction.get(0).equals(KeyMap.RIGHT)) {
                g.drawImage(sprites[walkSpriteX], characterCoordinates[0] + offsetX, characterCoordinates[1] + offsetY, this);
            } else {
                g.drawImage(sprites[walkSpriteY], characterCoordinates[0] + offsetX, characterCoordinates[1] + offsetY, this);
            }
        } else { // Position debout
            g.drawImage(sprites[0], characterCoordinates[0] + offsetX, characterCoordinates[1] + offsetY, this);
            isStanding = true;
        }

        if (characterCoordinates[0] % 16 == 0) {
            walkSpriteX += 1;
        }
        if (characterCoordinates[1] % 16 == 0) {
            walkSpriteY += 1;
        }
        if (walkSpriteX == 9) {
            walkSpriteX = 0;
        }
        if (walkSpriteY == 9) {
            walkSpriteY = 2;
        }

        /** affiche les elements de la carte au premier plan **/
        if (map.isScrollable()) {
            g.drawImage(foregroundImage, 0, 0, this);
        } else
            g.drawImage(foregroundImage,
                    -((int)Math.floor((foregroundImage.getWidth() - Constant.FRAME_WIDTH) / 64)) * 32,
                    -((int)Math.floor((foregroundImage.getHeight() - Constant.FRAME_HEIGHT) / 64)) * 32,
                    this);

        /** affiche les objet de decor au premier plan **/
        for (DecorObject decorObject : map.getDecorObjectArraylist()) {
            if (decorObject.getForegroundImage() != null) {
                g.drawImage(decorObject.getForegroundImage(), decorObject.getX() + decorObject.getForegroundOffsetX(), decorObject.getY() + decorObject.getForegroundOffsetY(), this);
            }
        }

//        /** Affichage des cases de téléportation en jaune **/
//        for (Tile teleport : map.getTeleportList()) {
//            g.setColor(new Color(255, 255, 0, 180));
//            g.fillRect(teleport.getTeleportBounds().x, teleport.getTeleportBounds().y, teleport.getTeleportBounds().width, teleport.getTeleportBounds().height);
//        }
//
//        /** met en surbrillance rouge les zone de collision **/
//        for (Rectangle hitbox : map.getHitboxList()) {
//            g.setColor(new Color(255, 0, 0, 100));
//            g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
//        }
//
//        /** met en surbrillance violete la zone de collision du perso **/
//        g.setColor(new Color(255, 0, 255, 100));
//        g.fillRect(charBounds.x, charBounds.y, charBounds.width, charBounds.height);
//
//        /** met en surbrillance orange la zone d'action du personnage **/
//        g.setColor(new Color(255, 128, 0, 100));
//        g.fillRect(charActionArea.x, charActionArea.y, charActionArea.width, charActionArea.height);


//        /** met en surbrillance jaune le dos du personnage **/
//        g.setColor(new Color(255,255,0,180));
//        g.fillRect(getCharBack().x,getCharBack().y,getCharBack().width,getCharBack().height);


    }

    /**
     * Action a effectuer lorsqu'une touche est pressée.
     *
     * @param keyCode
     */
    public void onKeyPressed(int keyCode) {
        isStanding = false;
        if (keyCode == KeyMap.LEFT && !isMovingLeft) {
            setDirection(keyCode);
            isMovingLeft = true;
        }
        if (keyCode == KeyMap.RIGHT && !isMovingRight) {
            setDirection(keyCode);
            isMovingRight = true;
        }
        if (keyCode == KeyMap.UP && !isMovingUp) {
            setDirection(keyCode);
            isMovingUp = true;
        }
        if (keyCode == KeyMap.DOWN && !isMovingDown) {
            setDirection(keyCode);
            isMovingDown = true;
        }
        if (keyCode == KeyMap.ATTACK && character.getRightHand().toString() != "NONE") {
            isHiting = true;
        }
        if (keyCode == KeyMap.ESCAPE) {
            pause = true;
            new GameMenu();
        }
        if (keyCode == KeyMap.ACTION) {
            isActionning = true;
        }
        if (keyCode == 83) {
            stopDebug = !stopDebug;
        }


    }

    /**
     * Action a effectuer lorsqu'une touche est relachée.
     *
     * @param keyCode
     */
    public void onKeyReleased(int keyCode) {
        if (keyCode == KeyMap.LEFT) {
            removeDirection(keyCode);
            isMovingLeft = false;
        }
        if (keyCode == KeyMap.RIGHT) {
            removeDirection(keyCode);
            isMovingRight = false;
        }
        if (keyCode == KeyMap.UP) {
            removeDirection(keyCode);
            isMovingUp = false;
        }
        if (keyCode == KeyMap.DOWN) {
            removeDirection(keyCode);
            isMovingDown = false;
        }
    }

    /**
     * Modifie les coordonnées du personnage en fonction de sa direction de déplacement et de la longueur de son pas (Constant.STRIDE).
     */
    public void setCharCoordinates() {
        if (isMovingRight) {
            Rectangle test = new Rectangle(characterCoordinates[0] + Constant.STRIDE, characterCoordinates[1], Constant.TILE_SIZE, Constant.TILE_SIZE);
            if (!collisionChecker(test)) {
                characterCoordinates[0] += Constant.STRIDE;
            }
        }
        if (isMovingLeft) {
            Rectangle test = new Rectangle(characterCoordinates[0] - Constant.STRIDE, characterCoordinates[1], Constant.TILE_SIZE, Constant.TILE_SIZE);
            if (!collisionChecker(test)) {
                characterCoordinates[0] -= Constant.STRIDE;
            }
        }
        if (isMovingUp) {
            Rectangle test = new Rectangle(characterCoordinates[0], characterCoordinates[1] - Constant.STRIDE, Constant.TILE_SIZE, Constant.TILE_SIZE);
            if (!collisionChecker(test)) {
                characterCoordinates[1] -= Constant.STRIDE;
            }
        }
        if (isMovingDown) {
            Rectangle test = new Rectangle(characterCoordinates[0], characterCoordinates[1] + Constant.STRIDE, Constant.TILE_SIZE, Constant.TILE_SIZE);
            if (!collisionChecker(test)) {
                characterCoordinates[1] += Constant.STRIDE;
            }
        }
    }

    /**
     * Ajoute une nouvelle direction à la file d'attente lorsqu'une touche est pressée.
     *
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
     *
     * @param keyCode
     */
    public void removeDirection(Object keyCode) {
        if (direction.size() > 1) {
            direction.remove(keyCode);
        }
    }

    /**
     * Teste si le personnage entre en collision avec un des blocs de collision de la map.
     */
    public boolean collisionChecker(Rectangle rectangle) {
        boolean collision = false;

        /** test des collision hitbox **/
        if (!collision) {
            for (Rectangle block : map.getHitboxList()) {
                if (rectangle.intersects(block)) {
                    isBlocked = true;
                    collision = true;
                    break;
                }
            }
        }
        if (!collision) {
            isBlocked = false;
        }

        return collision;
    }

    /**
     * Teste si le personnage entre sur une case de téléportation de la map.
     */
    public void teleportChecker() {

//        Rectangle charBack = getCharBack();

        Rectangle charCenter = new Rectangle(charBounds.x + 15, charBounds.y + 15, 2, 2);
        for (Tile tile : map.getTeleportList()) {
            if (charCenter.intersects(tile.getTeleportBounds())) {
                String teleportCoord = (tile.getY() + Constant.TILE_SIZE) / Constant.TILE_SIZE + "," + (tile.getY() + Constant.TILE_SIZE) / Constant.TILE_SIZE;
                teleportMap = tile.getBindedTile().split(" ")[0];
                characterCoordinates[0] = Integer.parseInt(tile.getBindedTile().split(" ")[1].split(",")[0]) * Constant.TILE_SIZE;
                characterCoordinates[1] = Integer.parseInt(tile.getBindedTile().split(" ")[1].split(",")[1]) * Constant.TILE_SIZE;
                isTeleport = true;

                MainZeldo.state = MainZeldo.GameState.valueOf(teleportMap);
                MainZeldo.onStateChange = true;
                Thread.currentThread().stop();
            }
        }
    }


//    public Rectangle getCharBack()
//    {
//
//        Integer backWidth = 2;
//        Rectangle charBack = null;
//        switch ((Integer)direction.get(0))
//        {
//            case 37:
//            {
//                charBack = new Rectangle(charBounds.x+charBounds.width,charBounds.y,backWidth,charBounds.height);
//                break;
//            }
//            case 38:
//            {
//                charBack = new Rectangle(charBounds.x,charBounds.y+charBounds.height,charBounds.width,backWidth);
//                break;
//            }
//            case 39:
//            {
//                charBack = new Rectangle(charBounds.x-backWidth,charBounds.y,backWidth,charBounds.height);
//                break;
//            }
//            case 40:
//            {
//                charBack = new Rectangle(charBounds.x,charBounds.y-backWidth,charBounds.width,backWidth);
//                break;
//            }
//
//        }
//
//        return charBack;
//    }

    public void getActionArea() {
        Integer actionWidth = 10;

        if (direction.get(0) == KeyMap.LEFT)
            charActionArea = new Rectangle(charBounds.x - actionWidth, charBounds.y + 11, actionWidth, 10);
        else if (direction.get(0) == KeyMap.UP)
            charActionArea = new Rectangle(charBounds.x  + 12, charBounds.y - actionWidth, 10, actionWidth);
        else if (direction.get(0) == KeyMap.RIGHT)
            charActionArea = new Rectangle(charBounds.x + charBounds.width, charBounds.y + 11, actionWidth, 10);
        else if (direction.get(0) == KeyMap.DOWN)
            charActionArea = new Rectangle(charBounds.x + 12, charBounds.y + charBounds.height, 10, actionWidth);


        /*switch ((Integer) direction.get(0)) {
            case 37: {
                charActionArea = new Rectangle(charBounds.x - actionWidth, charBounds.y, actionWidth, charBounds.height);
                break;
            }
            case 38: {
                charActionArea = new Rectangle(charBounds.x, charBounds.y - actionWidth, charBounds.width, actionWidth);
                break;
            }
            case 39: {
                charActionArea = new Rectangle(charBounds.x + charBounds.width, charBounds.y, actionWidth, charBounds.height);
                break;
            }
            case 40: {
                charActionArea = new Rectangle(charBounds.x, charBounds.y + charBounds.height, charBounds.width, actionWidth);
                break;
            }
        }*/

    }


    /**
     * Teste si l'action du personnage entre en collision avec un des blocs d'interaction de la map.
     */
    public void hitChecker(Rectangle rectangle) {
        ArrayList<DecorObject> tempToRemove = new ArrayList<>();
        ArrayList<DecorObject> tempToAdd = new ArrayList<>();
        for (DecorObject decorObject : map.getDecorObjectArraylist()) {
            if (rectangle.intersects(decorObject.getHitbox())) {
                if (isHiting) {
                    if (decorObject instanceof Bush) {
                        tempToRemove.add(decorObject);
                    }
                }
                if (isActionning) {
                    if (decorObject instanceof Chest) {
                        tempToAdd.add(new Chest("open", decorObject.getX() / Constant.TILE_SIZE, decorObject.getY() / Constant.TILE_SIZE));
                        tempToRemove.add(decorObject);
                    }
                }
            }
        }

        for (DecorObject decorObject : tempToRemove)
            map.getDecorObjectArraylist().remove(decorObject);
        for (DecorObject decorObject : tempToAdd)
            map.getDecorObjectArraylist().add(decorObject);

        isActionning = false;
    }
}
