package com.mesi.panels.maps;

import com.mesi.decor.DecorObject;
import com.mesi.params.Constant;
import com.mesi.params.Hitbox;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Map abstraite de base.
 */
public abstract class MapModel extends JPanel {

    /**********  Attributes  **********/

    public static String test;

    private Integer width;
    private Integer height;

    private Integer startingPositionX;
    private Integer startingPositionY;
    private Integer startingDirection;

    private boolean scrollable;

    private Hashtable<String, Tile> tileList = new Hashtable<>();

    private String backgroundURL;
    private BufferedImage backgroundImage;
    private String foregroundURL;
    private BufferedImage foregroundImage;

    private ArrayList<DecorObject>decorObjectArraylist = new ArrayList<>();

    private ArrayList<Rectangle> hitboxs = new ArrayList<>();

    private ArrayList<Rectangle> leftBounds;
    private ArrayList<Rectangle> rightBounds;
    private ArrayList<Rectangle> upperBounds;
    private ArrayList<Rectangle> lowerBounds;

    private ArrayList<Tile> teleports = new ArrayList<>();

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

        /** ajout de bloc de collision sur les quatre coté **/

        /** bord ouest **/
        for (int y = 0; y < this.height; y++)
        {
            Tile tile = tileList.get( "0," + y);
            tile.addHtibox(Hitbox.WEST_BORD);
        }
        /** bord est **/
        for (int y = 0; y < this.height; y++)
        {
            Tile tile = tileList.get( (this.width-1)+"," + y);
            tile.addHtibox(Hitbox.EAST_BORD);
        }
        /** bord ouest **/
        for (int x = 0; x < this.width; x++)
        {
            Tile tile = tileList.get( x+",0");
            tile.addHtibox(Hitbox.NORTH_BORD);
        }
        /** bord ouest **/
        for (int x = 0; x < this.width; x++)
        {
            Tile tile = tileList.get( x+","+(this.height-1));
            tile.addHtibox(Hitbox.SOUTH_BORD);
        }


    }

    /**********  Getters / Setters  **********/

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Hashtable<String, Tile> getTileList() {
        return tileList;
    }
    public boolean isScrollable() { return scrollable; }
    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }
    public void setBackgroundURL(String backgroundURL) {
        this.backgroundURL = backgroundURL;
    }
    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }
    public void setForegroundURL(String foregroundURL) { this.foregroundURL = foregroundURL; }
    public BufferedImage getForegroundImage() { return foregroundImage; }
    public void setBackgroundImage() throws IOException { this.backgroundImage = ImageIO.read(new File(backgroundURL)); }
    public void setForegroundImage() throws IOException { this.foregroundImage = ImageIO.read(new File(foregroundURL)); }
    public ArrayList<Tile> getTeleports() {
        return teleports;
    }

    public ArrayList<DecorObject> getDecorObjectArraylist()
    {
        return decorObjectArraylist;
    }

    public void setDecorObjectArraylist(ArrayList<DecorObject> decorObjectArraylist)
    {
        this.decorObjectArraylist = decorObjectArraylist;
    }


    /** Récupère la liste des hitbox. **/
    public ArrayList<Rectangle> getHitboxList()
    {
        ArrayList<Rectangle>hitboxList = new ArrayList<>();

        /** ajout des hitboxs des objet **/
        for (DecorObject decorObject:getDecorObjectArraylist())
        {
            if(decorObject.getHitbox()!=null)
            {
                hitboxList.add(decorObject.getHitbox());
            }

        }

        /** ajout des hitboxs des cases non traversables **/
        Enumeration<Tile> e = getTileList().elements();
        while (e.hasMoreElements())
        {
            Tile tile = e.nextElement();
            if (!tile.isTraversable())
            {
                Rectangle hitbox = new Rectangle(tile.getX()+Hitbox.FULL.x,tile.getY()+Hitbox.FULL.y,Hitbox.FULL.width,Hitbox.FULL.height);
                hitboxList.add(hitbox);
            }
            if(tile.getHitBoxs().size()>0)
            {
                for (Rectangle hitbox:tile.getHitBoxs())
                {
                    hitboxList.add(hitbox);
                }
            }
        }

//        /** ajout des hitboxs de bord de map **/
//        Enumeration en = getTileList().elements();
//        while(en.hasMoreElements())
//        {
//            Tile tile = (Tile)en.nextElement();
//            if(tile.getHitBoxs().size()>0)
//            {
//                for (Rectangle hitbox:tile.getHitBoxs())
//                {
//                    hitboxList.add(hitbox);
//                }
//            }
//        }


        return hitboxList;
    }


    public void addTeleport(Tile teleport,Boolean isTeleport, String destination,Rectangle teleportBounds)
    {
        teleport.setTeleport(isTeleport,destination,teleportBounds);
        teleport.setTraversable(true);
        teleport.getHitBoxs().clear();
        teleports.add(teleport);
    }





}
