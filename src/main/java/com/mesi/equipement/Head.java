package com.mesi.equipement;

import com.mesi.resources.Images;

import java.awt.image.BufferedImage;

/**
 * Équipement de protection de tête du héros.
 */
public enum Head {

    NONE(null),
    METAL_HELMET(Images.SP_HELMET_METAL),
    METAL_HAT(Images.SP_HAT_METAL),
    LEATHER_HAT(Images.SP_HAT_LEATHER),
    CHAIN_HOOD(Images.SP_HOOD_CHAIN),
    ROBE_HOOD(Images.SP_HOOD_ROBE);

    private final BufferedImage image;

    /**********  Constructors  **********/

    Head(BufferedImage image) {
        this.image = image;
    }

    /**********  Methods  **********/

    public BufferedImage getImage() {
        return this.image;
    }
}