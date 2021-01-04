package com.mesi.animation;

import com.mesi.equipement.*;
import com.mesi.params.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Animation {

    /**********  Attributes  **********/

    private String fileName;
    private Integer firstRowIndex, lastRowIndex, firstColIndex, lastColIndex;
    private Hair hair;
    private Head head;
    private Torso torso;
    private Hands hands;
    private Legs legs;
    private Feet feet;
    private RightHand rightHand;
    private LeftHand leftHand;
    private BufferedImage newCharacter;

    /**********  Constructors  **********/

    public Animation(Hair hair, Head head, Torso torso, Hands hands, Legs legs, Feet feet, RightHand rightHand, LeftHand leftHand) {
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

    public RightHand getRightHand() {
        return rightHand;
    }

    /**********  Methods  **********/

    /**
     * Création de l'image du personnage principal.
     * Récupère les images du personnage et de son équipement en fonction des paramètres de la signatures du personnage, et les
     * fusionne pour obtenir l'image finale.
     * @return BufferedImage.
     * @throws IOException
     */
    public BufferedImage createNewCharacter() throws IOException {
        BufferedImage character = ImageIO.read(new File(fileName));
        BufferedImage charHair = (hair.getImageURL() != null && (head != head.METAL_HELMET && head != head.CHAIN_HOOD)) ? ImageIO.read(new File(hair.getImageURL())) : null;
        BufferedImage charHead = (head.getImageURL() != null) ? ImageIO.read(new File(head.getImageURL())) : null;
        BufferedImage charTorso = (torso.getImageURL() != null) ? ImageIO.read(new File(torso.getImageURL())) : null;
        BufferedImage charHands = (hands.getImageURL() != null) ? ImageIO.read(new File(hands.getImageURL())) : null;
        BufferedImage charLegs = (legs.getImageURL() != null) ? ImageIO.read(new File(legs.getImageURL())) : null;
        BufferedImage charFeet = (feet.getImageURL() != null) ? ImageIO.read(new File(feet.getImageURL())) : null;
        BufferedImage charRightHand = (rightHand.getImageURL() != null) ? ImageIO.read(new File(rightHand.getImageURL())) : null;
        BufferedImage charLeftHand = (leftHand.getImageURL() != null) ? (head != head.METAL_HAT) ? ImageIO.read(new File(leftHand.getImageURL())) : ImageIO.read(new File(leftHand.getImageURL2())) : null;
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
        return this.newCharacter = newCharacter;
    }

    /**
     * Récupère les images du mouvement parmis la table de sprites en fonction des paramètres.
     * @return BufferedImage[]
     * @throws IOException
     */
    public BufferedImage[] loadSprites() throws IOException {
        int index = 0;
        BufferedImage[] sprites = new BufferedImage[(lastRowIndex - firstRowIndex + 1) * (lastColIndex - firstColIndex + 1)];

        for (int i = firstRowIndex; i <= lastRowIndex; i++) {
            for (int j = firstColIndex; j <= lastColIndex; j++) {
                sprites[index] = newCharacter.getSubimage(j * Constant.SPRITE_SIZE, i * Constant.SPRITE_SIZE, Constant.SPRITE_SIZE, Constant.SPRITE_SIZE);
                index++;
            }
        }
        return sprites;
    }

    /**
     * Calcul les coordonnées des images à récupérer dans la tables de sprites.
     * @param firstRowIndex
     * @param lastRowIndex
     * @param firstColIndex
     * @param lastColIndex
     */
    public void setSpritesCoordinates(Integer firstRowIndex, Integer lastRowIndex, Integer firstColIndex, Integer lastColIndex) {
        this.firstRowIndex = firstRowIndex;
        this.lastRowIndex = lastRowIndex;
        this.firstColIndex = firstColIndex;
        this.lastColIndex = lastColIndex;
    }

    /**
     * Récupère les sprites lorsque le personnage est debout.
     * @param direction
     * @return BufferedImage[]
     * @throws IOException
     */
    public BufferedImage[] stand(int direction) throws IOException {
        switch (direction) {
            case 37: // left
                setSpritesCoordinates(9, 9, 0, 0);
                break;
            case 38: // up
                setSpritesCoordinates(8, 8, 0, 0);
                break;
            case 39: // right
                setSpritesCoordinates(11, 11, 0, 0);
                break;
            default: // down
                setSpritesCoordinates(10, 10, 0, 0);
        }
        return this.loadSprites();
    }

    /**
     * Récupère les sprites lorsque le personnage marche.
     * @param direction
     * @return BufferedImage[]
     * @throws IOException
     */
    public BufferedImage[] walkCycle(int direction) throws IOException {
        switch (direction) {
            case 37: // left
                setSpritesCoordinates(9, 9, 0, 8);
                break;
            case 38: // up
                setSpritesCoordinates(8, 8, 0, 8);
                break;
            case 39: // right
                setSpritesCoordinates(11, 11, 0, 8);
                break;
            default: // down
                setSpritesCoordinates(10, 10, 0, 8);
        }
        return this.loadSprites();
    }

    /**
     * Récupère les sprites lorsque le personnage attaque.
     * @param direction
     * @param weapon
     * @return BufferedImage[]
     * @throws IOException
     */
    public BufferedImage[] hit(int direction, RightHand weapon) throws IOException {
        if (weapon == RightHand.DAGGER || weapon == RightHand.SWORD) {
            switch (direction) {
                case 37: // left
                    setSpritesCoordinates(13, 13, 0, 5);
                    break;
                case 38: // up
                    setSpritesCoordinates(12, 12, 0, 5);
                    break;
                case 39: // right
                    setSpritesCoordinates(15, 15, 0, 5);
                    break;
                default: // down
                    setSpritesCoordinates(14, 14, 0, 5);
            }
            return this.loadSprites();
        } else /*if (weapon == RightHand.SPEAR) */{
            switch (direction) {
                case 37: // left
                    setSpritesCoordinates(5, 5, 0, 7);
                    break;
                case 38: // up
                    setSpritesCoordinates(4, 4, 0, 7);
                    break;
                case 39: // right
                    setSpritesCoordinates(7, 7, 0, 7);
                    break;
                default: // down
                    setSpritesCoordinates(6, 6, 0, 7);
            }
            return this.loadSprites();
        }
    }
}
