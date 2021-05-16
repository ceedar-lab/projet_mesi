package com.mesi.panels.maps;

import com.mesi.MainZeldo;
import com.mesi.params.Constant;
import com.mesi.resources.Fonts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Génération des différentes cartes au démarrage du jeu.
 */
public class MapGenerator {

    /**********  Constructors  **********/

    private static final Logger logger = LogManager.getLogger(MapGenerator.class);

    public MapGenerator() throws IOException {
        logger.info("MapGenerator is initializing game maps");

        map3();
        map2();
        map1();
    }

    /**********  Methods  **********/

    public void map1() throws IOException {
        MapModel map = new Map1(90, 50);
        String name = "MAP_1";

        MainZeldo.mapList.put(name, map);

        logger.debug(name + " created");
    }

    public void map2() throws IOException {
        MapModel map = new Map2(Constant.FRAME_WIDTH / 32, Constant.FRAME_HEIGHT / 32);
        String name = "MAP_2";

        MainZeldo.mapList.put(name, map);

        logger.debug(name + " created");
    }

    public void map3() throws IOException {
        MapModel map = new Map3(80, 50);
        String name = "MAP_3";

        MainZeldo.mapList.put(name, map);

        logger.debug(name + " created");
    }
}
