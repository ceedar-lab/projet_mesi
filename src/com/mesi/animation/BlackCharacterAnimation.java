package com.mesi.animation;

import com.mesi.equipement.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BlackCharacterAnimation extends Animation {

    /**********  Constructors  **********/

    public BlackCharacterAnimation(Hair hair, Head head, Torso torso, Hands hands, Legs legs, Feet feet, LeftHand leftHand) throws IOException {
        super(hair, head, torso, hands, legs, feet, leftHand);
        setFileName("res/images/character-black_skin.png");
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