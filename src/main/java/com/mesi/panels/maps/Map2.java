package com.mesi.panels.maps;

import com.mesi.params.Hitbox;
import com.mesi.resources.Images;

import java.io.IOException;

/**
 * Paramétrage de l'image de fond, des blocs de collisions et de téléportation de la map.
 * L'initialisation se fait partir de la classe MapGenerator.
 */
public class Map2 extends MapModel {

    /**********  Constructors  **********/

    public Map2(Integer width, Integer height) throws IOException {
        super(width, height);

        setBackgroundImage(Images.MAP_2_BG);
        setForegroundImage(Images.MAP_2_FG);

        setScrollable(false);

        /** Coordonnées des blocs de collision **/
        for (int i = getMapHeight()/2 - 2; i <= getMapHeight()/2 + 6; i++)
            getTileList().get(getMapWidth()/2 - 9 + "," + i).setTraversable(false); // Bord gauche
        for (int i = getMapHeight()/2 + 1; i <= getMapHeight()/2 + 3; i++)
            getTileList().get(getMapWidth()/2 + 8 + "," + i).setTraversable(false); // Bord droit
        for (int i = getMapWidth()/2 - 8; i <= getMapWidth()/2 + 5; i++)
            getTileList().get(i + "," + (getMapHeight()/2 - 3)).setTraversable(false); // Bord nord
        for (int i = getMapWidth()/2 - 8; i <= getMapWidth()/2 + 3; i++)
            getTileList().get(i + "," + (getMapHeight()/2 + 7)).setTraversable(false); // Bord sud

        getTileList().get(getMapWidth()/2 - 8 + "," + (getMapHeight()/2 + 1)).setTraversable(false);
        getTileList().get(getMapWidth()/2 - 8 + "," + (getMapHeight()/2 + 3)).setTraversable(false);
        getTileList().get(getMapWidth()/2 - 8 + "," + (getMapHeight()/2 + 4)).setTraversable(false);
        getTileList().get(getMapWidth()/2 - 7 + "," + (getMapHeight()/2 - 2)).setTraversable(false);
        getTileList().get(getMapWidth()/2 - 5 + "," + (getMapHeight()/2 - 1)).setTraversable(false);
        getTileList().get(getMapWidth()/2 - 5 + "," + getMapHeight()/2).setTraversable(false);
        getTileList().get(getMapWidth()/2 - 5 + "," + (getMapHeight()/2 + 1)).setTraversable(false);
        getTileList().get(getMapWidth()/2 - 4 + "," + (getMapHeight()/2 - 1)).setTraversable(false);
        getTileList().get(getMapWidth()/2 - 4 + "," + getMapHeight()/2).setTraversable(false);
        getTileList().get(getMapWidth()/2 - 4 + "," + (getMapHeight()/2 + 1)).setTraversable(false);
        getTileList().get(getMapWidth()/2 - 1 + "," + (getMapHeight()/2 + 7)).setTraversable(true);
        getTileList().get(getMapWidth()/2 + "," + (getMapHeight()/2 + 7)).setTraversable(true);
        getTileList().get(getMapWidth()/2 + 2 + "," + (getMapHeight()/2 + 2)).setTraversable(false);
        getTileList().get(getMapWidth()/2 + 4 + "," + (getMapHeight()/2 + 6)).setTraversable(false);
        getTileList().get(getMapWidth()/2 + 5 + "," + (getMapHeight()/2 + 5)).setTraversable(false);
        getTileList().get(getMapWidth()/2 + 6 + "," + (getMapHeight()/2 - 2)).setTraversable(false);
        getTileList().get(getMapWidth()/2 + 6 + "," + (getMapHeight()/2 - 1)).setTraversable(false);
        getTileList().get(getMapWidth()/2 + 6 + "," + (getMapHeight()/2 + 4)).setTraversable(false);
        getTileList().get(getMapWidth()/2 + 7 + "," + getMapHeight()/2).setTraversable(false);
        getTileList().get(getMapWidth()/2 + 7 + "," + (getMapHeight()/2 + 4)).setTraversable(false);

        /** Coordonnées des blocs de téléportation et tuile de destination **/
        addTeleport(getTileList().get(getMapWidth()/2 - 1 + "," + (getMapHeight()/2 + 6)), true, "MAP_1 17,15", Hitbox.SOUTH_BORD);
        addTeleport(getTileList().get(getMapWidth()/2 + "," + (getMapHeight()/2 + 6)), true, "MAP_1 18,15", Hitbox.SOUTH_BORD);
    }
}
