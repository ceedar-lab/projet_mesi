package com.mesi.panels.maps;

import java.io.IOException;

/**
 * Paramétrage de l'image de fond, des blocs de collisions et de téléportation de la map.
 * L'initialisation se fait partir de la classe MapGenerator.
 */
public class Map_0_1 extends MapModel {

    /**********  Constructors  **********/

    public Map_0_1(Integer width, Integer height, Integer startingPositionX, Integer startingPositionY, Integer startingDirection) throws IOException {
        super(width, height, startingPositionX, startingPositionY, startingDirection);

        setBackgroundURL("res/images/map/newMap.jpg");
        setBackgroundImage();

        setScrollable(true);

        /** Coordonnées des blocs de collision **/
        getTileList().get("0,0").setTraversable(false);

        /** Coordonnées des blocs de téléportation et tuile de destination **/
        getTileList().get("0,10").setTeleport(true, "MAP_0_0 39,10");
        getTileList().get("79,10").setTeleport(true, "MAP_0_0 -1,10"); // -1 permet au personnage de réapparaitre collé à la bordure
        getTileList().get("10,0").setTeleport(true, "MAP_0_0 10,23");
        getTileList().get("10,47").setTeleport(true, "MAP_0_0 10,0");

        /** Crée les listes de blocs collision / téléportation à récupérer **/
        setLeftBounds();
        setRightBounds();
        setUpperBounds();
        setLowerBounds();
        setTeleport();
    }
}
