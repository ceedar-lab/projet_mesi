package com.mesi.decor.collectable;

import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.resources.Images;

public class PantsBlue extends CollectableItem {

    /**********  Constructors  **********/

    public PantsBlue() {
        foregroundOffsetX = 0;
        foregroundOffsetY = 0;
        backgroundOffsetX = 0;
        backgroundOffsetY = 0;

        category = "armor";

        setBackgroundImage(Images.PANTS_BLUE);

        setInteractionBox(Hitbox.FULL);
    }

    public PantsBlue(Integer tileX, Integer tileY, Integer quantity) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
        this.quantity = quantity;
    }
}
