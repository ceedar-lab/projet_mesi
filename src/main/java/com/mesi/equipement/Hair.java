package com.mesi.equipement;

/**
 * Couleur de cheveux du héros du héros.
 */
public enum Hair {

    NONE(null),
    BLACK("src/main/resources/images/sprites/hair-black.png"),
    BROWN("src/main/resources/images/sprites/hair-brown.png"),
    BLOND("src/main/resources/images/sprites/hair-blond.png");

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
