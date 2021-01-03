package com.mesi.panels.maps;

public class Map_0_0 extends MapModel {

    public Map_0_0(Integer startingPositionX, Integer startingPositionY, Integer startingDirection) {
        super(startingPositionX, startingPositionY, startingDirection);

        getTileList().get("3,2").setTraversable(false);
        getTileList().get("3,3").setTraversable(false);
        getTileList().get("3,5").setTraversable(false);
        getTileList().get("3,6").setTraversable(false);
        getTileList().get("3,7").setTraversable(false);
        getTileList().get("3,8").setTraversable(false);
        getTileList().get("4,2").setTraversable(false);
        getTileList().get("5,2").setTraversable(false);
        getTileList().get("5,3").setTraversable(false);
    }
}
