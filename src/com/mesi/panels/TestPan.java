/*
package com.mesi.display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestPan extends JPanel {

    private Integer TILE_SIZE = 64;
    public Graphics t;

    BufferedImage foliage = ImageIO.read(new File("res/images/tree-foliage.png"));

    public TestPan() throws IOException {
        setBounds(0, 0, 1280, 768);
        //setBackground(new Color(0,0,0,0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(foliage, TILE_SIZE + TILE_SIZE/2, 4*TILE_SIZE + TILE_SIZE/2, this);
    }
}
*/
