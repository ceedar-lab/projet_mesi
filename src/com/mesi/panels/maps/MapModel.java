package com.mesi.panels.maps;

import com.mesi.params.Constant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class MapModel extends JPanel {

    private Integer startingPositionX;
    private Integer startingPositionY;
    private Integer startingDirection;

    private Hashtable<String, Tile> tileList = new Hashtable<>();

    public MapModel(Integer startingPositionX, Integer startingPositionY, Integer startingDirection) {
        this.startingPositionX = startingPositionX;
        this.startingPositionY = startingPositionY;
        this.startingDirection = startingDirection;
        /*setOpaque(true);
        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);*/
        for (int x = 0; x < Constant.MAP_WIDTH; x++) {
            for (int y = 0; y < Constant.MAP_HEIGHT; y++) {
                tileList.put(x+ "," +y, new Tile(x*Constant.TILE_SIZE, y*Constant.TILE_SIZE, true));
            }
        }
    }

    public Hashtable<String, Tile> getTileList() {
        return tileList;
    }

    public void setTileList(Hashtable<String, Tile> tileList) {
        this.tileList = tileList;
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

    public ArrayList<Rectangle> getLeftBounds() {
        ArrayList<Rectangle> leftBounds = new ArrayList<>();
        Enumeration<Tile> e = tileList.elements();
        while (e.hasMoreElements()) {
            Tile tile = e.nextElement();
            if (!tile.isTraversable()) leftBounds.add(tile.getLeftBound());
        }
        return leftBounds;
    }

    public ArrayList<Rectangle> getRightBounds() {
        ArrayList<Rectangle> rightBounds = new ArrayList<>();
        Enumeration<Tile> e = tileList.elements();
        while (e.hasMoreElements()) {
            Tile tile = e.nextElement();
            if (!tile.isTraversable()) rightBounds.add(tile.getRightBound());
        }
        return rightBounds;
    }

    public ArrayList<Rectangle> getUpperBounds() {
        ArrayList<Rectangle> upperBounds = new ArrayList<>();
        Enumeration<Tile> e = tileList.elements();
        while (e.hasMoreElements()) {
            Tile tile = e.nextElement();
            if (!tile.isTraversable()) upperBounds.add(tile.getUpperBound());
        }
        return upperBounds;
    }

    public ArrayList<Rectangle> getLowerBounds() {
        ArrayList<Rectangle> lowerBounds = new ArrayList<>();
        Enumeration<Tile> e = tileList.elements();
        while (e.hasMoreElements()) {
            Tile tile = e.nextElement();
            if (!tile.isTraversable()) lowerBounds.add(tile.getLowerBound());
        }
        return lowerBounds;
    }
}
