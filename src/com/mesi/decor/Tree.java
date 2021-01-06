package com.mesi.decor;

import com.mesi.params.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tree extends DecorObject
{

    public Tree()
    {
        urlForegroundImage = "res/images/tree-foliage.png";
        urlBackgroundImage = "res/images/tree-trunk.png";

        foregroundOffsetX = -1 * Constant.TILE_SIZE;
        foregroundOffsetY = -3 * Constant.TILE_SIZE;
        backgroundOffsetX = -Constant.TILE_SIZE/2;
        backgroundOffsetY = 0;

        hitbox = new Rectangle(0,0, Constant.TILE_SIZE, Constant.TILE_SIZE);
    }
}
