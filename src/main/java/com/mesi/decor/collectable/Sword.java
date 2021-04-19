package com.mesi.decor.collectable;

import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.resources.Images;

public class Sword extends CollectableItem {

    public Sword() {
        foregroundOffsetX = -Constant.TILE_SIZE / 2;
        foregroundOffsetY = 0;
        backgroundOffsetX = -Constant.TILE_SIZE / 2;
        backgroundOffsetY = 0;
        setName("épée");

        setBackgroundImage(Images.SWORD);

        setInteractionBox(Hitbox.FULL);

    }

    public Sword(Integer tileX, Integer tileY, Integer quantity) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
        this.quantity = quantity;
    }
}
