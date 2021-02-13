package com.mesi.panels;

import com.mesi.params.Constant;

import javax.swing.*;
import java.awt.*;

public class GameTitle extends JPanel {

    /**********  Attributes  **********/

    private JLabel title = new JLabel("ZELDO");
    private JLabel goToMenu = new JLabel("ESC pour accéder au MENU");

    /**********  Constructors  **********/

    /**
     *
     * Écran titre. C'est le premier écran affichée.
     */

    public GameTitle() {
        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        setBackground(Color.LIGHT_GRAY);
        GroupLayout layout = new GroupLayout(this);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(title)
                        .addGap(10, 10, Short.MAX_VALUE)
                        .addComponent(goToMenu)
                )
                .addGap(10, 10, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(title)
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(goToMenu)
                .addGap(10, 10, Short.MAX_VALUE)
        );

        setLayout(layout);
    }
}
