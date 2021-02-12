package com.mesi.params;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {

    /** Sprites **/
    public static BufferedImage SP_ARMOR_METAL;
    public static BufferedImage SP_ARMOR_CHAIN;
    public static BufferedImage SP_ARMOR_LEATHER;
    public static BufferedImage SP_BOOTS_METAL;
    public static BufferedImage SP_BOOTS_LEATHER;
    public static BufferedImage SP_DAGGER;
    public static BufferedImage SP_GLOVES_METAL;
    public static BufferedImage SP_HAIR_BLACK;
    public static BufferedImage SP_HAIR_BROWN;
    public static BufferedImage SP_HAIR_BLOND;
    public static BufferedImage SP_HAT_LEATHER;
    public static BufferedImage SP_HAT_METAL;
    public static BufferedImage SP_HELMET_METAL;
    public static BufferedImage SP_HOOD_CHAIN;
    public static BufferedImage SP_HOOD_ROBE;
    public static BufferedImage SP_PANTS_LEATHER;
    public static BufferedImage SP_PANTS_METAL;
    public static BufferedImage SP_SHIELD;
    public static BufferedImage SP_SHIELD_CUT;
    public static BufferedImage SP_SKIRT;
    public static BufferedImage SP_SPEAR;
    public static BufferedImage SP_SWORD;
    public static BufferedImage SP_TSHIRT;
    /** Objets **/
    public static BufferedImage BOOTS_LEATHER;
    public static BufferedImage BUSH;
    public static BufferedImage CHEST;
    public static BufferedImage CHEST_OPEN;
    public static BufferedImage DAGGER;
    public static BufferedImage FOLIAGE;
    public static BufferedImage SHIELD;
    public static BufferedImage SWORD;
    public static BufferedImage TRUNK;
    /** Maps **/
    public static BufferedImage MAP_1_BG;
    public static BufferedImage MAP_1_FG;
    public static BufferedImage MAP_2_BG;
    public static BufferedImage MAP_2_FG;

    static {
        try {
            /** Sprites **/
            SP_ARMOR_METAL = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/torso-armor_metal.png")));
            SP_ARMOR_CHAIN = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/torso-armor_chain.png")));
            SP_ARMOR_LEATHER = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/torso-armor_leather.png")));
            SP_BOOTS_METAL = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/feet-boots_metal.png")));
            SP_BOOTS_LEATHER = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/feet-boots_leather.png")));
            SP_DAGGER = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/right_hand-dagger.png")));
            SP_GLOVES_METAL = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/hands-gloves_metal.png")));
            SP_HAIR_BLACK = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/hair-black.png")));
            SP_HAIR_BROWN = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/hair-brown.png")));
            SP_HAIR_BLOND = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/hair-blond.png")));
            SP_HAT_LEATHER = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/head-hat_leather.png")));
            SP_HAT_METAL = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/head-hat_metal.png")));
            SP_HELMET_METAL = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/head-helmet_metal.png")));
            SP_HOOD_CHAIN = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/head-hood_chain.png")));
            SP_HOOD_ROBE = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/head-hood_robe.png")));
            SP_PANTS_LEATHER = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/legs-pants_leather.png")));
            SP_PANTS_METAL = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/legs-pants_metal.png")));
            SP_SHIELD = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/left_hand-shield.png")));
            SP_SHIELD_CUT = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/left_hand-shield_cut.png")));
            SP_SKIRT = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/legs-skirt.png")));
            SP_SPEAR = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/right_hand-spear.png")));
            SP_SWORD = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/right_hand-sword.png")));
            SP_TSHIRT = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/sprites/torso-tshirt.png")));
            /** Objets **/
            BOOTS_LEATHER = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/boots-leather.png")));
            BUSH = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/bush.png")));
            CHEST = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/chest-closed.jpg")));
            CHEST_OPEN = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/chest-open.jpg")));
            DAGGER = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/dagger.png")));
            FOLIAGE = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/tree-foliage.png")));
            SHIELD = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/shield.png")));
            SWORD = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/sword.png")));
            TRUNK = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/tree-trunk.png")));
            /** Maps **/
            MAP_1_BG = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/maps/map_1-background.jpg")));
            MAP_1_FG = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/maps/map_1-foreground.png")));
            MAP_2_BG = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/maps/map_2-background.jpg")));
            MAP_2_FG = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/maps/map_2-foreground.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test si l'image est optimisé pour le système, et la convertit dans le cas contraire.
     * @param image
     * @return
     */
    public static BufferedImage toCompatibleImage(BufferedImage image) {
        GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        /**  Retourne l'image si elle est déjà compatible et optimisée pour le système  **/
        if (image.getColorModel().equals(config.getColorModel())) {
            return image;
        }

        /**  Si elle n'est pas optimisée, conversion en image qui l'est  **/
        final BufferedImage new_image = config.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
        final Graphics2D g2d = (Graphics2D) new_image.getGraphics();

        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return new_image;
    }
}
