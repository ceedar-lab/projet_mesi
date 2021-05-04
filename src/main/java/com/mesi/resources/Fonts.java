package com.mesi.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {

    private static final Logger logger = LogManager.getLogger(Fonts.class);

    private static Font florante;

    static {
        try {
            logger.info("Start loading files");

            florante = Font.createFont(Font.TRUETYPE_FONT, checkIfFileExists(new File("src/main/resources/font/florante.ttf")));

            logger.info("Files loaded");
        } catch (FontFormatException | IOException | NullPointerException e) {
            logger.error("Error loading font");
            logger.fatal("<--------------- GAME STOP --------------->");
            System.exit(1);
        }
    }

    public static final Font FLORANTE = florante;

    private static File checkIfFileExists(File file) {
        if (!file.canRead()) logger.error("File doesn't exists : " + file.toString());
        else logger.debug(file.toString() + " loaded");

        return file;
    }

    private Fonts() {
        // Constructeur privé utilisé pour cacher le constructeur implicite crée par Java.
    }
}
