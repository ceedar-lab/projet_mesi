package com.mesi.equipement;

import com.mesi.resources.Images;

import java.awt.image.BufferedImage;

/**
 * Équipement dans la main gauche du héros (bouclier).
 */
public enum LeftHand {

    NONE(null, null),
    SHIELD(Images.SP_SHIELD, Images.SP_SHIELD_CUT);

    private final BufferedImage image;
    private final BufferedImage image2;

    /**********  Constructors  **********/

    LeftHand(BufferedImage image, BufferedImage image2) {
        this.image = image;
        this.image2 = image2;
    }

    /**********  Methods  **********/

    public BufferedImage getImage() {
        return this.image;
    }

    public BufferedImage getImage2() {
        return this.image2;
    }
}