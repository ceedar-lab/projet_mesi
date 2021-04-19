package com.mesi.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Sounds {

    private static final Logger logger = LogManager.getLogger(Sounds.class);

    private static File attack;
    private static File chest;
    private static File equipClothes;
    private static File equipWeapon;
    private static File menu;
    private static File menuClic;
    private static File musicForest;
    private static File musicStart;
    private static File musicTent;

    static {
        logger.info("Start loading files");

        attack = checkIfFileExists(new File("src/main/resources/sounds/effects/attack.ogg"));
        chest = checkIfFileExists(new File("src/main/resources/sounds/effects/chest.ogg"));
        equipClothes = checkIfFileExists(new File("src/main/resources/sounds/effects/equip_clothes.ogg"));
        equipWeapon = checkIfFileExists(new File("src/main/resources/sounds/effects/equip_weapon.ogg"));
        menu = checkIfFileExists(new File("src/main/resources/sounds/effects/menu.ogg"));
        menuClic = checkIfFileExists(new File("src/main/resources/sounds/effects/menu_clic.ogg"));
        musicForest = checkIfFileExists(new File("src/main/resources/sounds/music/forest.ogg"));
        musicStart = checkIfFileExists(new File("src/main/resources/sounds/music/start.ogg"));
        musicTent = checkIfFileExists(new File("src/main/resources/sounds/music/tent.ogg"));

        logger.info("Files loaded");
    }

    /** Effects **/
    public static final File ATTACK = attack;
    public static final File CHEST = chest;
    public static final File EQUIP_WEAPON = equipWeapon;
    public static final File EQUIP_CLOTHES = equipClothes;
    public static final File MENU = menu;
    public static final File MENU_CLIC = menuClic;
    /** Music **/
    public static final File FOREST = musicForest;
    public static final File TENT = musicTent;
    public static final File GENERIC_START = musicStart;

    private static File checkIfFileExists(File file) {
        if (!file.canRead()) logger.error("File doesn't exists : " + file.toString());
        else logger.debug(file.toString() + " loaded");

        return file;
    }

    private Sounds() {
        // Constructeur privé utilisé pour cacher le constructeur implicite crée par Java.
    }
}
