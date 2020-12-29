package com.mesi.equipement;

public enum Hair {

    NONE(null),
    BLACK("res/images/hair-black.png"),
    BROWN("res/images/hair-brown.png"),
    BLOND("res/images/hair-blond.png");

    private final String imageURL;

    /**********  Constructors  **********/

    Hair(String imageURL) {
        this.imageURL = imageURL;
    }

    /**********  Methods  **********/

    public String getImageURL() {
        return this.imageURL;
    }
}
