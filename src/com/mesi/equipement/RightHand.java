package com.mesi.equipement;

/**
 * Arme dans la main droite du héros.
 */
public enum RightHand {

    NONE(null),
    DAGGER("res/images/right_hand-dagger.png"),
    SWORD("res/images/right_hand-sword.png"),
    SPEAR("res/images/right_hand-spear.png");

    private final String imageURL;

    /**********  Constructors  **********/

    RightHand(String imageURL) {
        this.imageURL = imageURL;
    }

    /**********  Methods  **********/

    public String getImageURL() {
        return this.imageURL;
    }
}