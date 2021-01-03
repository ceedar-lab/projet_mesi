/*
package com.mesi;

import com.mesi.panel.map.Map;
import com.mesi.panel.map.Map_0_0;

import java.util.Hashtable;

public class GenerationCarte {

    public static Hashtable<String, Map> mapList = new Hashtable<String, Map>();


    public GenerationCarte() {
        //MainZeldo.MapList.clear();

        map_0_0();

        */
/*carteDuDebut();
        carte_0_1();
        carte_0_2();
        carte_0_3();
        carte_n1_2();
        carte_n1_3();
        carte_1_2();
        carte_1_3();*//*



    }

    public void map_0_0() {
        Map_0_0 map = new Map_0_0();

        */
/*PositionCarte positionCarte = new PositionCarte(0, 2, "Monde", "Monde");
        Carte carte = mapDeBase(positionCarte, "herbe","Monde Monde -1 2","Monde Monde 0 1","Monde Monde 1 2","Monde Monde 0 3");

        // arbre
        carte.getListeCase().get("" + 0 + " " + 0).setObjetDecor(new Arbre());
        carte.getListeCase().get("" + 16 + " " + 0).setObjetDecor(new Arbre());


        // rocher
        carte.getListeCase().get("" + 5 + " " + 7).setObjetDecor(new Rocher());
        carte.getListeCase().get("" + 14 + " " + 7).setObjetDecor(new Rocher());
        carte.getListeCase().get("" + 15 + " " + 7).setObjetDecor(new Rocher());*//*


        mapList.put("0_0", map);
    }

    */
/*public Carte mapDeBase(PositionCarte positionCarte, String typeDeSol , String tpOuest, String tpNord, String tpEst, String tpSud) {
        Carte carteTemp = new Carte(positionCarte);

        for (int i = 0; i < Carte.carteWidth; i++)
        {
            for (int j = 0; j < Carte.carteHeight; j++)
            {
                CaseCarte caseTemp = new CaseCarte(i, j);
                caseTemp.setType(typeDeSol);
                caseTemp.setTraversable(true);

                carteTemp.getListeCase().put(("" + i + " " + j), caseTemp);
            }
        }

        // Teleport ouest
        for (int j = 0; j < Carte.carteHeight; j++)
        {
            CaseCarte caseTemp = new CaseCarte(-1,j );
            caseTemp.setType("tp");
            caseTemp.setTraversable(true);
            if(tpOuest.equals(""))
            {
                caseTemp.setTraversable(false);
                //	carteTemp.getListeCase().get("" + 0 + " " + i).setTraversable(false);
            }
            caseTemp.setTypePassage(1);
            caseTemp.setDestinationPassage(tpOuest, 19, -1);

            carteTemp.getListeCase().put(("" + -1 + " " + j), caseTemp);
        }

        // Teleport nord
        for (int i = 0; i < Carte.carteWidth; i++)
        {
            CaseCarte caseTemp = new CaseCarte(i,-1 );
            caseTemp.setType("tp");
            caseTemp.setTraversable(true);
            if(tpNord.equals(""))
            {
                caseTemp.setTraversable(false);
//				carteTemp.getListeCase().get("" + i + " " + -1).setTraversable(false);
            }
            caseTemp.setTypePassage(1);
            caseTemp.setDestinationPassage(tpNord, -1, 11);

            carteTemp.getListeCase().put(("" + i + " " + -1), caseTemp);
        }

        // Teleport est
        for (int i = 0; i < Carte.carteHeight; i++)
        {
            CaseCarte caseTemp = new CaseCarte(Carte.carteWidth,i );
            caseTemp.setType("tp");
            caseTemp.setTraversable(true);
            if(tpEst.equals(""))
            {
                caseTemp.setTraversable(false);
            }
            caseTemp.setTypePassage(1);
            caseTemp.setDestinationPassage(tpEst, 0, -1);

            carteTemp.getListeCase().put(("" + Carte.carteWidth + " " + i), caseTemp);
        }

        // Teleport sud
        for (int i = 0; i < Carte.carteWidth; i++)
        {
            CaseCarte caseTemp = new CaseCarte(i,Carte.carteHeight );
            caseTemp.setType("tp");
            caseTemp.setTraversable(true);
            if(tpSud.equals(""))
            {
                caseTemp.setTraversable(false);
            }
            caseTemp.setTypePassage(1);
            caseTemp.setDestinationPassage(tpSud, -1, 0);

            carteTemp.getListeCase().put(("" + i + " " + Carte.carteHeight), caseTemp);
        }

        // murs cardinaux

        CaseCarte caseTemp = new CaseCarte(-1,-1 );
        caseTemp.setType("");
        caseTemp.setTraversable(false);

        carteTemp.getListeCase().put(("" + -1 + " " + -1), caseTemp);

        caseTemp = new CaseCarte(-1,Carte.carteHeight );
        caseTemp.setType("");
        caseTemp.setTraversable(false);

        carteTemp.getListeCase().put(("" + -1 + " " + Carte.carteHeight), caseTemp);

        caseTemp = new CaseCarte(Carte.carteWidth,Carte.carteHeight );
        caseTemp.setType("");
        caseTemp.setTraversable(false);

        carteTemp.getListeCase().put(("" + Carte.carteWidth + " " + Carte.carteHeight), caseTemp);

        caseTemp = new CaseCarte(Carte.carteWidth,-1 );
        caseTemp.setType("");
        caseTemp.setTraversable(false);

        carteTemp.getListeCase().put(("" + Carte.carteWidth + " " + -1), caseTemp);


        return carteTemp;
    }

    public void carteDuDebut()
    {
        PositionCarte positionCarte =
                new PositionCarte(0, 0, "Maison", "Depart");
        Carte carte = mapDeBase(positionCarte, "sol","","","","Monde Monde 0 1");

        // murs horizontaux
        for (int i = 0; i < Carte.carteWidth; i++)
        {
            carte.getListeCase().get("" + i + " " + 0).setTraversable(false);
            carte.getListeCase().get("" + i + " " + 0).setType("mur");
            carte.getListeCase().get("" + i + " " + (Carte.carteHeight-1)).setTraversable(false);
            carte.getListeCase().get("" + i + " " + (Carte.carteHeight-1)).setType("mur");
        }

        // murs verticaux
        for (int i = 0; i < Carte.carteHeight; i++)
        {
            carte.getListeCase().get("" + 0 + " " + i).setTraversable(false);
            carte.getListeCase().get("" + 0 + " " + i).setType("mur");
            carte.getListeCase().get("" + (Carte.carteWidth-1) + " " + i).setTraversable(false);
            carte.getListeCase().get("" + (Carte.carteWidth-1) + " " + i).setType("mur");
        }

        // porte d'entr�e
        for (int i = 7; i < 9; i++)
        {
            carte.getListeCase().get("" + i + " " + 11).setTraversable(true);
            carte.getListeCase().get("" + i + " " + 11).setType("sol");
        }

        // coffre
        carte.getListeCase().get("" + 15 + " " + 4).setObjetDecor(
                new Coffre("Gant de Force"));

        carte.getListeCase().get("" + 12 + " " + 3).setObjetDecor(
                new Coffre("Ep�e"));

        try
        {
            BufferedImage img1 = ImageIO.read(new File("res/image/FEET_shoes_brown.png"));
            BufferedImage img2 = ImageIO.read(new File("res/image/LEGS_pants_greenish.png"));
            BufferedImage img3 = ImageIO.read(new File("res/image/TORSO_robe_shirt_brown.png"));
            BufferedImage img4 = ImageIO.read(new File("res/image/WEAPON_shield_cutout_body.png"));

            Coffre coffre1 =new Coffre(new Equipement(img1));
            carte.getListeCase().get("" + 1 + " " + 1).setObjetDecor(coffre1);

            Coffre coffre2 =new Coffre(new Equipement(img2));
            carte.getListeCase().get("" + 2 + " " + 1).setObjetDecor(coffre2);
            Coffre coffre3 =new Coffre(new Equipement(img3));
            carte.getListeCase().get("" + 3 + " " + 1).setObjetDecor(coffre3);
            Coffre coffre4 =new Coffre(new Equipement(img4));
            carte.getListeCase().get("" + 4 + " " + 1).setObjetDecor(coffre4);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



        // lit
        // for (int i = 2; i < 5; i++)
        // {
        // for (int j = 2; j < 6; j++)
        // {
        // carte.getListeCase().get("" + i + " " + j).setObjetDecor(new
        // ObjetDecor());
        //// carte.getListeCase().get("" + i + " " + j).setType("lit");
        // }
        // }

        MainZeldo.listeCarte.put(positionCarte.getPositionText(), carte);

    }

    public void carte_0_1()
    {
        PositionCarte positionCarte = new PositionCarte(0, 1, "Monde", "Monde");
        Carte carte = mapDeBase(positionCarte, "herbe","","Maison Depart 0 0","","Monde Monde 0 2");

        // eau
        for (int i = 1; i < 12; i++)
        {
            carte.getListeCase().get("" + 0 + " " + i).setTraversable(false);
            carte.getListeCase().get("" + 0 + " " + i).setType("eau");
            carte.getListeCase().get("" + 19 + " " + i).setTraversable(false);
            carte.getListeCase().get("" + 19 + " " + i).setType("eau");
        }

        // mur maison
        for (int i = 0; i < 20; i++)
        {
            carte.getListeCase().get("" + i + " " + 0).setTraversable(false);
            carte.getListeCase().get("" + i + " " + 0).setType("mur");
        }

        // porte d'entr�e
        for (int i = 7; i < 9; i++)
        {
            carte.getListeCase().get("" + i + " " + 0).setTraversable(true);
            carte.getListeCase().get("" + i + " " + 0).setType("sol");
        }

        // buisson
        carte.getListeCase().get("" + 5 + " " + 7).setObjetDecor(new Buisson());

        carte.getListeCase().get("" + 14 + " " + 7).setObjetDecor(new Buisson());

        MainZeldo.listeCarte.put(positionCarte.getPositionText(), carte);

    }

    public void carte_0_2()
    {
        PositionCarte positionCarte = new PositionCarte(0, 2, "Monde", "Monde");
        Carte carte = mapDeBase(positionCarte, "herbe","Monde Monde -1 2","Monde Monde 0 1","Monde Monde 1 2","Monde Monde 0 3");

        // arbre
        carte.getListeCase().get("" + 0 + " " + 0).setObjetDecor(new Arbre());
        carte.getListeCase().get("" + 16 + " " + 0).setObjetDecor(new Arbre());


        // rocher
        carte.getListeCase().get("" + 5 + " " + 7).setObjetDecor(new Rocher());
        carte.getListeCase().get("" + 14 + " " + 7).setObjetDecor(new Rocher());
        carte.getListeCase().get("" + 15 + " " + 7).setObjetDecor(new Rocher());


        MainZeldo.listeCarte.put(positionCarte.getPositionText(), carte);

    }

    public void carte_0_3()
    {
        PositionCarte positionCarte = new PositionCarte(0, 3, "Monde", "Monde");
        Carte carte = mapDeBase(positionCarte, "herbe","Monde Monde -1 3","Monde Monde 0 2","Monde Monde 1 3","");



        MainZeldo.listeCarte.put(positionCarte.getPositionText(), carte);

    }

    public void carte_n1_2()
    {
        PositionCarte positionCarte = new PositionCarte(-1, 2, "Monde", "Monde");
        Carte carte = mapDeBase(positionCarte, "herbe","","","Monde Monde 0 2","Monde Monde -1 3");

        // arbre
//		carte.getListeCase().get("" + 0 + " " + 0).setObjetDecor(new Arbre());
        carte.getListeCase().get("" + 16 + " " + 0).setObjetDecor(new Arbre());

        MainZeldo.listeCarte.put(positionCarte.getPositionText(), carte);

    }

    public void carte_n1_3()
    {
        PositionCarte positionCarte = new PositionCarte(-1, 3, "Monde", "Monde");
        Carte carte = mapDeBase(positionCarte, "herbe","","Monde Monde -1 2","Monde Monde 0 3","");

        MainZeldo.listeCarte.put(positionCarte.getPositionText(), carte);

    }

    public void carte_1_2()
    {
        PositionCarte positionCarte = new PositionCarte(1, 2, "Monde", "Monde");
        Carte carte = mapDeBase(positionCarte, "herbe","Monde Monde 0 2","","","Monde Monde 1 3");

        // arbre
        carte.getListeCase().get("" + 0 + " " + 0).setObjetDecor(new Arbre());
//		carte.getListeCase().get("" + 16 + " " + 0).setObjetDecor(new Arbre());

        MainZeldo.listeCarte.put(positionCarte.getPositionText(), carte);

    }

    public void carte_1_3()
    {
        PositionCarte positionCarte = new PositionCarte(1, 3, "Monde", "Monde");
        Carte carte = mapDeBase(positionCarte, "herbe","Monde Monde 0 3","Monde Monde 1 2","","");


        MainZeldo.listeCarte.put(positionCarte.getPositionText(), carte);

    }*//*

}
*/
