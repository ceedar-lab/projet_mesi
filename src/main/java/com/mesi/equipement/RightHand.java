package com.mesi.equipement;

/**
 * Arme dans la main droite du héros.
 */
public enum RightHand {

    NONE(null),
    DAGGER("src/main/resources/images/sprites/right_hand-dagger.png"),
    SWORD("src/main/resources/images/sprites/right_hand-sword.png"),
    SPEAR("src/main/resources/images/sprites/right_hand-spear.png");

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