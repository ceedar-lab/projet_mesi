package com.mesi.decor;

import com.mesi.params.Constant;
import com.mesi.params.Images;

import java.awt.*;

public class Chest extends DecorObject {

    private String state;

    public Chest(String state) {
        foregroundOffsetX = 0;
        foregroundOffsetY = 0;
        backgroundOffsetX = 0;
        backgroundOffsetY = 0;

        this.state = state;

        if (state.equals("closed"))
            setBackgroundImage(Images.CHEST);
        else if (state.equals("open"))
            setBackgroundImage(Images.CHEST_OPEN);

        hitbox = new Rectangle(0, 0, Constant.TILE_SIZE, Constant.TILE_SIZE);
    }

    public Chest(String state, Integer tileX, Integer tileY) {
        this(state);
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
        hitbox = new Rectangle(x, y, Constant.TILE_SIZE, Constant.TILE_SIZE);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}