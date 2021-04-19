package com.mesi.decor.collectable;

import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.resources.Images;

public class Shield extends CollectableItem {

    public Shield() {
        foregroundOffsetX = 0;
        foregroundOffsetY = 0;
        backgroundOffsetX = 0;
        backgroundOffsetY = 0;
        setName("bouclier");

        setBackgroundImage(Images.SHIELD);

        setInteractionBox(Hitbox.FULL);

    }

    public Shield(Integer tileX, Integer tileY, Integer quantity) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
        this.quantity = quantity;
    }
}
