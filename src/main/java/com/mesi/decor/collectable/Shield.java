package com.mesi.decor.collectable;

import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.params.Images;

public class Shield extends CollectableItem {

    public Shield() {
        foregroundOffsetX = 0;
        foregroundOffsetY = 0;
        backgroundOffsetX = 0;
        backgroundOffsetY = 0;
        setName("Bouclier");

        setBackgroundImage(Images.SHIELD);

        setInteractionBox(Hitbox.FULL);

    }

    public Shield(Integer tileX, Integer tileY) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
    }
}
