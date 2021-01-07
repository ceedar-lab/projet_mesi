package com.mesi.panels.maps;

import java.io.IOException;

public class Map_0_1 extends MapModel {

    /**********  Constructors  **********/

    public Map_0_1(Integer width, Integer height, Integer startingPositionX, Integer startingPositionY, Integer startingDirection) throws IOException {
        super(width, height, startingPositionX, startingPositionY, startingDirection);

        setBackgroundURL("res/images/map/newMap.jpg");
        setBackgroundImage();

        setScrollable(true);

        /** Coordonnées des blocs de collision **/
        getTileList().get("0,0").setTraversable(false);

        /** Coordonnées des blocs de téléportation **/
        getTileList().get("0,10").setTeleport(true);
        getTileList().get("0,11").setTeleport(true);
        getTileList().get("79,10").setTeleport(true);
        getTileList().get("79,11").setTeleport(true);

        /** Crée les listes de blocs collision / téléportation à récupérer **/
        setLeftBounds();
        setRightBounds();
        setUpperBounds();
        setLowerBounds();
        setTeleport();
    }
}
