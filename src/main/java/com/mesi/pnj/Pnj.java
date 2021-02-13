package com.mesi.pnj;

import com.mesi.animation.Animation;
import com.mesi.equipement.*;

import java.awt.*;
import java.io.IOException;

public class Pnj extends Animation {

    private String name;
    private Integer[] characterCoordinates;
    private Integer direction;
    private Rectangle hitbox;

    /**********  Constructors
     * @param hair
     * @param head
     * @param torso
     * @param hands
     * @param legs
     * @param feet
     * @param rightHand
     * @param leftHand**********/
    public Pnj(Hair hair, Head head, Torso torso, Hands hands, Legs legs, Feet feet, RightHand rightHand, LeftHand leftHand, String filename) throws IOException {
        super(hair, head, torso, hands, legs, feet, rightHand, leftHand);
        setFileName(filename);
        super.createNewCharacter();
    }


    public Integer[] getCharacterCoordinates() {
        return characterCoordinates;
    }

    public void setCharacterCoordinates(Integer[] characterCoordinates) {
        this.characterCoordinates = characterCoordinates;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Rectangle getHitbox() {
        return new Rectangle(characterCoordinates[0] + hitbox.x, characterCoordinates[1] + hitbox.y, hitbox.width, hitbox.height);
    }

    public void setHitbox(Rectangle rectangle) {
        this.hitbox = rectangle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
