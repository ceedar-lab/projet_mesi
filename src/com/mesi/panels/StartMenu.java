package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.params.Constant;
import com.mesi.params.Couleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JPanel {

    /**********  Attributes  **********/

    private JButton btnNouvellePartie = new JButton("NOUVELLE PARTIE");
    private JButton btnContinuer = new JButton("CONTINUER");
    private JButton btnOptions = new JButton("OPTIONS");
    private JButton btnQuitter = new JButton("QUITTER");

    /**********  Constructors  **********/

    /**
     * Menu du jeu.
     */
    public StartMenu() {
        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
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

    /**********  Methods  **********/

    /**
     * Démarre une nouvelle partie.
     * @return
     */
    public JButton getBtnNouvellePartie() {
        btnNouvellePartie.setFocusable(false);
        btnNouvellePartie.setBackground(Color.LIGHT_GRAY);

        btnNouvellePartie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainZeldo.state = MainZeldo.GameState.MAP_0_0;
                MainZeldo.onStateChange = true;
            }
        });

        return btnNouvellePartie;
    }

    /**
     * Continue la partie sauvegardée.
     * @return
     */
    public JButton getBtnContinuer() {
        /*btnContinuer.setFocusable(false);
        btnContinuer.setBackground(Color.LIGHT_GRAY);

        if (btnContinuer.getActionListeners().length == 0) {
            btnContinuer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }*/

        return btnContinuer;
    }

    /**
     * Entre dans les options du jeu.
     * @return
     */
    public JButton getBtnOptions() {
        /*btnOptions.setFocusable(false);
        btnContinuer.setBackground(Color.LIGHT_GRAY);

        if (btnOptions.getActionListeners().length == 0) {
            btnOptions.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }*/

        return btnOptions;
    }

    /**
     * Quitte le jeu.
     * @return
     */
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
