package com.mesi.params;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {


    public static BufferedImage TRUNK;
    public static BufferedImage FOLIAGE;
    public static BufferedImage BUSH;
    public static BufferedImage CHEST;


    public Images() {
        try {
            FOLIAGE = ImageIO.read(new File("res/images/tree-foliage.png"));

            TRUNK = ImageIO.read(new File("res/images/tree-trunk.png"));

            BUSH = ImageIO.read(new File("res/images/bush.png"));

            CHEST = ImageIO.read(new File("res/images/chest-closed.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
