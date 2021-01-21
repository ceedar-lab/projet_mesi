package com.mesi.panels.maps;

import com.mesi.decor.Tree;
import com.mesi.params.Hitbox;

import java.io.IOException;

/**
 * Paramétrage de l'image de fond, des blocs de collisions et de téléportation de la map.
 * L'initialisation se fait partir de la classe MapGenerator.
 */
public class Map_0_1 extends MapModel {

    /**********  Constructors  **********/

    public Map_0_1(Integer width, Integer height, Integer startingPositionX, Integer startingPositionY, Integer startingDirection) throws IOException {
        super(width, height, startingPositionX, startingPositionY, startingDirection);

        setBackgroundURL("res/images/map/newMap.jpg");
        setBackgroundImage();

        setScrollable(true);

        /** Coordonnées des blocs de collision **/

        /** Ajout arbre de decor **/
        getDecorObjectArraylist().add(new Tree(10,10));
        getDecorObjectArraylist().add(new Tree(20,10));
        getDecorObjectArraylist().add(new Tree(25,15));
        getDecorObjectArraylist().add(new Tree(40,10));
        getDecorObjectArraylist().add(new Tree(45,20));
        getDecorObjectArraylist().add(new Tree(50,30));
        getDecorObjectArraylist().add(new Tree(60,35));
        getDecorObjectArraylist().add(new Tree(70,25));

//        /** Coordonnées des blocs de téléportation et tuile de destination **/
//        getTileList().get("0,10").setTeleport(true, "MAP_0_0 39,10", Hitbox.FULL);
//        getTileList().get("79,10").setTeleport(true, "MAP_0_0 -1,10", Hitbox.FULL); // -1 permet au personnage de réapparaitre collé à la bordure
//        getTileList().get("10,0").setTeleport(true, "MAP_0_0 10,23", Hitbox.FULL);
//        getTileList().get("10,47").setTeleport(true, "MAP_0_0 10,0", Hitbox.FULL);

        /** Coordonnées des blocs de téléportation et tuile de destination **/
        addTeleport(getTileList().get("0,10"),true, "MAP_0_0 39,10", Hitbox.WEST_BORD);
        addTeleport(getTileList().get("79,10"),true, "MAP_0_0 -1,10", Hitbox.EAST_BORD); //
        addTeleport(getTileList().get("10,0"),true, "MAP_0_0 10,23", Hitbox.NORTH_BORD);
        addTeleport(getTileList().get("10,47"),true, "MAP_0_0 10,0", Hitbox.SOUTH_BORD);
    }
}
