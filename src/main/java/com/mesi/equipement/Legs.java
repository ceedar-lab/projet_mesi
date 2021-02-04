package com.mesi.equipement;

/**
 * Équipement de protection de jambes du héros.
 */
public enum Legs {
    NONE(null),
    METAL_PANTS("src/main/resources/images/sprites/legs-pants_metal.png"),
    LEATHER_PANTS("src/main/resources/images/sprites/legs-pants_leather.png"),
    SKIRT("src/main/resources/images/sprites/legs-skirt.png");

    private final String imageURL;

    /**********  Constructors  **********/

    Legs(String imageURL) {
        this.imageURL = imageURL;
    }

    /**********  Methods  **********/

    public String getImageURL() {
        return this.imageURL;
    }
}