package com.mesi.decor;

import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.resources.Images;

public class Sign extends DecorObject {


    private String text ="";

    public Sign() {
        foregroundOffsetX = -1 * Constant.TILE_SIZE - 1;
        foregroundOffsetY = -3 * Constant.TILE_SIZE;
        backgroundOffsetX = -Constant.TILE_SIZE / 2;
        backgroundOffsetY = 0;
        setName("Sign");

        setBackgroundImage(null);
        setForegroundImage(null);

        hitbox = Hitbox.FULL;
    }

    public Sign(Integer tileX, Integer tileY) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
