package com.mesi.decor;

import com.mesi.panels.Game;
import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.params.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Chest extends DecorObject {

    public Chest() {
        foregroundOffsetX = 0;
        foregroundOffsetY = 0;
        backgroundOffsetX = 0;
        backgroundOffsetY = 0;

        hitbox = Hitbox.FULL;
        setName("Coffre");
        decorObjectAnim = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            decorObjectAnim[i] = Images.CHEST.getSubimage(32, i * 32, 32, 32);
        }

    }

    public Chest(Integer tileX, Integer tileY) {
        this();
        setX(tileX * Constant.TILE_SIZE);
        setY(tileY * Constant.TILE_SIZE);
    }


    public void open() {
        setAnimLaunched(true);
    }


    @Override
    public BufferedImage getForgroundImage() {

        return null;
    }

    @Override
    public BufferedImage getBackgroundImage() {
        if (getAnimPhase() >= 2) {
            setAnimLaunched(false);
            setAnimPhase(2);
        }
        return decorObjectAnim[getAnimPhase()];
    }


}
