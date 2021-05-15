package com.mesi.panels.maps;

import com.mesi.decor.DecorObject;
import com.mesi.params.Constant;
import com.mesi.params.Hitbox;
import com.mesi.pnj.Pnj;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;


/**
 * Map abstraite de base.
 */
public abstract class MapModel extends JPanel {

    /**********  Attributes  **********/

    private Integer mapWidth;
    private Integer mapHeight;

    private File music;

    private boolean scrollable;

    private Map<String, Tile> tileList = new HashMap<>();

    private BufferedImage backgroundImage;
    private BufferedImage foregroundImage;

    private List<DecorObject> decorObjectArraylist = new ArrayList<>();
    private List<Pnj> pnjList = new ArrayList<>();
    private List<Tile> teleportList = new ArrayList<>();

    /**********  Constructors  **********/

    protected MapModel(Integer mapWidth, Integer mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        /** Crée toutes les tuiles de la carte, sans bloc de collision et sans téléportation par défaut **/
        for (int x = 0; x < this.mapWidth; x++) {
            for (int y = 0; y < this.mapHeight; y++) {
                tileList.put(x + "," + y, new Tile(x * Constant.TILE_SIZE, y * Constant.TILE_SIZE));
            }
        }

        /** ajout de bloc de collision sur les quatre coté **/

        /** bord ouest **/
        for (int y = 0; y < this.mapHeight; y++) {
            Tile tile = tileList.get("0," + y);
            tile.addHitbox(Hitbox.WEST_BORD);
        }
        /** bord est **/
        for (int y = 0; y < this.mapHeight; y++) {
            Tile tile = tileList.get((this.mapWidth - 1) + "," + y);
            tile.addHitbox(Hitbox.EAST_BORD);
        }
        /** bord ouest **/
        for (int x = 0; x < this.mapWidth; x++) {
            Tile tile = tileList.get(x + ",0");
            tile.addHitbox(Hitbox.NORTH_BORD);
        }
        /** bord ouest **/
        for (int x = 0; x < this.mapWidth; x++) {
            Tile tile = tileList.get(x + "," + (this.mapHeight - 1));
            tile.addHitbox(Hitbox.SOUTH_BORD);
        }

    }

    /**********  Getters / Setters  **********/

    public int getMapWidth() {
        return mapWidth;
    }
    public int getMapHeight() {
        return mapHeight;
    }
    public Map<String, Tile> getTileList() {
        return tileList;
    }
    public boolean isScrollable() {
        return scrollable;
    }
    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }
    public BufferedImage getBackgroundImage() { return backgroundImage; }
    public BufferedImage getForegroundImage() { return foregroundImage; }
    public void setBackgroundImage(BufferedImage backgroundImage) { this.backgroundImage = backgroundImage; }
    public void setForegroundImage(BufferedImage foregroundImage) { this.foregroundImage = foregroundImage; }
    public List<Tile> getTeleportList() {
        return teleportList;
    }
    public List<DecorObject> getDecorObjectArraylist() {
        return decorObjectArraylist;
    }
    public void setDecorObjectArraylist(List<DecorObject> decorObjectArraylist) { this.decorObjectArraylist = decorObjectArraylist; }
    public List<Pnj> getPnjList() {
        return pnjList;
    }
    public void setPnjList(List<Pnj> pnjList) {
        this.pnjList = pnjList;
    }
    public File getMusic() { return music; }
    public void setMusic(File music) { this.music = music; }

    /**
     * Récupère la liste des hitbox.
     **/
    public List<Rectangle> getHitboxList() {
        ArrayList<Rectangle> hitboxList = new ArrayList<>();

        /** ajout des hitboxs des objet **/
        for (DecorObject decorObject : getDecorObjectArraylist()) {
            if (decorObject.getHitbox() != null) {
                hitboxList.add(decorObject.getHitbox());
            }

        }

        /** ajout des hitboxs des PNJ **/
        for (Pnj pnj : getPnjList()) {
            if (pnj.getHitbox() != null) {
                hitboxList.add(pnj.getHitbox());
            }
        }

        /** ajout des hitboxs des cases non traversables **/
        for (Map.Entry<String, Tile> entry : tileList.entrySet()) {
            Tile tile = entry.getValue();
            if (!tile.isTraversable()) {
                Rectangle hitbox = new Rectangle(tile.getTileX() + Hitbox.FULL.x, tile.getTileY() + Hitbox.FULL.y, Hitbox.FULL.width, Hitbox.FULL.height);
                hitboxList.add(hitbox);
            }
            if (!tile.getHitBoxs().isEmpty()) {
                for (Rectangle hitbox : tile.getHitBoxs()) {
                    hitboxList.add(hitbox);
                }
            }
        }

        return hitboxList;
    }

    /**
     * Ajoute les cases de téléportation à la liste.
     **/
    public void addTeleport(Tile teleport, Boolean isTeleport, String destination, Rectangle teleportBounds) {
        teleport.setTeleport(isTeleport, destination, teleportBounds);
        teleport.setTraversable(true);
        teleport.getHitBoxs().clear();
        teleportList.add(teleport);
    }
}
