package com.mesi.animation;

import com.mesi.equipement.*;
import com.mesi.params.Constant;
import com.mesi.params.KeyMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Animation {

    /**********  Attributes  **********/

    private String fileName;
    private Hair hair;
    private Head head;
    private Torso torso;
    private Hands hands;
    private Legs legs;
    private Feet feet;
    private RightHand rightHand;
    private LeftHand leftHand;
    private BufferedImage characterImage;

    /**********  Constructors  **********/

    protected Animation(Hair hair, Head head, Torso torso, Hands hands, Legs legs, Feet feet, RightHand rightHand, LeftHand leftHand) {
        this.hair = hair;
        this.head = head;
        this.torso = torso;
        this.hands = hands;
        this.legs = legs;
        this.feet = feet;
        this.rightHand = rightHand;
        this.leftHand = leftHand;
    }

    /**********  Getters / Setters  **********/

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Hair getHair() {
        return hair;
    }
    public void setHair(Hair hair) {
        this.hair = hair;
    }
    public Head getHead() {
        return head;
    }
    public void setHead(Head head) {
        this.head = head;
    }
    public Torso getTorso() {
        return torso;
    }
    public void setTorso(Torso torso) {
        this.torso = torso;
    }
    public Hands getHands() {
        return hands;
    }
    public void setHands(Hands hands) {
        this.hands = hands;
    }
    public Legs getLegs() {
        return legs;
    }
    public void setLegs(Legs legs) {
        this.legs = legs;
    }
    public Feet getFeet() {
        return feet;
    }
    public void setFeet(Feet feet) {
        this.feet = feet;
    }
    public RightHand getRightHand() {
        return rightHand;
    }
    public void setRightHand(RightHand rightHand) {
        this.rightHand = rightHand;
    }
    public LeftHand getLeftHand() {
        return leftHand;
    }
    public void setLeftHand(LeftHand leftHand) {
        this.leftHand = leftHand;
    }

    /**********  Methods  **********/

    /**
     * Création de l'image du personnage principal.
     * Récupère les images du personnage et de son équipement en fonction des paramètres de la signatures du personnage, et les
     * fusionne pour obtenir l'image finale.
     *
     * @return BufferedImage.
     * @throws IOException
     */
    public void createNewCharacter() throws IOException {
        BufferedImage character = ImageIO.read(new File(fileName));
        BufferedImage charHair = hair != Hair.NONE && (head != Head.METAL_HELMET && head != Head.CHAIN_HOOD) ? hair.getImage() : null;
        BufferedImage charHead = head != Head.NONE ? head.getImage() : null;
        BufferedImage charTorso = torso != Torso.NONE ? torso.getImage() : null;
        BufferedImage charHands = hands != Hands.NONE ? hands.getImage() : null;
        BufferedImage charLegs = legs != Legs.NONE ? legs.getImage() : null;
        BufferedImage charFeet = feet != Feet.NONE ? feet.getImage() : null;
        BufferedImage charRightHand = rightHand != RightHand.NONE ? rightHand.getImage() : null;
        BufferedImage charLeftHand = leftHand != LeftHand.NONE ? (head != Head.METAL_HAT) ? leftHand.getImage() : leftHand.getImage2() : null;
        int width = 832;
        int height = 1344;
        BufferedImage newCharacter = new BufferedImage(width, height, 2);
        Graphics2D g = newCharacter.createGraphics();
        g.setPaint(new Color(0, 0, 0, 1));
        g.fillRect(0, 0, width, height);
        g.drawImage(character, null, 0, 0);
        g.drawImage(charHair, null, 0, 0);
        g.drawImage(charHead, null, 0, 0);
        g.drawImage(charTorso, null, 0, 0);
        g.drawImage(charHands, null, 0, 0);
        g.drawImage(charLegs, null, 0, 0);
        g.drawImage(charFeet, null, 0, 0);
        g.drawImage(charRightHand, null, 0, 0);
        g.drawImage(charLeftHand, null, 0, 0);
        this.characterImage = newCharacter;
    }

    /**
     * Récupère les images du mouvement parmi la table de sprites en fonction des paramètres.
     *
     * @return BufferedImage[]
     * @throws IOException
     */
    public BufferedImage[] loadSprites(Integer firstRowIndex, Integer lastRowIndex, Integer firstColIndex, Integer lastColIndex) {
        int index = 0;
        BufferedImage[] sprites = new BufferedImage[(lastRowIndex - firstRowIndex + 1) * (lastColIndex - firstColIndex + 1)];

        for (int i = firstRowIndex; i <= lastRowIndex; i++) {
            for (int j = firstColIndex; j <= lastColIndex; j++) {
                sprites[index] = characterImage.getSubimage(j * Constant.SPRITE_SIZE, i * Constant.SPRITE_SIZE, Constant.SPRITE_SIZE, Constant.SPRITE_SIZE);
                index++;
            }
        }
        return sprites;
    }

    /**
     * Récupère les sprites lorsque le personnage est debout.
     *
     * @param direction
     * @return BufferedImage[]
     * @throws IOException
     */
    public BufferedImage[] stand(int direction) throws IOException {
        if (direction == KeyMap.LEFT)
            return loadSprites(9, 9, 0, 0);
        if (direction == KeyMap.UP)
            return loadSprites(8, 8, 0, 0);
        if (direction == KeyMap.RIGHT)
            return loadSprites(11, 11, 0, 0);
        // Si direction == KeyMap.DOWN
        return loadSprites(10, 10, 0, 0);
    }

    /**
     * Récupère les sprites lorsque le personnage marche.
     *
     * @param direction
     * @return BufferedImage[]
     * @throws IOException
     */
    public BufferedImage[] walkCycle(int direction) throws IOException {
        if (direction == KeyMap.LEFT)
            return loadSprites(9, 9, 0, 8);
        if (direction == KeyMap.UP)
            return loadSprites(8, 8, 0, 8);
        if (direction == KeyMap.RIGHT)
            return loadSprites(11, 11, 0, 8);
        // Si direction == KeyMap.DOWN
        return loadSprites(10, 10, 0, 8);
    }

    /**
     * Récupère les sprites lorsque le personnage attaque.
     *
     * @param direction
     * @param weapon
     * @return BufferedImage[]
     * @throws IOException
     */
    public BufferedImage[] hit(int direction, RightHand weapon) throws IOException {
        if (weapon == RightHand.DAGGER || weapon == RightHand.SWORD) {
            if (direction == KeyMap.LEFT)
                return loadSprites(13, 13, 0, 5);
            if (direction == KeyMap.UP)
                return loadSprites(12, 12, 0, 5);
            if (direction == KeyMap.RIGHT)
                return loadSprites(15, 15, 0, 5);
            // Si direction == KeyMap.DOWN
            return loadSprites(14, 14, 0, 5);
        } else { // Si l'arme est une lance
            if (direction == KeyMap.LEFT)
                return loadSprites(5, 5, 0, 7);
            if (direction == KeyMap.UP)
                return loadSprites(4, 4, 0, 7);
            if (direction == KeyMap.RIGHT)
                return loadSprites(7, 7, 0, 7);
            // Si direction == KeyMap.DOWN
            return loadSprites(6, 6, 0, 7);
        }
    }
}
