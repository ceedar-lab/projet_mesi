package com.mesi.panels.maps;

import com.mesi.decor.Tree;
import com.mesi.params.Hitbox;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Map_0_0 extends MapModel {

    /**********  Constructors  **********/

    public Map_0_0(Integer mapWidth, Integer mapHeight, Integer startingPositionX, Integer startingPositionY, Integer startingDirection) throws IOException {
        super(mapWidth, mapHeight, startingPositionX, startingPositionY, startingDirection);

        setBackgroundURL("res/images/map/map_0_0.jpg");
        setBackgroundImage();

        /** Coordonnées des blocs de collision **/
        getTileList().get("5,14").setTraversable(false);
        getTileList().get("8,11").setTraversable(false);
        getTileList().get("9,16").setTraversable(false);
        getTileList().get("13,13").setTraversable(false);
        getTileList().get("17,19").setTraversable(false);
        getTileList().get("17,20").setTraversable(false);
        getTileList().get("18,18").setTraversable(false);
        getTileList().get("18,19").setTraversable(false);
        getTileList().get("18,20").setTraversable(false);
        getTileList().get("18,21").setTraversable(false);
        getTileList().get("18,22").setTraversable(false);
        getTileList().get("19,18").setTraversable(false);
        getTileList().get("19,22").setTraversable(false);
        getTileList().get("20,18").setTraversable(false);
        getTileList().get("20,22").setTraversable(false);
        getTileList().get("21,16").setTraversable(false);
        getTileList().get("21,17").setTraversable(false);
        getTileList().get("21,18").setTraversable(false);
        getTileList().get("21,22").setTraversable(false);
        getTileList().get("22,14").setTraversable(false);
        getTileList().get("22,15").setTraversable(false);
        getTileList().get("22,21").setTraversable(false);
        getTileList().get("23,13").setTraversable(false);
        getTileList().get("23,21").setTraversable(false);
        getTileList().get("24,13").setTraversable(false);
        getTileList().get("24,20").setTraversable(false);
        getTileList().get("25,13").setTraversable(false);
        getTileList().get("25,14").setTraversable(false);
        getTileList().get("25,19").setTraversable(false);
        getTileList().get("26,15").setTraversable(false);
        getTileList().get("26,18").setTraversable(false);
        getTileList().get("27,15").setTraversable(false);
        getTileList().get("27,16").setTraversable(false);
        getTileList().get("27,17").setTraversable(false);
        getTileList().get("39,9").setTraversable(false);
        getTileList().get("39,12").setTraversable(false);

        /** case avec hitbox full **/
        getTileList().get("20,4").setHitbox(Hitbox.FULL);
        /** case avec un arbre **/
        getTileList().get("4,4").setDecorObject(new Tree());
        getTileList().get("5,4").setDecorObject(new Tree());
        getTileList().get("7,4").setDecorObject(new Tree());

        /** Coordonnées des blocs de téléportation **/
        getTileList().get("39,10").setTeleport(true);
        getTileList().get("39,11").setTeleport(true);

        /** Crée les listes de blocs collision / téléportation à récupérer **/
        setLeftBounds();
        setRightBounds();
        setUpperBounds();
        setLowerBounds();
        setTeleport();


    }
}
