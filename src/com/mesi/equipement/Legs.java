package com.mesi.equipement;

public enum Legs {
    NONE(null),
    METAL_PANTS("res/images/legs-pants_metal.png"),
    LEATHER_PANTS("res/images/legs-pants_leather.png"),
    SKIRT("res/images/legs-skirt.png");

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