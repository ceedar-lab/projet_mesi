package com.mesi.panels.maps;

import com.github.cliftonlabs.json_simple.Jsonable;
import com.mesi.decor.Bush;
import com.mesi.decor.Chest;
import com.mesi.params.Hitbox;

import java.io.IOException;
import java.io.Writer;

/**
 * Paramétrage de l'image de fond, des blocs de collisions et de téléportation de la map.
 * L'initialisation se fait partir de la classe MapGenerator.
 */
public class Map_2 extends MapModel {

    /**********  Constructors  **********/

    public Map_2(Integer width, Integer height, Integer startingPositionX, Integer startingPositionY, Integer startingDirection) throws IOException {
        super(width, height, startingPositionX, startingPositionY, startingDirection);

        setBackgroundURL("src/main/resources/images/map/map_2a-background.jpg");
        setForegroundURL("src/main/resources/images/map/map_2a-foreground.png");
        setBackgroundImage();
        setForegroundImage();

        setScrollable(false);

        /** Coordonnées des blocs de collision **/
        for (int i = getHeight()/2 - 2; i <= getHeight()/2 + 6; i++)
            getTileList().get(getWidth()/2 - 9 + "," + i).setTraversable(false); // Bord gauche
        for (int i = getHeight()/2 + 1; i <= getHeight()/2 + 3; i++)
            getTileList().get(getWidth()/2 + 8 + "," + i).setTraversable(false); // Bord droit
        for (int i = getWidth()/2 - 8; i <= getWidth()/2 + 5; i++)
            getTileList().get(i + "," + (getHeight()/2 - 3)).setTraversable(false); // Bord nord
        for (int i = getWidth()/2 - 8; i <= getWidth()/2 + 3; i++)
            getTileList().get(i + "," + (getHeight()/2 + 7)).setTraversable(false); // Bord sud

        getTileList().get(getWidth()/2 - 8 + "," + (getHeight()/2 + 1)).setTraversable(false);
        getTileList().get(getWidth()/2 - 8 + "," + (getHeight()/2 + 3)).setTraversable(false);
        getTileList().get(getWidth()/2 - 8 + "," + (getHeight()/2 + 4)).setTraversable(false);
        getTileList().get(getWidth()/2 - 7 + "," + (getHeight()/2 - 2)).setTraversable(false);
        getTileList().get(getWidth()/2 - 5 + "," + (getHeight()/2 - 1)).setTraversable(false);
        getTileList().get(getWidth()/2 - 5 + "," + getHeight()/2).setTraversable(false);
        getTileList().get(getWidth()/2 - 5 + "," + (getHeight()/2 + 1)).setTraversable(false);
        getTileList().get(getWidth()/2 - 4 + "," + (getHeight()/2 - 1)).setTraversable(false);
        getTileList().get(getWidth()/2 - 4 + "," + getHeight()/2).setTraversable(false);
        getTileList().get(getWidth()/2 - 4 + "," + (getHeight()/2 + 1)).setTraversable(false);
        getTileList().get(getWidth()/2 - 1 + "," + (getHeight()/2 + 7)).setTraversable(true);
        getTileList().get(getWidth()/2 + "," + (getHeight()/2 + 7)).setTraversable(true);
        getTileList().get(getWidth()/2 + 2 + "," + (getHeight()/2 + 2)).setTraversable(false);
        getTileList().get(getWidth()/2 + 4 + "," + (getHeight()/2 + 6)).setTraversable(false);
        getTileList().get(getWidth()/2 + 5 + "," + (getHeight()/2 + 5)).setTraversable(false);
        getTileList().get(getWidth()/2 + 6 + "," + (getHeight()/2 - 2)).setTraversable(false);
        getTileList().get(getWidth()/2 + 6 + "," + (getHeight()/2 - 1)).setTraversable(false);
        getTileList().get(getWidth()/2 + 6 + "," + (getHeight()/2 + 4)).setTraversable(false);
        getTileList().get(getWidth()/2 + 7 + "," + getHeight()/2).setTraversable(false);
        getTileList().get(getWidth()/2 + 7 + "," + (getHeight()/2 + 4)).setTraversable(false);

        /** ajout des coffres **/
        getDecorObjectArraylist().add(new Chest(getWidth()/2, getHeight()/2 + -2));
        getDecorObjectArraylist().add(new Chest(getWidth()/2 + 3, getHeight()/2 + -2));

        /** Coordonnées des blocs de téléportation et tuile de destination **/
        addTeleport(getTileList().get(getWidth()/2 - 1 + "," + (getHeight()/2 + 6)), true, "MAP_1 17,15", Hitbox.SOUTH_BORD);
        addTeleport(getTileList().get(getWidth()/2 + "," + (getHeight()/2 + 6)), true, "MAP_1 18,15", Hitbox.SOUTH_BORD);
    }
}
