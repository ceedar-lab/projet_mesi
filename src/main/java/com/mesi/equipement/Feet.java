package com.mesi.equipement;

/**
 * Équipement de protection de pieds du héros.
 */
public enum Feet {

    NONE(null),
    METAL_BOOTS("src/main/resources/images/feet-boots_metal.png"),
    LEATHER_BOOTS("src/main/resources/images/feet-boots_leather.png");

    private final String imageURL;

    /**********  Constructors  **********/

    Feet(String imageURL) {
        this.imageURL = imageURL;
    }

    /**********  Methods  **********/

    public String getImageURL() {
        return this.imageURL;
    }
}