package com.mesi.equipement;

/**
 * Équipement de protection de corps du héros.
 */
public enum Torso {

    NONE(null),
    METAL_ARMOR("src/main/resources/images/sprites/torso-armor_metal.png"),
    CHAIN_ARMOR("src/main/resources/images/sprites/torso-armor_chain.png"),
    LEATHER_ARMOR("src/main/resources/images/sprites/torso-armor_leather.png"),
    TSHIRT("src/main/resources/images/sprites/torso-tshirt.png");

    private final String imageURL;

    /**********  Constructors  **********/

    Torso(String imageURL) {
        this.imageURL = imageURL;
    }

    /**********  Methods  **********/

    public String getImageURL() {
        return this.imageURL;
    }
}