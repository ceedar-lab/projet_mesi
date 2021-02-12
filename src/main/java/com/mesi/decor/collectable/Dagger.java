package com.mesi.decor.collectable;

import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.params.Images;

public class Dagger extends CollectableItem {

    public Dagger() {
        foregroundOffsetX = 0;
        foregroundOffsetY = 0;
        backgroundOffsetX = 0;
        backgroundOffsetY = 0;
        setName("Dague");

        setBackgroundImage(Images.DAGGER);

        setInteractionBox(Hitbox.FULL);

    }

    public Dagger(Integer tileX, Integer tileY) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
    }
}
