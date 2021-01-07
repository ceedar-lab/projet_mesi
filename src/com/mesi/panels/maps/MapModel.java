package com.mesi.panels.maps;

import com.mesi.params.Constant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;


/**
 * Map abstraite de base.
 */
public abstract class MapModel extends JPanel {

    /**********  Attributes  **********/

    private Integer width;
    private Integer height;

    private Integer startingPositionX;
    private Integer startingPositionY;
    private Integer startingDirection;

    private boolean scrollable;

    private Hashtable<String, Tile> tileList = new Hashtable<>();

    private String backgroundURL;
    private BufferedImage backgroundImage;

    private ArrayList<Rectangle> leftBounds;
    private ArrayList<Rectangle> rightBounds;
    private ArrayList<Rectangle> upperBounds;
    private ArrayList<Rectangle> lowerBounds;
    private ArrayList<Rectangle> teleport;

    /**********  Constructors  **********/

    public MapModel(Integer width, Integer height, Integer startingPositionX, Integer startingPositionY, Integer startingDirection) throws IOException {
        this.width = width;
        this.height = height;
        this.startingPositionX = startingPositionX;
        this.startingPositionY = startingPositionY;
        this.startingDirection = startingDirection;
        //setOpaque(true);
        //setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);

        /** Crée toutes les tuiles de la carte, sans bloc de collision et sans téléportation par défaut **/
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                tileList.put(x + "," + y, new Tile(x * Constant.TILE_SIZE, y * Constant.TILE_SIZE));
            }
        }
    }

    /**********  Getters / Setters  **********/

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Hashtable<String, Tile> getTileList() {
        return tileList;
    }
    public Integer getStartingPositionX() {
        return startingPositionX;
    }
    public void setStartingPositionX(Integer startingPositionX) {
        this.startingPositionX = startingPositionX;
    }
    public Integer getStartingPositionY() {
        return startingPositionY;
    }
    public void setStartingPositionY(Integer startingPositionY) {
        this.startingPositionY = startingPositionY;
    }
    public Integer getStartingDirection() {
        return startingDirection;
    }
    public void setStartingDirection(Integer startingDirection) {
        this.startingDirection = startingDirection;
    }
    public boolean isScrollable() { return scrollable; }
    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }
    public String getBackgroundURL() {
        return backgroundURL;
    }
    public void setBackgroundURL(String backgroundURL) {
        this.backgroundURL = backgroundURL;
    }
    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }
    public void setBackgroundImage() throws IOException { this.backgroundImage = ImageIO.read(new File(backgroundURL)); }
    public ArrayList<Rectangle> getLeftBounds() {
        return leftBounds;
    }
    public ArrayList<Rectangle> getRightBounds() {
        return rightBounds;
    }
    public ArrayList<Rectangle> getUpperBounds() {
        return upperBounds;
    }
    public ArrayList<Rectangle> getLowerBounds() {
        return lowerBounds;
    }
    public ArrayList<Rectangle> getTeleport() {
        return teleport;
    }

    /** Récupère la liste des bords ouest des blocs de collision de la carte **/
    public void setLeftBounds() {
        ArrayList<Rectangle> leftBounds = new ArrayList<>();
        Enumeration<Tile> e = tileList.elements();
        while (e.hasMoreElements()) {
            Tile tile = e.nextElement();
            if (!tile.isTraversable()) leftBounds.add(tile.getLeftBound());
        }
        this.leftBounds = leftBounds;
    }

    /** Récupère la liste des bords est des blocs de collision de la carte **/
    public void setRightBounds() {
        ArrayList<Rectangle> rightBounds = new ArrayList<>();
        Enumeration<Tile> e = tileList.elements();
        while (e.hasMoreElements()) {
            Tile tile = e.nextElement();
            if (!tile.isTraversable()) rightBounds.add(tile.getRightBound());
        }
        this.rightBounds = rightBounds;
    }

    /** Récupère la liste des bords nord des blocs de collision de la carte **/
    public void setUpperBounds() {
        ArrayList<Rectangle> upperBounds = new ArrayList<>();
        Enumeration<Tile> e = tileList.elements();
        while (e.hasMoreElements()) {
            Tile tile = e.nextElement();
            if (!tile.isTraversable()) upperBounds.add(tile.getUpperBound());
        }
        this.upperBounds = upperBounds;
    }

    /** Récupère la liste des bords sud des blocs de collision de la carte **/
    public void setLowerBounds() {
        ArrayList<Rectangle> lowerBounds = new ArrayList<>();
        Enumeration<Tile> e = tileList.elements();
        while (e.hasMoreElements()) {
            Tile tile = e.nextElement();
            if (!tile.isTraversable()) lowerBounds.add(tile.getLowerBound());
        }
        this.lowerBounds = lowerBounds;
    }

    /**
     * Récupère la liste des blocs de téléportation.
     * @return
     */
    public void setTeleport() {
        ArrayList<Rectangle> teleport = new ArrayList<>();
        Enumeration<Tile> e = tileList.elements();
        while (e.hasMoreElements()) {
            Tile tile = e.nextElement();
            if (tile.isTeleport()) teleport.add(tile.getTeleportBounds());
        }
        this.teleport = teleport;
    }
}
