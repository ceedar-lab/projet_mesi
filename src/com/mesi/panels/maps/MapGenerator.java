package com.mesi.panels.maps;

import com.mesi.MainZeldo;
import com.mesi.panels.Game;

import java.io.IOException;

/**
 * Génération des différentes cartes au démarrage du jeu.
 */
public class MapGenerator {

    /**********  Constructors  **********/

    public MapGenerator() throws IOException {

        map_0_0();
        map_0_1();
    }

    /**********  Methods  **********/

    public void map_0_0() throws IOException {
        MapModel map = new Map_0_0(40, 24,  Game.teleportPositionX, Game.teleportPositionY, (Integer)Game.direction.get(0));
        String name = "MAP_0_0";

        MainZeldo.mapList.put(name, map);
    }

    public void map_0_1() throws IOException {
        MapModel map = new Map_0_1(80, 48,  Game.teleportPositionX, Game.teleportPositionY, (Integer)Game.direction.get(0));
        String name = "MAP_0_1";

        MainZeldo.mapList.put(name, map);
    }
}
