package com.mesi.panels.maps;

import java.io.IOException;

public class Map_0_1 extends MapModel {

    /**********  Constructors  **********/

    public Map_0_1(Integer mapWidth, Integer mapHeight, Integer startingPositionX, Integer startingPositionY, Integer startingDirection) throws IOException {
        super(mapWidth, mapHeight, startingPositionX, startingPositionY, startingDirection);

        /** Coordonnées des blocs de collision **/
        getTileList().get("0,0").setTraversable(false);
    }
}
