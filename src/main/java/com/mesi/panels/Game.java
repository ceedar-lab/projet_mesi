package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.animation.*;
import com.mesi.decor.Bush;
import com.mesi.decor.Chest;
import com.mesi.decor.DecorObject;
import com.mesi.decor.collectable.CollectableItem;
import com.mesi.equipement.*;
import com.mesi.panels.maps.MapModel;
import com.mesi.panels.maps.Tile;
import com.mesi.params.Constant;
import com.mesi.params.KeyMap;
import com.mesi.pnj.Pnj;
import com.mesi.sound.Player;
import com.mesi.sound.Sounds;
import org.apache.log4j.Logger;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel {

    /**********  Attributes  **********/

    public static Player music;

    private static Logger logger = Logger.getLogger(Game.class);

    private MapModel map;

    private static Integer[] characterCoordinates = new Integer[] { 11 * Constant.TILE_SIZE, 11 * Constant.TILE_SIZE };
    private static List<Integer> direction = new ArrayList<>();
    static {
        direction.add(KeyMap.DOWN);
    }
    private static boolean pause = false;
    private static boolean killThread = false;
    private static boolean stopDebug = false;

    private BufferedImage[] sprites;
    public static BufferedImage charPic;

    private Rectangle charBounds;
    private Rectangle charActionArea;

    private boolean isMovingLeft;
    private boolean isMovingRight;
    private boolean isMovingUp;
    private boolean isMovingDown;
    private boolean isStanding = true;
    private boolean isHiting = false;
    private boolean isActing = false;

    private Integer walkSpriteX = 0;
    private Integer walkSpriteY = 2;
    private Integer count = 0;
    private Integer hitSprite = 0;

    private BufferedImage backgroundImage;
    private BufferedImage foregroundImage;

    private Integer translateBoundRight = (Constant.FRAME_WIDTH / 2) - Constant.TILE_SIZE;
    private Integer translateBoundDown = Constant.FRAME_HEIGHT / 2 - (Constant.TILE_SIZE * 2);

    private static Animation character;

    static {
        try {
            character = new WhiteCharacterAnimation(Hair.BLOND, Head.NONE, Torso.NONE, Hands.NONE, Legs.NONE, Feet.NONE, RightHand.NONE, LeftHand.NONE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                do {
                    if (!isStanding && !pause) {
                        setCharCoordinates();
                        charBounds.setBounds(characterCoordinates[0], characterCoordinates[1], Constant.TILE_SIZE, Constant.TILE_SIZE);
                        getActionArea();
                        teleportChecker();
                        if (isHiting || isActing) {
                            hitChecker(charActionArea);
                        }
                        repaint();

                        if (stopDebug) {
                            logger.info("Petite pause");
                        }
                    }
                    Thread.sleep(Constant.FPS);
                } while (!killThread);
            } catch (Exception e) {
                logger.error("Erreur lors de l'exécution du thread");
            }
            Thread.currentThread().interrupt();
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
        this.map = map;
        switch (map.getClass().getSimpleName()) {
            case "Map1": music = new Player(Sounds.FOREST, true); break;
            case "Map2": music = new Player(Sounds.HOME, true); break;
        }
        charBounds = new Rectangle(characterCoordinates[0], characterCoordinates[1], Constant.TILE_SIZE, Constant.TILE_SIZE);
        getActionArea();
        sprites = character.stand(direction.get(0));
        charPic = character.stand(KeyMap.DOWN)[0];
        backgroundImage = map.getBackgroundImage();
        foregroundImage = map.getForegroundImage();
        setOpaque(false);
        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);

        /** Lancement du thread **/
        thread.start();
    }

    /**********  Getters / Setters  **********/

    public static Integer[] getCharacterCoordinates() { return characterCoordinates; }
    public static void setCharacterCoordinates(Integer[] characterCoordinates) { Game.characterCoordinates = characterCoordinates; }
    public static List<Integer> getDirection() { return direction; }
    public static void setDirection(List<Integer> direction) { Game.direction = direction; }
    public static boolean isPaused() { return pause; }
    public static void setPause(boolean pause) { Game.pause = pause; }
    public static boolean isThreadKilled() { return killThread; }
    public static void setKillThread(boolean killThread) { Game.killThread = killThread; }
    public static boolean isDebugStopped() { return stopDebug; }
    public static void setStopDebug(boolean stopDebug) { Game.stopDebug = stopDebug; }
    public static Animation getCharacter() { return character; }

    /**********  Methods  **********/

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Integer offsetXChar = -Constant.TILE_SIZE / 2;
        Integer offsetYChar = -Constant.TILE_SIZE;

        /** Fait défiler la map en fonction des mouvements du personnage **/
        boolean rightEdge;
        boolean lowerEdge;

        if (map.isScrollable()) {
            rightEdge = (characterCoordinates[0] > map.getMapWidth() * Constant.TILE_SIZE - Constant.FRAME_WIDTH + translateBoundRight);
            lowerEdge = (characterCoordinates[1] > map.getMapHeight() * Constant.TILE_SIZE - Constant.FRAME_HEIGHT + translateBoundDown);
            if (!rightEdge) {
                if (characterCoordinates[0] > translateBoundRight) {
                    g.translate(0 - (characterCoordinates[0] - translateBoundRight), 0);
                }
            } else {
                g.translate(0 - map.getMapWidth() * Constant.TILE_SIZE + Constant.FRAME_WIDTH, 0);
            }
            if (!lowerEdge) {
                if (characterCoordinates[1] > translateBoundDown) {
                    g.translate(0, 0 - (characterCoordinates[1] - translateBoundDown));
                }
            } else {
                g.translate(0, 0 - map.getMapHeight() * Constant.TILE_SIZE + Constant.FRAME_HEIGHT);
            }
            g.drawImage(backgroundImage, 0, 0,this);
        } else {
            g.drawImage(backgroundImage,
                    -((backgroundImage.getWidth() - Constant.FRAME_WIDTH) / (Constant.TILE_SIZE * 2)) * Constant.TILE_SIZE,
                    -((backgroundImage.getHeight() - Constant.FRAME_HEIGHT) / (Constant.TILE_SIZE * 2)) * Constant.TILE_SIZE,
                    this);
        }

        /** Affiche les objets de décor en arrière plan **/
        for (DecorObject decorObject : map.getDecorObjectArraylist()) {
            if (decorObject.getBackgroundImage() != null) {
                g.drawImage(decorObject.getBackgroundImage(), decorObject.getX() + decorObject.getBackgroundOffsetX(), decorObject.getY() + decorObject.getBackgroundOffsetY(), this);
            }
        }

        /** affiche les PNJ en arriere plan **/
        for (Pnj pnj : map.getPnjList()) {
            g.drawImage(pnj.stand(pnj.getDirection())[0], pnj.getCharacterCoordinates()[0] + offsetXChar, pnj.getCharacterCoordinates()[1] + offsetYChar, this);
        }

        /** Ombre du personnage **/
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(characterCoordinates[0], characterCoordinates[1] + 16, 32, 14);

        /** Mise à jour des mouvements du personnage **/
        if (isHiting) { // Attaque
            sprites = character.hit(direction.get(0), character.getRightHand());

            if (character.getRightHand() == RightHand.DAGGER || character.getRightHand() == RightHand.SWORD) {
                g.drawImage(sprites[hitSprite], characterCoordinates[0] + offsetXChar, characterCoordinates[1] + offsetYChar, this);

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
                g.drawImage(sprites[hitSprite], characterCoordinates[0] + offsetXChar, characterCoordinates[1] + offsetYChar, this);

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
            sprites = character.walkCycle(direction.get(0));
            if (direction.get(0).equals(KeyMap.LEFT) || direction.get(0).equals(KeyMap.RIGHT)) {
                g.drawImage(sprites[walkSpriteX], characterCoordinates[0] + offsetXChar, characterCoordinates[1] + offsetYChar, this);
            } else {
                g.drawImage(sprites[walkSpriteY], characterCoordinates[0] + offsetXChar, characterCoordinates[1] + offsetYChar, this);
            }
        } else { // Position debout
            g.drawImage(sprites[0], characterCoordinates[0] + offsetXChar, characterCoordinates[1] + offsetYChar, this);
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
                    -((foregroundImage.getWidth() - Constant.FRAME_WIDTH) / (Constant.TILE_SIZE * 2)) * Constant.TILE_SIZE,
                    -((foregroundImage.getHeight() - Constant.FRAME_HEIGHT) / (Constant.TILE_SIZE * 2)) * Constant.TILE_SIZE,
                    this);

        /** affiche les objet de decor au premier plan **/
        for (DecorObject decorObject : map.getDecorObjectArraylist()) {
            if (decorObject.getForegroundImage() != null) {
                g.drawImage(decorObject.getForegroundImage(), decorObject.getX() + decorObject.getForegroundOffsetX(), decorObject.getY() + decorObject.getForegroundOffsetY(), this);
            }
        }

        /** affiche les PNJ au premier plan **/
        for (Pnj pnj : map.getPnjList()) {

            if (pnj.getCharacterCoordinates()[1] > characterCoordinates[1]) {
                g.drawImage(pnj.stand(pnj.getDirection())[0], pnj.getCharacterCoordinates()[0] + offsetXChar, pnj.getCharacterCoordinates()[1] + offsetYChar, this);
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
//        /** met en surbrillance rouge les zone de collision **/
//        for (DecorObject object : map.getDecorObjectArraylist()) {
//            if (object instanceof CollectableItem) {
//                g.setColor(new Color(0, 0, 255, 100));
//                g.fillRect(object.getX(), object.getY(), Constant.TILE_SIZE, Constant.TILE_SIZE);
//            }
//        }
//
//        /** met en surbrillance violete la zone de collision du perso **/
//        g.setColor(new Color(255, 0, 255, 100));
//        g.fillRect(charBounds.x, charBounds.y, charBounds.width, charBounds.height);
//
//        /** met en surbrillance orange la zone d'action du personnage **/
//        g.setColor(new Color(255, 128, 0, 100));
//        g.fillRect(charActionArea.x, charActionArea.y, charActionArea.width, charActionArea.height);
    }

    /**
     * Action a effectuer lorsqu'une touche est pressée.
     *
     * @param keyCode
     */
    public void onKeyPressed(int keyCode) throws IOException {
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
        if (keyCode == KeyMap.ATTACK && !character.getRightHand().toString().equals("NONE")) {
            new Player(Sounds.ATTACK, false);
            isHiting = true;
        }
        if (keyCode == KeyMap.ESCAPE) {
            setPause(true);
            new GameMenu();
        }
        if (keyCode == KeyMap.STOP) {
            setStopDebug(!isDebugStopped());
            new Player(Sounds.GENERIC_START, true);
        }
        if (keyCode == KeyMap.ACTION) {
            isActing = true;
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
        if (keyCode == KeyMap.ACTION) {
            isActing = false;
        }
    }

    /**
     * Modifie les coordonnées du personnage en fonction de sa direction de déplacement et de la longueur de son pas (Constant.STRIDE).
     */
    public void setCharCoordinates() {
        // Utilisation du setter static conseillée pour la mise à jour des coordonnées en private static
        if (isMovingRight) {
            Rectangle test = new Rectangle(characterCoordinates[0] + Constant.STRIDE, characterCoordinates[1], Constant.TILE_SIZE, Constant.TILE_SIZE);
            if (!collisionChecker(test)) {
                setCharacterCoordinates(new Integer[] { getCharacterCoordinates()[0] += Constant.STRIDE, getCharacterCoordinates()[1] });
            }
        }
        if (isMovingLeft) {
            Rectangle test = new Rectangle(characterCoordinates[0] - Constant.STRIDE, characterCoordinates[1], Constant.TILE_SIZE, Constant.TILE_SIZE);
            if (!collisionChecker(test)) {
                setCharacterCoordinates(new Integer[] { getCharacterCoordinates()[0] -= Constant.STRIDE, getCharacterCoordinates()[1] });
            }
        }
        if (isMovingUp) {
            Rectangle test = new Rectangle(characterCoordinates[0], characterCoordinates[1] - Constant.STRIDE, Constant.TILE_SIZE, Constant.TILE_SIZE);
            if (!collisionChecker(test)) {
                setCharacterCoordinates(new Integer[] { getCharacterCoordinates()[0], getCharacterCoordinates()[1] -= Constant.STRIDE });
            }
        }
        if (isMovingDown) {
            Rectangle test = new Rectangle(characterCoordinates[0], characterCoordinates[1] + Constant.STRIDE, Constant.TILE_SIZE, Constant.TILE_SIZE);
            if (!collisionChecker(test)) {
                setCharacterCoordinates(new Integer[] { getCharacterCoordinates()[0], getCharacterCoordinates()[1] += Constant.STRIDE });
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

        for (Rectangle block : map.getHitboxList()) {
            if (rectangle.intersects(block)) {
                collision = true;
                break;
            }
        }
        return collision;
    }

    /**
     * Teste si le personnage entre sur une case de téléportation de la map.
     */
    public void teleportChecker() throws IOException {
        Rectangle charCenter = new Rectangle(charBounds.x + 15, charBounds.y + 15, 2, 2);
        for (Tile tile : map.getTeleportList()) {
            if (charCenter.intersects(tile.getTeleportBounds())) {
                music.stop();
                String teleportMap = tile.getBindedTile().split(" ")[0];
                setCharacterCoordinates(new Integer[]{
                        Integer.parseInt(tile.getBindedTile().split(" ")[1].split(",")[0]) * Constant.TILE_SIZE,
                        Integer.parseInt(tile.getBindedTile().split(" ")[1].split(",")[1]) * Constant.TILE_SIZE
                });
                MainZeldo.setGameState(MainZeldo.GameState.valueOf(teleportMap));
                MainZeldo.setGameStateChange(true);
                Thread.currentThread().stop();
            }
        }
    }

    public void getActionArea() {
        Integer actionWidth = 10;

        if (direction.get(0).equals(KeyMap.LEFT))
            charActionArea = new Rectangle(charBounds.x - actionWidth, charBounds.y + 11, actionWidth, 10);
        else if (direction.get(0).equals(KeyMap.UP))
            charActionArea = new Rectangle(charBounds.x  + 12, charBounds.y - actionWidth, 10, actionWidth);
        else if (direction.get(0).equals(KeyMap.RIGHT))
            charActionArea = new Rectangle(charBounds.x + charBounds.width, charBounds.y + 11, actionWidth, 10);
        else if (direction.get(0).equals(KeyMap.DOWN))
            charActionArea = new Rectangle(charBounds.x + 12, charBounds.y + charBounds.height, 10, actionWidth);
    }


    /**
     * Teste si l'action du personnage entre en collision avec un des blocs d'interaction de la map.
     */
    public void hitChecker(Rectangle rectangle) {
        for (Pnj pnj : map.getPnjList()) {
            if (rectangle.intersects(pnj.getHitbox())) {
                logger.info("Je parle au pnj " + pnj.getName());
            }
        }

        ArrayList<DecorObject> objectToRemove = new ArrayList<>();
        ArrayList<DecorObject> objectToAdd = new ArrayList<>();
        for (DecorObject decorObject : map.getDecorObjectArraylist()) {
            if (!(decorObject instanceof CollectableItem)) {
                if (rectangle.intersects(decorObject.getHitbox())) {
                    if (isHiting) {
                        if (decorObject instanceof Bush) {
                            objectToRemove.add(decorObject);
                        }
                    }
                    if (isActing) {
                        if (decorObject instanceof Chest) {
                            new Player(Sounds.CHEST, false);
                            objectToAdd.add(new Chest("open", decorObject.getX() / Constant.TILE_SIZE, decorObject.getY() / Constant.TILE_SIZE));
                            objectToRemove.add(decorObject);
                        }
                    }
                }
            }
            if (decorObject instanceof CollectableItem) {
                if (((CollectableItem) decorObject).getInteractionBox() != null) {
                    if (rectangle.intersects(((CollectableItem) decorObject).getInteractionBox())) {
                        logger.info("Je ramasse l'objet " + decorObject.getName());
                        objectToRemove.add(decorObject);
                        character.itemList.add((CollectableItem)decorObject);
                    }
                }
            }
        }

        for (DecorObject decorObject : objectToRemove)
            map.getDecorObjectArraylist().remove(decorObject);
        for (DecorObject decorObject : objectToAdd)
            map.getDecorObjectArraylist().add(decorObject);

    }
}
