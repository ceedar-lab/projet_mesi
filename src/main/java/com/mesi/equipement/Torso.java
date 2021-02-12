package com.mesi.equipement;

import com.mesi.params.Images;

import java.awt.image.BufferedImage;

/**
 * Équipement de protection de corps du héros.
 */
public enum Torso {

    NONE(null),
    METAL_ARMOR(Images.SP_ARMOR_METAL),
    CHAIN_ARMOR(Images.SP_ARMOR_CHAIN),
    LEATHER_ARMOR(Images.SP_ARMOR_LEATHER),
    TSHIRT(Images.SP_TSHIRT);

    private final BufferedImage image;

    /**********  Constructors  **********/

    Torso(BufferedImage image) {
        this.image = image;
    }

    /**********  Methods  **********/

    public BufferedImage getImage() {
        return this.image;
    }
}