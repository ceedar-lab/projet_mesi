package com.mesi.decor;

import com.mesi.params.Constant;
import com.mesi.params.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bush extends DecorObject
{

    public Bush()
    {
        foregroundOffsetX = 0 ;
        foregroundOffsetY = 0;
        backgroundOffsetX = 0;
        backgroundOffsetY = 0;

        hitbox = new Rectangle(0,0, Constant.TILE_SIZE, Constant.TILE_SIZE);
    }

    public Bush(Integer tileX, Integer tileY)
    {
        this();
        setX(tileX*Constant.TILE_SIZE);
        setY(tileY*Constant.TILE_SIZE);
        hitbox = new Rectangle(x,y, Constant.TILE_SIZE, Constant.TILE_SIZE);
    }

    @Override
    public BufferedImage getBackgroundImage()
    {
        return Images.BUSH;
    }



}
