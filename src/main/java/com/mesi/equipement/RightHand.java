package com.mesi.equipement;

import com.mesi.resources.Images;

import java.awt.image.BufferedImage;

/**
 * Arme dans la main droite du héros.
 */
public enum RightHand {

    NONE(null),
    DAGGER(Images.SP_DAGGER),
    SWORD(Images.SP_SWORD),
    SPEAR(Images.SP_SPEAR);

    private final BufferedImage image;

    /**********  Constructors  **********/

    RightHand(BufferedImage image) {
        this.image = image;
    }

    /**********  Methods  **********/

    public BufferedImage getImage() {
        return this.image;
    }
}