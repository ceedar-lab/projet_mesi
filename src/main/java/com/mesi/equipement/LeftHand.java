package com.mesi.equipement;

/**
 * Équipement dans la main gauche du héros (bouclier).
 */
public enum LeftHand {

    NONE(null, null),
    SHIELD("src/main/resources/images/left_hand-shield.png", "src/main/resources/images/left_hand-shield_cut.png");

    private final String imageURL;
    private final String imageURL2;

    /**********  Constructors  **********/

    LeftHand(String imageURL, String imageURL2) {
        this.imageURL = imageURL;
        this.imageURL2 = imageURL2;
    }

    /**********  Methods  **********/

    public String getImageURL() {
        return this.imageURL;
    }

    public String getImageURL2() {
        return this.imageURL2;
    }
}