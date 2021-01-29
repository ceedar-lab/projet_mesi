package com.mesi.decor.collectableItem;

import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.params.Images;

import java.awt.image.BufferedImage;

public class Sword extends CollectableItem {

    public Sword() {
        foregroundOffsetX = -Constant.TILE_SIZE / 2;
        foregroundOffsetY = 0;
        backgroundOffsetX = -Constant.TILE_SIZE / 2;
        backgroundOffsetY = 0;
        setName("Sword");

        setInteractionBox(Hitbox.FULL);

    }

    public Sword(Integer tileX, Integer tileY) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
    }

    @Override
    public BufferedImage getBackgroundImage() {
        return Images.SWORD;
    }


}
