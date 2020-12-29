package com.mesi.equipement;

public enum Head {

    NONE(null),
    METAL_HELMET("res/images/head-helmet_metal.png"),
    METAL_HAT("res/images/head-hat_metal.png"),
    LEATHER_HAT("res/images/head-hat_leather.png"),
    CHAIN_HOOD("res/images/head-hood_chain.png"),
    ROBE_HOOD("res/images/head-hood_robe.png");

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