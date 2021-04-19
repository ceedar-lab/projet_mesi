package com.mesi.decor;

import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.resources.Images;

public class Tree extends DecorObject {

    public Tree() {
        foregroundOffsetX = -1 * Constant.TILE_SIZE - 1;
        foregroundOffsetY = -3 * Constant.TILE_SIZE;
        backgroundOffsetX = -Constant.TILE_SIZE / 2;
        backgroundOffsetY = 0;
        setName("Arbre");

        setBackgroundImage(Images.TRUNK);
        setForegroundImage(Images.FOLIAGE);

        hitbox = Hitbox.FULL;
    }

    public Tree(Integer tileX, Integer tileY) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
    }
}
