package com.mesi.equipement;

public enum Feet {

    NONE(null),
    METAL_BOOTS("res/images/feet-boots_metal.png"),
    LEATHER_BOOTS("res/images/feet-boots_leather.png");

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