package com.mesi.equipement;

import com.mesi.resources.Images;

import java.awt.image.BufferedImage;

/**
 * Équipement de protection de mains du héros.
 */
public enum Hands {

    NONE(null),
    METAL_GLOVES(Images.SP_GLOVES_METAL);

    private final BufferedImage image;

    /**********  Constructors  **********/

    Hands(BufferedImage image) {
        this.image = image;
    }

    /**********  Methods  **********/

    public BufferedImage getImage() {
        return this.image;
    }
}