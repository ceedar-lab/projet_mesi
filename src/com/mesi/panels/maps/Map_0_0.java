package com.mesi.panels.maps;

import com.mesi.decor.Tree;
import com.mesi.params.Hitbox;

import java.io.IOException;

/**
 * Paramétrage de l'image de fond, des blocs de collisions et de téléportation de la map.
 * L'initialisation se fait partir de la classe MapGenerator.
 */
public class Map_0_0 extends MapModel {

    /**********  Constructors  **********/

    public Map_0_0(Integer width, Integer height, Integer startingPositionX, Integer startingPositionY, Integer startingDirection) throws IOException {
        super(width, height, startingPositionX, startingPositionY, startingDirection);

        setBackgroundURL("res/images/map/map_0_0.jpg");
        setBackgroundImage();

        setScrollable(false);

        /** Coordonnées des blocs de collision **/
        getTileList().get("5,14").setTraversable(false);
        getTileList().get("8,11").setTraversable(false);
        getTileList().get("9,16").setTraversable(false);
        getTileList().get("13,13").setTraversable(false);
        getTileList().get("17,19").setTraversable(false);
        getTileList().get("17,20").setTraversable(false);
        getTileList().get("18,18").setTraversable(false);
        getTileList().get("18,19").setTraversable(false);
        getTileList().get("18,20").setTraversable(false);
        getTileList().get("18,21").setTraversable(false);
        getTileList().get("18,22").setTraversable(false);
        getTileList().get("19,18").setTraversable(false);
        getTileList().get("19,22").setTraversable(false);
        getTileList().get("20,18").setTraversable(false);
        getTileList().get("20,22").setTraversable(false);
        getTileList().get("21,16").setTraversable(false);
        getTileList().get("21,17").setTraversable(false);
        getTileList().get("21,18").setTraversable(false);
        getTileList().get("21,22").setTraversable(false);
        getTileList().get("22,14").setTraversable(false);
        getTileList().get("22,15").setTraversable(false);
        getTileList().get("22,21").setTraversable(false);
        getTileList().get("23,13").setTraversable(false);
        getTileList().get("23,21").setTraversable(false);
        getTileList().get("24,13").setTraversable(false);
        getTileList().get("24,20").setTraversable(false);
        getTileList().get("25,13").setTraversable(false);
        getTileList().get("25,14").setTraversable(false);
        getTileList().get("25,19").setTraversable(false);
        getTileList().get("26,15").setTraversable(false);
        getTileList().get("26,18").setTraversable(false);
        getTileList().get("27,15").setTraversable(false);
        getTileList().get("27,16").setTraversable(false);
        getTileList().get("27,17").setTraversable(false);


        /** ajout des arbres **/
        for(int i =1;i<width;i+=2)
        {
            getDecorObjectArraylist().add(new Tree(i,1));
        }
        for(int i =0;i<width;i+=2)
        {
            if(i!=10)
            {
                getDecorObjectArraylist().add(new Tree(i,2));
            }
        }
        for(int i =1;i<height;i+=2)
        {
            getDecorObjectArraylist().add(new Tree(1,i));
        }
        for(int i =0;i<height;i+=2)
        {
            if(i!=10)
            {
                getDecorObjectArraylist().add(new Tree(2,i));
            }
        }




        /** Coordonnées des blocs de téléportation et tuile de destination **/

        addTeleport(getTileList().get("39,10"),true, "MAP_0_1 -1,10", Hitbox.EAST_BORD);
        addTeleport(getTileList().get("0,10"),true, "MAP_0_1 79,10", Hitbox.WEST_BORD);
        addTeleport(getTileList().get("10,0"),true, "MAP_0_1 10,47", Hitbox.NORTH_BORD);
        addTeleport(getTileList().get("10,23"),true, "MAP_0_1 10,0", Hitbox.SOUTH_BORD);


//        /** Crée les listes de blocs collision / téléportation à récupérer **/
//        setLeftBounds();
//        setRightBounds();
//        setUpperBounds();
//        setLowerBounds();



    }
}
