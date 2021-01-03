package com.mesi.panels.maps;

import com.mesi.params.Constant;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    private Integer x = 0;
    private Integer y = 0;

    private boolean traversable = true;

    public Tile(Integer x, Integer y, Boolean traversable) {
        if (traversable) setBackground(Color.RED);
        this.x = x;
        this.y = y;
        this.traversable = traversable;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isTraversable() {
        return traversable;
    }

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, Constant.TILE_SIZE, Constant.TILE_SIZE);
    }

    public Rectangle getLeftBound() { return new Rectangle(x, y + Constant.BOUND_THICKNESS, Constant.BOUND_THICKNESS, Constant.TILE_SIZE - 2*Constant.BOUND_THICKNESS); }

    public Rectangle getRightBound() { return new Rectangle(x + Constant.TILE_SIZE - Constant.BOUND_THICKNESS, y + Constant.BOUND_THICKNESS, Constant.BOUND_THICKNESS, Constant.TILE_SIZE - 2*Constant.BOUND_THICKNESS); }

    public Rectangle getUpperBound() { return new Rectangle(x + Constant.BOUND_THICKNESS, y, Constant.TILE_SIZE - 2*Constant.BOUND_THICKNESS , Constant.BOUND_THICKNESS); }

    public Rectangle getLowerBound() { return new Rectangle(x + Constant.BOUND_THICKNESS, y + Constant.TILE_SIZE - Constant.BOUND_THICKNESS, Constant.TILE_SIZE - 2*Constant.BOUND_THICKNESS, Constant.BOUND_THICKNESS); }

    /*public Rectangle getLeftBound() { return new Rectangle(x - Constant.BOUND_THICKNESS, y, Constant.BOUND_THICKNESS, Constant.TILE_SIZE); }

    public Rectangle getRightBound() { return new Rectangle(x + Constant.TILE_SIZE, y, Constant.BOUND_THICKNESS, Constant.TILE_SIZE); }

    public Rectangle getUpperBound() { return new Rectangle(x, y - Constant.BOUND_THICKNESS, Constant.TILE_SIZE, Constant.BOUND_THICKNESS); }

    public Rectangle getLowerBound() { return new Rectangle(x, y + Constant.TILE_SIZE, Constant.TILE_SIZE, Constant.BOUND_THICKNESS); }*/
}
