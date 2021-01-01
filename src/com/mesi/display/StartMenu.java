package com.mesi.display;

import com.mesi.MainZeldo;
import com.mesi.params.Couleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JPanel {

    private JButton btnNouvellePartie = new JButton("NOUVELLE PARTIE");
    private JButton btnContinuer = new JButton("CONTINUER");
    private JButton btnQuitter = new JButton("QUITTER");
    
    public StartMenu() {
        setBounds(0, 0, 1280, 768);
        setBackground(Couleur.Brown_1);

        GroupLayout layout = new GroupLayout(this);

        int largeurBtn = 140;
        int hauteurBtn = 20;

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(getBtnNouvellePartie(),largeurBtn,largeurBtn,largeurBtn)
                .addComponent(getBtnContinuer(),largeurBtn,largeurBtn,largeurBtn)
                .addComponent(getBtnQuitter(),largeurBtn,largeurBtn,largeurBtn)
            )
            .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGap(10, 10, Short.MAX_VALUE)
            .addComponent(getBtnNouvellePartie(),hauteurBtn, hauteurBtn,hauteurBtn)
            .addGap(20)
            .addComponent(getBtnContinuer(),hauteurBtn, hauteurBtn,hauteurBtn)
            .addGap(20)
            .addComponent(getBtnQuitter(),hauteurBtn, hauteurBtn,hauteurBtn)
            .addGap(10, 10, Short.MAX_VALUE)
        );

        setLayout(layout);
    }

    public JButton getBtnNouvellePartie() {
        btnNouvellePartie.setFocusable(false);
        btnNouvellePartie.setBackground(Color.LIGHT_GRAY);

        btnNouvellePartie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainZeldo.state = MainZeldo.GameState.NEW_GAME;
                MainZeldo.onStateChange = 1;
            }
        });

        return btnNouvellePartie;
    }

    public JButton getBtnContinuer() {
        /*btnContinuer.setFocusable(false);
        btnContinuer.setBackground(Color.LIGHT_GRAY);

        if (btnContinuer.getActionListeners().length == 0) {
            btnContinuer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MainZeldo.fen.continuerPartie();
                    dispose();
                }
            });
        }*/

        return btnContinuer;
    }

    public JButton getBtnQuitter() {
        btnQuitter.setFocusable(false);
        btnQuitter.setBackground(Color.LIGHT_GRAY);

        btnQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return btnQuitter;
    }
}
