package com.mesi.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {

    private static final Logger logger = LogManager.getLogger(Images.class);

    /** Menu **/
    private static BufferedImage menuBackground;
    private static BufferedImage menuItemLong;
    private static BufferedImage menuItemShort;
    private static BufferedImage title;
    private static BufferedImage titleScreen;
    /** Sprites **/
    private static BufferedImage spriteArmorMetal;
    private static BufferedImage spriteArmorChain;
    private static BufferedImage spriteArmorLeather;
    private static BufferedImage spriteBootsMetal;
    private static BufferedImage spriteBootsLeather;
    private static BufferedImage spriteDagger;
    private static BufferedImage spriteGlovesMetal;
    private static BufferedImage spriteHairBlack;
    private static BufferedImage spriteHairBrown;
    private static BufferedImage spriteHairBlond;
    private static BufferedImage spriteHatLeather;
    private static BufferedImage spriteHatMetal;
    private static BufferedImage spriteHelmetMetal;
    private static BufferedImage spriteHoodChain;
    private static BufferedImage spriteHoodRobe;
    private static BufferedImage spritePantsLeather;
    private static BufferedImage spritePantsMetal;
    private static BufferedImage spriteShield;
    private static BufferedImage spriteShieldCut;
    private static BufferedImage spriteSkirt;
    private static BufferedImage spriteSpear;
    private static BufferedImage spriteSword;
    private static BufferedImage spriteTshirt;
    /** Objets **/
    private static BufferedImage bootsLeather;
    private static BufferedImage bush;
    private static BufferedImage chest;
    private static BufferedImage chestOpen;
    private static BufferedImage dagger;
    private static BufferedImage foliage;
    private static BufferedImage shield;
    private static BufferedImage sword;
    private static BufferedImage trunk;
    /** Maps **/
    private static BufferedImage map1Background;
    private static BufferedImage map1Foreground;
    private static BufferedImage map2Background;
    private static BufferedImage map2Foreground;

    static {
        try {
            logger.info("Start loading files");

            /** Menu **/
            menuBackground = toCompatibleImage(new File("src/main/resources/images/menu/menu-background.png"));
            menuItemLong = toCompatibleImage(new File("src/main/resources/images/menu/menu-item_long.png"));
            menuItemShort = toCompatibleImage(new File("src/main/resources/images/menu/menu-item_short.png"));
            title = toCompatibleImage(new File("src/main/resources/images/menu/title.png"));
            titleScreen = toCompatibleImage(new File("src/main/resources/images/menu/title_screen.jpg"));
            /** Sprites **/
            spriteArmorMetal = toCompatibleImage(new File("src/main/resources/images/sprites/torso-armor_metal.png"));
            spriteArmorChain = toCompatibleImage(new File("src/main/resources/images/sprites/torso-armor_chain.png"));
            spriteArmorLeather = toCompatibleImage(new File("src/main/resources/images/sprites/torso-armor_leather.png"));
            spriteBootsMetal = toCompatibleImage(new File("src/main/resources/images/sprites/feet-boots_metal.png"));
            spriteBootsLeather = toCompatibleImage(new File("src/main/resources/images/sprites/feet-boots_leather.png"));
            spriteDagger = toCompatibleImage(new File("src/main/resources/images/sprites/right_hand-dagger.png"));
            spriteGlovesMetal = toCompatibleImage(new File("src/main/resources/images/sprites/hands-gloves_metal.png"));
            spriteHairBlack = toCompatibleImage(new File("src/main/resources/images/sprites/hair-black.png"));
            spriteHairBrown = toCompatibleImage(new File("src/main/resources/images/sprites/hair-brown.png"));
            spriteHairBlond = toCompatibleImage(new File("src/main/resources/images/sprites/hair-blond.png"));
            spriteHatLeather = toCompatibleImage(new File("src/main/resources/images/sprites/head-hat_leather.png"));
            spriteHatMetal = toCompatibleImage(new File("src/main/resources/images/sprites/head-hat_metal.png"));
            spriteHelmetMetal = toCompatibleImage(new File("src/main/resources/images/sprites/head-helmet_metal.png"));
            spriteHoodChain = toCompatibleImage(new File("src/main/resources/images/sprites/head-hood_chain.png"));
            spriteHoodRobe = toCompatibleImage(new File("src/main/resources/images/sprites/head-hood_robe.png"));
            spritePantsLeather = toCompatibleImage(new File("src/main/resources/images/sprites/legs-pants_leather.png"));
            spritePantsMetal = toCompatibleImage(new File("src/main/resources/images/sprites/legs-pants_metal.png"));
            spriteShield = toCompatibleImage(new File("src/main/resources/images/sprites/left_hand-shield.png"));
            spriteShieldCut = toCompatibleImage(new File("src/main/resources/images/sprites/left_hand-shield_cut.png"));
            spriteSkirt = toCompatibleImage(new File("src/main/resources/images/sprites/legs-skirt.png"));
            spriteSpear = toCompatibleImage(new File("src/main/resources/images/sprites/right_hand-spear.png"));
            spriteSword = toCompatibleImage(new File("src/main/resources/images/sprites/right_hand-sword.png"));
            spriteTshirt = toCompatibleImage(new File("src/main/resources/images/sprites/torso-tshirt.png"));
            /** Objets **/
            bootsLeather = toCompatibleImage(new File("src/main/resources/images/objects/boots-leather.png"));
            bush = toCompatibleImage(new File("src/main/resources/images/objects/bush.png"));
            chest = toCompatibleImage(new File("src/main/resources/images/objects/chest-closed.jpg"));
            chestOpen = toCompatibleImage(new File("src/main/resources/images/objects/chest-open.jpg"));
            dagger = toCompatibleImage(new File("src/main/resources/images/objects/dagger.png"));
            foliage = toCompatibleImage(new File("src/main/resources/images/objects/tree-foliage.png"));
            shield = toCompatibleImage(new File("src/main/resources/images/objects/shield.png"));
            sword = toCompatibleImage(new File("src/main/resources/images/objects/sword.png"));
            trunk = toCompatibleImage(new File("src/main/resources/images/objects/tree-trunk.png"));
            /** Maps **/
            map1Background = toCompatibleImage(new File("src/main/resources/images/maps/map_1-background.jpg"));
            map1Foreground = toCompatibleImage(new File("src/main/resources/images/maps/map_1-foreground.png"));
            map2Background = toCompatibleImage(new File("src/main/resources/images/maps/map_2-background.jpg"));
            map2Foreground = toCompatibleImage(new File("src/main/resources/images/maps/map_2-foreground.png"));

            logger.info("Files loaded");
        } catch (IOException e) {
            logger.error("Error loading picture : " +e.getMessage());
            logger.fatal("<--------------- GAME STOP --------------->");
            System.exit(1);
        }
    }

    /** Menu **/
    public static final BufferedImage MENU_BACKGROUND = menuBackground;
    public static final BufferedImage MENU_ITEM_L = menuItemLong;
    public static final BufferedImage MENU_ITEM_S = menuItemShort;
    public static final BufferedImage TITLE = title;
    public static final BufferedImage TITLE_SCREEN = titleScreen;
    /** Sprites **/
    public static final BufferedImage SP_ARMOR_CHAIN = spriteArmorChain;
    public static final BufferedImage SP_ARMOR_METAL = spriteArmorMetal;
    public static final BufferedImage SP_ARMOR_LEATHER = spriteArmorLeather;
    public static final BufferedImage SP_BOOTS_METAL = spriteBootsMetal;
    public static final BufferedImage SP_BOOTS_LEATHER = spriteBootsLeather;
    public static final BufferedImage SP_DAGGER = spriteDagger;
    public static final BufferedImage SP_GLOVES_METAL = spriteGlovesMetal;
    public static final BufferedImage SP_HAIR_BLACK = spriteHairBlack;
    public static final BufferedImage SP_HAIR_BROWN = spriteHairBrown;
    public static final BufferedImage SP_HAIR_BLOND = spriteHairBlond;
    public static final BufferedImage SP_HAT_LEATHER = spriteHatLeather;
    public static final BufferedImage SP_HAT_METAL = spriteHatMetal;
    public static final BufferedImage SP_HELMET_METAL = spriteHelmetMetal;
    public static final BufferedImage SP_HOOD_CHAIN = spriteHoodChain;
    public static final BufferedImage SP_HOOD_ROBE = spriteHoodRobe;
    public static final BufferedImage SP_PANTS_LEATHER = spritePantsLeather;
    public static final BufferedImage SP_PANTS_METAL = spritePantsMetal;
    public static final BufferedImage SP_SHIELD = spriteShield;
    public static final BufferedImage SP_SHIELD_CUT = spriteShieldCut;
    public static final BufferedImage SP_SKIRT = spriteSkirt;
    public static final BufferedImage SP_SPEAR = spriteSpear;
    public static final BufferedImage SP_SWORD = spriteSword;
    public static final BufferedImage SP_TSHIRT = spriteTshirt;
    /** Objets **/
    public static final BufferedImage BOOTS_LEATHER = bootsLeather;
    public static final BufferedImage BUSH = bush;
    public static final BufferedImage CHEST = chest;
    public static final BufferedImage CHEST_OPEN = chestOpen;
    public static final BufferedImage DAGGER = dagger;
    public static final BufferedImage FOLIAGE = foliage;
    public static final BufferedImage SHIELD = shield;
    public static final BufferedImage SWORD = sword;
    public static final BufferedImage TRUNK = trunk;
    /** Maps **/
    public static final BufferedImage MAP_1_BG = map1Background;
    public static final BufferedImage MAP_1_FG = map1Foreground;
    public static final BufferedImage MAP_2_BG = map2Background;
    public static final BufferedImage MAP_2_FG = map2Foreground;

    /**
     * Test si l'image est optimisé pour le système, et la convertit dans le cas contraire.
     * @param file
     * @return
     */
    public static BufferedImage toCompatibleImage(File file) throws IOException {
        if (!file.canRead()) logger.error("File doesn't exists : " + file.toString());
        else logger.debug(file.toString() + " loaded");
        BufferedImage image = ImageIO.read(file);
        GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        /**  Retourne l'image si elle est déjà compatible et optimisée pour le système  **/
        if (image.getColorModel().equals(config.getColorModel())) {
            return image;
        }

        /**  Si elle n'est pas optimisée, conversion en image qui l'est  **/
        final BufferedImage newImage = config.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
        final Graphics2D g2d = (Graphics2D) newImage.getGraphics();

        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return newImage;
    }

    private Images() {
        // Constructeur privé utilisé pour cacher le constructeur implicite crée par Java.
    }
}
