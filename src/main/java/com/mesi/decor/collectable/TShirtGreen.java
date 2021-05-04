package com.mesi.decor.collectable;

import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.resources.Images;

public class TShirtGreen extends CollectableItem {

    /**********  Constructors  **********/

    public TShirtGreen() {
        foregroundOffsetX = 0;
        foregroundOffsetY = 0;
        backgroundOffsetX = 0;
        backgroundOffsetY = 0;

        category = "armor";

        setBackgroundImage(Images.TSHIRT_GREEN);

        setInteractionBox(Hitbox.FULL);
    }

    public TShirtGreen(Integer tileX, Integer tileY, Integer quantity) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
        this.quantity = quantity;
    }

}
