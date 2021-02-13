package com.mesi.panels.maps;

import com.mesi.MainZeldo;
import com.mesi.params.Constant;

import java.io.IOException;

/**
 * Génération des différentes cartes au démarrage du jeu.
 */
public class MapGenerator {

    /**********  Constructors  **********/

    public MapGenerator() throws IOException {

        map2();
        map1();
    }

    /**********  Methods  **********/

    public void map1() throws IOException {
        MapModel map = new Map1(90, 50);
        String name = "MAP_1";

        MainZeldo.mapList.put(name, map);
    }

    public void map2() throws IOException {
        MapModel map = new Map2(Constant.FRAME_WIDTH / 32, Constant.FRAME_HEIGHT / 32);
        String name = "MAP_2";

        MainZeldo.mapList.put(name, map);
    }
}
