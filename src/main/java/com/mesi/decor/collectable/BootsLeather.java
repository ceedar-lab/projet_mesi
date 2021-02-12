package com.mesi.decor.collectable;

import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.params.Images;

public class BootsLeather extends CollectableItem {

    public BootsLeather() {
        foregroundOffsetX = 0;
        foregroundOffsetY = 0;
        backgroundOffsetX = 0;
        backgroundOffsetY = 0;
        setName("Bottes");

        setBackgroundImage(Images.BOOTS_LEATHER);

        setInteractionBox(Hitbox.FULL);

    }

    public BootsLeather(Integer tileX, Integer tileY) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
    }
}
