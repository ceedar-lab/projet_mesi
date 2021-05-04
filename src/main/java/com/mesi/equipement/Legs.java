package com.mesi.equipement;

import com.mesi.resources.Images;

import java.awt.image.BufferedImage;

/**
 * Équipement de protection de jambes du héros.
 */
public enum Legs {
    NONE(null),
    BLUE_PANTS(Images.SP_PANTS_BLUE),
    METAL_PANTS(Images.SP_PANTS_METAL),
    LEATHER_PANTS(Images.SP_PANTS_LEATHER),
    SKIRT(Images.SP_SKIRT);

    private final BufferedImage image;

    /**********  Constructors  **********/

    Legs(BufferedImage image) {
        this.image = image;
    }

    /**********  Methods  **********/

    public BufferedImage getImage() {
        return this.image;
    }
}