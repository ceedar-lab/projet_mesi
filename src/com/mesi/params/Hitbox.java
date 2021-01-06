package com.mesi.params;

import java.awt.*;

public class Hitbox
{
    public static final Rectangle FULL = new Rectangle(0,0,Constant.TILE_SIZE,Constant.TILE_SIZE);
    public static final Rectangle WEST_BORD = new Rectangle(0,0,Constant.BOUND_THICKNESS,Constant.TILE_SIZE);
    public static final Rectangle NORTH_BORD = new Rectangle(0,0,Constant.TILE_SIZE,Constant.BOUND_THICKNESS);
    public static final Rectangle EAST_BORD = new Rectangle(Constant.TILE_SIZE - Constant.BOUND_THICKNESS,0,Constant.BOUND_THICKNESS,Constant.TILE_SIZE);
    public static final Rectangle SOUTH_BORD = new Rectangle(0,Constant.TILE_SIZE - Constant.BOUND_THICKNESS,Constant.TILE_SIZE,Constant.BOUND_THICKNESS);

}
