package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.params.Constant;
import com.mesi.params.Couleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameMenu extends JDialog {

    private JPanel panelPrinc = new JPanel();

    private JButton btnNouvellePartie = new JButton("NOUVELLE PARTIE");
    private JButton btnEnregistrer = new JButton("ENREGISTRER");
    private JButton btnCharger = new JButton("CHARGER");
    private JButton btnMenuPrincipal = new JButton("MENU PRINCIPAL");
    private JButton btnQuitter = new JButton("QUITTER");

    private ArrayList<JButton> listeBtn = new ArrayList<JButton>();

    private int indexSelection = 0;

    /**********  Constructors  **********/

    public GameMenu() {
        /*setOpaque(false);
        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);*/
        setSize(300, 400);
        setUndecorated(true);
        setModal(true);

        add(getPanelPrinc());

        setLocationRelativeTo(null);

        setVisible(true);

    }

    /**********  Methods  **********/

    public JPanel getPanelPrinc() {
        panelPrinc.setBackground(Couleur.Brown_1);

        GroupLayout layout = new GroupLayout(panelPrinc);

        int largeurBtn = 140;
        int hauteurBtn = 20;

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(btnNouvellePartie,largeurBtn,largeurBtn,largeurBtn)
                        .addComponent(btnEnregistrer,largeurBtn,largeurBtn,largeurBtn)
                        .addComponent(btnCharger,largeurBtn,largeurBtn,largeurBtn)
                        .addComponent(btnMenuPrincipal,largeurBtn,largeurBtn,largeurBtn)
                        .addComponent(getBtnQuitter(),largeurBtn,largeurBtn,largeurBtn)
                )
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(btnNouvellePartie,hauteurBtn, hauteurBtn,hauteurBtn)
                .addGap(20)
                .addComponent(btnEnregistrer,hauteurBtn, hauteurBtn,hauteurBtn)
                .addGap(20)
                .addComponent(btnCharger,hauteurBtn, hauteurBtn,hauteurBtn)
                .addGap(20)
                .addComponent(btnMenuPrincipal,hauteurBtn, hauteurBtn,hauteurBtn)
                .addGap(20)
                .addComponent(getBtnQuitter(),hauteurBtn, hauteurBtn,hauteurBtn)
                .addGap(10, 10, Short.MAX_VALUE)
        );

        panelPrinc.setLayout(layout);

        return panelPrinc;
    }

    public JButton getBtnQuitter() {
        btnQuitter.setFocusable(false);
        btnQuitter.setBackground(Color.LIGHT_GRAY);

        btnQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.pause = false;
                dispose();
            }
        });

        return btnQuitter;
    }
}