package com.mesi.panels.maps;

import java.io.IOException;

public class Map_0_1 extends MapModel {

    /**********  Constructors  **********/

    public Map_0_1(Integer startingPositionX, Integer startingPositionY, Integer startingDirection) throws IOException {
        super(startingPositionX, startingPositionY, startingDirection);;

        /** Coordonnées des blocs de collision **/
        getTileList().get("0,0").setTraversable(false);
    }
}
