package com.mesi.panels;

import com.mesi.params.Couleur;

import javax.swing.*;

public class GameTitle extends JPanel {

    private JLabel gameTitle = new JLabel("ZELDO");
    private JLabel goToMenu = new JLabel("ESC pour acceder au MENU");

    public GameTitle() {
        setBounds(0, 0, 1280, 768);
        setBackground(Couleur.Brown_2);
        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGap(10, 10, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(gameTitle)
                .addGap(10,10,Short.MAX_VALUE)
                .addComponent(goToMenu)
            )
            .addGap(10, 10, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGap(10, 10, Short.MAX_VALUE)
            .addComponent(gameTitle)
            .addGap(10, 10, Short.MAX_VALUE)
            .addComponent(goToMenu)
            .addGap(10, 10, Short.MAX_VALUE)
        );

        setLayout(layout);
    }
}
