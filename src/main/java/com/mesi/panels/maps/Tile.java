package com.mesi.panels.maps;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tile extends JPanel {

    /**********  Attributes  **********/

    private Integer tileX = 0;
    private Integer tileY = 0;

    private boolean traversable;
    private List<Rectangle> hitBoxs = new ArrayList<>();
    private boolean teleport;
    private Rectangle teleportBounds;
    private TileEvent tileEvent;


    private String bindedTile = null;

    /**********  Constructors  **********/

    public Tile(Integer tileX, Integer tileY) {
        if (traversable) setBackground(Color.RED);
        this.tileX = tileX;
        this.tileY = tileY;
        this.traversable = true;
        this.teleport = false;
    }

    /**********  Getters / Setters  **********/

    public int getTileX() {
        return tileX;
    }
    public int getTileY() {
        return tileY;
    }
    public boolean isTraversable() {
        return traversable;
    }
    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }
    public boolean isTeleport() {
        return teleport;
    }
    public void setTeleport(boolean teleport, String bindedTile, Rectangle teleportBounds) {
        this.teleport = teleport;
        this.bindedTile = bindedTile;
        this.teleportBounds = new Rectangle
                (
                        tileX + teleportBounds.x,
                        tileY + teleportBounds.y,
                        teleportBounds.width,
                        teleportBounds.height
                );
    }
    public Rectangle getTeleportBounds() { return teleportBounds; }
    public String getBindedTile() {
        return bindedTile;
    }
    public List<Rectangle> getHitBoxs() {
        return hitBoxs;
    }

    public TileEvent getTileEvent() {
        return tileEvent;
    }

    public void setTileEvent(TileEvent tileEvent) {
        this.tileEvent = tileEvent;
    }
    /**********  Methods  **********/

    /**
     * Ajout d'une hitbox a la tile
     **/
    public void addHitbox(Rectangle hitbox) {
        hitBoxs.add(new Rectangle(tileX + hitbox.x, tileY + hitbox.y, hitbox.width, hitbox.height));
    }

    /**
     * Suppression des hitboxs de la tile
     **/
    public void removeHitboxs(){
        hitBoxs = new ArrayList<>();
    }

}
