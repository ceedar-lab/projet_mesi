package com.mesi.panels;

import com.mesi.params.Constant;
import com.mesi.params.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

public class GameTitle extends JPanel {

    /**********  Attributes  **********/

    private JLabel title = new JLabel(new ImageIcon(Images.TITLE.getScaledInstance(600, 300, Image.SCALE_SMOOTH)));
    private JLabel goToMenu = new JLabel("ESC pour acceder au MENU");

    //private JPanel title = new JPanel();

    /**********  Constructors  **********/

    /**
     *
     * Écran titre. C'est le premier écran affichée.
     */

    public GameTitle() {
        Font custom = null;
        try {
            custom = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/font/florante.ttf")).deriveFont(28f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(custom);
        } catch (IOException |FontFormatException e) {
            //Handle exception
        }

        title.setBounds(0, 0, 600, 300);
        goToMenu.setFont(custom.deriveFont(Font.BOLD));
        //goToMenu.setForeground(Color.black);

        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        setBackground(Color.LIGHT_GRAY);
        GroupLayout layout = new GroupLayout(this);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(title)
                        .addGap(10, 10, Short.MAX_VALUE)
                        .addComponent(goToMenu)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(120)
                .addComponent(title)
                .addGap(200, 200, Short.MAX_VALUE)
                .addComponent(goToMenu)
                .addGap(150, 150, Short.MAX_VALUE)
        );

        setLayout(layout);
    }

//    private JPanel title() {
//        title.setBackground(Color.YELLOW);
//        Graphics g = getGraphics();
//        title.paintComponents(g);
//        g.drawImage(Images.TITLE, 0, 0, 600, 300, this);
//        g.dispose();
//        return title;
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(Images.TITLE_SCREEN, 0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, this);
    }
}
