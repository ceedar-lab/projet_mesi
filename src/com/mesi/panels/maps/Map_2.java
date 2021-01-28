package com.mesi.panels.maps;

import com.mesi.decor.Bush;
import com.mesi.decor.Chest;
import com.mesi.decor.Tree;
import com.mesi.params.Hitbox;

import java.io.IOException;

/**
 * Paramétrage de l'image de fond, des blocs de collisions et de téléportation de la map.
 * L'initialisation se fait partir de la classe MapGenerator.
 */
public class Map_2 extends MapModel {

    /**********  Constructors  **********/

    public Map_2(Integer width, Integer height, Integer startingPositionX, Integer startingPositionY, Integer startingDirection) throws IOException {
        super(width, height, startingPositionX, startingPositionY, startingDirection);

        setBackgroundURL("res/images/map/map_2-background.jpg");
        setForegroundURL("res/images/map/map_2-foreground.png");
        setBackgroundImage();
        setForegroundImage();

        setScrollable(false);

        /** Coordonnées des blocs de collision **/
        for (int i = 9; i <= 17; i++)
            getTileList().get("11," + i).setTraversable(false); // Bord gauche
        for (int i = 12; i <= 15; i++)
            getTileList().get("28," + i).setTraversable(false); // Bord droit
        for (int i = 12; i <= 25; i++)
            getTileList().get(i + ",8").setTraversable(false); // Bord nord
        for (int i = 12; i <= 24; i++)
            getTileList().get(i + ",18").setTraversable(false); // Bord sud

        getTileList().get("12,12").setTraversable(false);
        getTileList().get("12,14").setTraversable(false);
        getTileList().get("12,15").setTraversable(false);
        getTileList().get("13,9").setTraversable(false);
        getTileList().get("15,10").setTraversable(false);
        getTileList().get("15,11").setTraversable(false);
        getTileList().get("15,12").setTraversable(false);
        getTileList().get("16,10").setTraversable(false);
        getTileList().get("16,11").setTraversable(false);
        getTileList().get("16,12").setTraversable(false);
        getTileList().get("19,18").setTraversable(true);
        getTileList().get("20,18").setTraversable(true);
        getTileList().get("22,13").setTraversable(false);
        getTileList().get("24,17").setTraversable(false);
        getTileList().get("25,16").setTraversable(false);
        getTileList().get("26,9").setTraversable(false);
        getTileList().get("26,10").setTraversable(false);
        getTileList().get("26,15").setTraversable(false);
        getTileList().get("27,11").setTraversable(false);
        getTileList().get("27,15").setTraversable(false);

        /** ajout des buissons **/
        for (int i = 21; i <= 23; i++) {
            getDecorObjectArraylist().add(new Chest(i, 9));
        }

        /** Coordonnées des blocs de téléportation et tuile de destination **/
        addTeleport(getTileList().get("19,17"), true, "MAP_1 17,15", Hitbox.SOUTH_BORD);
        addTeleport(getTileList().get("20,17"), true, "MAP_1 18,15", Hitbox.SOUTH_BORD);

    }
}
