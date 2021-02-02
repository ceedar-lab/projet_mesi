package com.mesi.decor.collectableItem;

import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.params.Images;

import java.awt.image.BufferedImage;

public class Shield extends CollectableItem {

    public Shield() {
        foregroundOffsetX = 0;
        foregroundOffsetY = 0;
        backgroundOffsetX = 0;
        backgroundOffsetY = 0;
        setName("shield");

        setInteractionBox(Hitbox.FULL);

    }

    public Shield(Integer tileX, Integer tileY) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
    }

    @Override
    public BufferedImage getBackgroundImage() {
        return Images.SHIELD;
    }


}
