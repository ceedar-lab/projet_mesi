package com.mesi.equipement;

import com.mesi.resources.Images;

import java.awt.image.BufferedImage;

/**
 * Couleur de cheveux du héros du héros.
 */
public enum Hair {

    NONE(null),
    BLACK(Images.SP_HAIR_BLACK),
    BROWN(Images.SP_HAIR_BROWN),
    BLOND(Images.SP_HAIR_BLOND);

    private final BufferedImage image;

    /**********  Constructors  **********/

    Hair(BufferedImage image) {
        this.image = image;
    }

    /**********  Methods  **********/

    public BufferedImage getImage() {
        return this.image;
    }
}
