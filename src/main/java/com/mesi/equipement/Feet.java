package com.mesi.equipement;

import com.mesi.params.Images;

import java.awt.image.BufferedImage;

/**
 * Équipement de protection de pieds du héros.
 */
public enum Feet {

    NONE(null),
    METAL_BOOTS(Images.SP_BOOTS_METAL),
    LEATHER_BOOTS(Images.SP_BOOTS_LEATHER);

    private final BufferedImage image;

    /**********  Constructors  **********/

    Feet(BufferedImage image) {
        this.image = image;
    }

    /**********  Methods  **********/

    public BufferedImage getImage() {
        return this.image;
    }
}