package com.mesi.animation;

import com.mesi.equipement.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BrownCharacterAnimation extends Animation {

    /**********  Constructors  **********/

    public BrownCharacterAnimation(Hair hair, Head head, Torso torso, Hands hands, Legs legs, Feet feet, RightHand rightHand, LeftHand leftHand) throws IOException {
        super(hair, head, torso, hands, legs, feet, rightHand, leftHand);
        setFileName("res/images/character-brown_skin.png");
        super.createNewCharacter();
    }

    /**********  Methods  **********/

    @Override
    public BufferedImage[] stand(int direction) throws IOException {
        return super.stand(direction);
    }

    @Override
    public BufferedImage[] walkCycle(int direction) throws IOException {
        return super.walkCycle(direction);
    }
}
