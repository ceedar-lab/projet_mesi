package com.mesi.equipement;

/**
 * Équipement de protection de tête du héros.
 */
public enum Head {

    NONE(null),
    METAL_HELMET("src/main/resources/images/sprites/head-helmet_metal.png"),
    METAL_HAT("src/main/resources/images/sprites/head-hat_metal.png"),
    LEATHER_HAT("src/main/resources/images/sprites/head-hat_leather.png"),
    CHAIN_HOOD("src/main/resources/images/sprites/head-hood_chain.png"),
    ROBE_HOOD("src/main/resources/images/sprites/head-hood_robe.png");

    private final String imageURL;

    /**********  Constructors  **********/

    Head(String imageURL) {
        this.imageURL = imageURL;
    }

    /**********  Methods  **********/

    public String getImageURL() {
        return this.imageURL;
    }
}