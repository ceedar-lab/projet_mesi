package com.mesi.panels.maps;

import com.mesi.params.Constant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tile extends JPanel {

    /**********  Attributes  **********/

    private Integer x = 0;
    private Integer y = 0;

    private boolean traversable;
    private ArrayList<Rectangle>hitBoxs = new ArrayList<>();
    private boolean teleport;
    private Rectangle teleportBounds;


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
    public void setTeleport(boolean teleport, String bindedTile ,Rectangle teleportBounds) {
        this.teleport = teleport;
        this.bindedTile = bindedTile;
        this.teleportBounds = new Rectangle
        (
            x + teleportBounds.x,
            y + teleportBounds.y ,
            teleportBounds.width,
            teleportBounds.height
        );
    }
    public String getBindedTile() { return bindedTile; }

    public ArrayList<Rectangle> getHitBoxs()
    {
        return hitBoxs;
    }

    /**********  Methods  **********/

    /**
     * Récupère toutes bornes d'une case téléportation.
     * @return Rectangle.
     */
    public Rectangle getTeleportBounds() {
//        return new Rectangle(x - Constant.TILE_SIZE, y - Constant.TILE_SIZE, Constant.TILE_SIZE * 3, Constant.TILE_SIZE * 3);
        return teleportBounds;
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


    /** ajout d'une hitbox a la tile **/
    public void addHtibox(Rectangle hitbox)
    {
        hitBoxs.add(new Rectangle(x+hitbox.x,y+hitbox.y,hitbox.width, hitbox.height));
    }


}
