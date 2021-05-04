package com.mesi.panels;

import com.mesi.params.Constant;
import com.mesi.resources.Fonts;
import com.mesi.resources.Images;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class GameTitle extends JPanel {

    /**********  Attributes  **********/

    private static final Logger logger = LogManager.getLogger(GameTitle.class);

    private final Integer TITLE_WIDTH = 600;
    private final Integer TITLE_HEIGHT = 300;

    private JLabel title = new JLabel(new ImageIcon(Images.TITLE.getScaledInstance(TITLE_WIDTH, TITLE_HEIGHT, Image.SCALE_SMOOTH)));
    private JLabel goToMenu = new JLabel("ESC pour acceder au MENU");

    /**********  Constructors  **********/

    /**
     *
     * Écran titre. C'est le premier écran affichée.
     */

    public GameTitle() {
        title.setBounds(0, 0, TITLE_WIDTH, TITLE_HEIGHT);

        goToMenu.setFont(Fonts.FLORANTE.deriveFont(28f).deriveFont(Font.BOLD));
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
                .addGap(150)
        );

        setLayout(layout);

        logger.info("User in GAME_TITLE");
    }

    /**********  Methods  **********/

    /**
     * Affiche l'image de fond de l'écran titre.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(Images.TITLE_SCREEN, 0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, this);
    }
}
