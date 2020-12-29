package com.mesi.equipement;

public enum Torso {

    NONE(null),
    METAL_ARMOR("res/images/torso-armor_metal.png"),
    CHAIN_ARMOR("res/images/torso-armor_chain.png"),
    LEATHER_ARMOR("res/images/torso-armor_leather.png"),
    TSHIRT("res/images/torso-tshirt.png");

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