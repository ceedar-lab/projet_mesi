package com.mesi.panels.maps;

import com.mesi.params.Constant;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    /**********  Attributes  **********/

    private Integer x = 0;
    private Integer y = 0;

    private boolean traversable;
    private boolean teleport;

    private String bindedTile = null;

    /**********  Constructors  **********/

    public Tile(Integer x, Integer y) {
        if (traversable) setBackground(Color.RED);
        this.x = x;
        this.y = y;
        this.traversable = true;
        this.teleport = false;
    }

    /**********  Getters / Setters  **********/

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isTraversable() {
        return traversable;
    }
    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }
    public boolean isTeleport() { return teleport; }
    public void setTeleport(boolean teleport, String bindedTile) {
        this.teleport = teleport;
        this.bindedTile = bindedTile;
    }
    public String getBindedTile() { return bindedTile; }

    /**********  Methods  **********/

    /**
     * Récupère toutes bornes d'une case téléportation.
     * @return Rectangle.
     */
    public Rectangle getTeleportBounds() {
        return new Rectangle(x - Constant.TILE_SIZE, y - Constant.TILE_SIZE, Constant.TILE_SIZE * 3, Constant.TILE_SIZE * 3);
    }

    /**
     * Récupère les bornes ouest de la case.
     * @return Rectangle.
     */
    public Rectangle getLeftBound() { return new Rectangle(x, y + Constant.BOUND_THICKNESS, Constant.BOUND_THICKNESS, Constant.TILE_SIZE - 2*Constant.BOUND_THICKNESS); }

    /**
     * Récupère les bornes est de la case.
     * @return Rectangle.
     */
    public Rectangle getRightBound() { return new Rectangle(x + Constant.TILE_SIZE - Constant.BOUND_THICKNESS, y + Constant.BOUND_THICKNESS, Constant.BOUND_THICKNESS, Constant.TILE_SIZE - 2*Constant.BOUND_THICKNESS); }

    /**
     * Récupère les bornes nord de la case.
     * @return Rectangle.
     */
    public Rectangle getUpperBound() { return new Rectangle(x + Constant.BOUND_THICKNESS, y, Constant.TILE_SIZE - 2*Constant.BOUND_THICKNESS , Constant.BOUND_THICKNESS); }

    /**
     * Récupère les bornes sud de la case.
     * @return Rectangle.
     */
    public Rectangle getLowerBound() { return new Rectangle(x + Constant.BOUND_THICKNESS, y + Constant.TILE_SIZE - Constant.BOUND_THICKNESS, Constant.TILE_SIZE - 2*Constant.BOUND_THICKNESS, Constant.BOUND_THICKNESS); }
}
