package com.mesi.panels.maps;

import com.mesi.MainZeldo;
import com.mesi.panels.Game;
import com.mesi.params.Constant;

import java.io.IOException;

/**
 * Génération des différentes cartes au démarrage du jeu.
 */
public class MapGenerator {

    /**********  Constructors  **********/

    public MapGenerator() throws IOException {

        map_2();
        map_1();
    }

    /**********  Methods  **********/

    public void map_1() throws IOException {
        MapModel map = new Map_1(90, 50, Game.characterCoordinates[0] / Constant.TILE_SIZE, Game.characterCoordinates[1] / Constant.TILE_SIZE, (Integer) Game.direction.get(0));
        String name = "MAP_1";

        MainZeldo.mapList.put(name, map);
    }

    public void map_2() throws IOException {
        //MapModel map = new Map_2(40, 24, Game.teleportPositionX, Game.teleportPositionY, (Integer) Game.direction.get(0));
        MapModel map = new Map_2(Constant.FRAME_WIDTH / 32, Constant.FRAME_HEIGHT / 32, Game.characterCoordinates[0] / Constant.TILE_SIZE, Game.characterCoordinates[1] / Constant.TILE_SIZE, (Integer) Game.direction.get(0));
        String name = "MAP_2";

        MainZeldo.mapList.put(name, map);
    }
}
