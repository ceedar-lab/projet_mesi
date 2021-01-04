package com.mesi.equipement;

/**
 * Équipement de protection de mains du héros.
 */
public enum Hands {

    NONE(null),
    METAL_GLOVES("res/images/hands-gloves_metal.png");

    private final String imageURL;

    /**********  Constructors  **********/

    Hands(String imageURL) {
        this.imageURL = imageURL;
    }

    /**********  Methods  **********/

    public String getImageURL() {
        return this.imageURL;
    }
}