package com.mesi.params;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {

    public static BufferedImage BOOTS_LEATHER;
    public static BufferedImage BUSH;
    public static BufferedImage CHEST;
    public static BufferedImage CHEST_OPEN;
    public static BufferedImage DAGGER;
    public static BufferedImage FOLIAGE;
    public static BufferedImage SHIELD;
    public static BufferedImage SWORD;
    public static BufferedImage TRUNK;
    public static BufferedImage MAP_1_BG;
    public static BufferedImage MAP_1_FG;
    public static BufferedImage MAP_2_BG;
    public static BufferedImage MAP_2_FG;

    static {
        try {
            BOOTS_LEATHER = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/boots-leather.png")));
            BUSH = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/bush.png")));
            CHEST = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/chest-closed.jpg")));
            CHEST_OPEN = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/chest-open.jpg")));
            DAGGER = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/dagger.png")));
            FOLIAGE = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/tree-foliage.png")));
            SHIELD = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/shield.png")));
            SWORD = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/sword.png")));
            TRUNK = toCompatibleImage(ImageIO.read(new File("src/main/resources/images/objects/tree-trunk.png")));
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
