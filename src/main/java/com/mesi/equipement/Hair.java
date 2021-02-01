package com.mesi.equipement;

/**
 * Couleur de cheveux du héros du héros.
 */
public enum Hair {

    NONE(null),
    BLACK("src/main/resources/images/hair-black.png"),
    BROWN("src/main/resources/images/hair-brown.png"),
    BLOND("src/main/resources/images/hair-blond.png");

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
