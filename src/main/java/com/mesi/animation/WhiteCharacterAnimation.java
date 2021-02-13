package com.mesi.animation;

import com.mesi.equipement.*;

import java.io.IOException;

public class WhiteCharacterAnimation extends Animation {

    /**********  Constructors  **********/

    /**
     * Création d'un héros blanc et de son équipement.
     *
     * @throws IOException
     */
    public WhiteCharacterAnimation(Hair hair, Head head, Torso torso, Hands hands, Legs legs, Feet feet, RightHand rightHand, LeftHand leftHand) throws IOException {
        super(hair, head, torso, hands, legs, feet, rightHand, leftHand);
        setFileName("src/main/resources/images/sprites/character-white_skin.png");
        super.createNewCharacter();
    }
}
