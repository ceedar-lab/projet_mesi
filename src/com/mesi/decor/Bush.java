package com.mesi.decor;

import com.mesi.params.Constant;
import com.mesi.params.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bush extends DecorObject {

    public Bush() {
        foregroundOffsetX = 0;
        foregroundOffsetY = 0;
        backgroundOffsetX = 0;
        backgroundOffsetY = 0;
        setName("Buisson");

    }

    public Bush(Integer tileX, Integer tileY) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
    }

    @Override
    public BufferedImage getBackgroundImage() {
        return Images.BUSH;
    }


}
