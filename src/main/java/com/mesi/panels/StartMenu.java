package com.mesi.panels;

import com.mesi.params.Constant;
import com.mesi.params.Couleur;
import com.mesi.params.Backup;
import com.mesi.params.KeyMap;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class StartMenu extends JPanel {

    /**********  Attributes  **********/

    private JButton btnNouvellePartie = new JButton("NOUVELLE PARTIE");
    private JButton btnContinuer = new JButton("CONTINUER");
    private JButton btnOptions = new JButton("OPTIONS");
    private JButton btnQuitter = new JButton("QUITTER");

    private ArrayList<JButton> listeBtn = new ArrayList<JButton>() {{
        add(btnNouvellePartie);
        add(btnContinuer);
        add(btnOptions);
        add(btnQuitter);
    }};

    private int indexSelection = 0;

    /**********  Constructors  **********/

    /**
     * Menu du jeu.
     */
    public StartMenu() throws IOException, ParseException {
        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        setBackground(Couleur.Brown_1);

        GroupLayout layout = new GroupLayout(this);

        int largeurBtn = 140;
        int hauteurBtn = 20;

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(getBtnNouvellePartie(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnContinuer(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(btnOptions, largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnQuitter(), largeurBtn, largeurBtn, largeurBtn)
                )
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(btnNouvellePartie, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnContinuer, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnOptions, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnQuitter, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(10, 10, Short.MAX_VALUE)
        );

        setLayout(layout);

        btnNouvellePartie.setBackground(Color.GREEN);
    }

    /**********  Methods  **********/

    /**
     * Démarre une nouvelle partie.
     *
     * @return
     */
    public JButton getBtnNouvellePartie() {
        btnNouvellePartie.setFocusable(false);
        btnNouvellePartie.setBackground(Color.LIGHT_GRAY);

        btnNouvellePartie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Backup().startNewGame();
            }
        });

        return btnNouvellePartie;
    }

    /**
     * Continue la partie sauvegardée.
     *
     * @return
     */
    public JButton getBtnContinuer() {
        btnContinuer.setFocusable(false);
        btnContinuer.setBackground(Color.LIGHT_GRAY);

        btnContinuer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Backup().load("save_1");
            }
        });

        return btnContinuer;
    }

    /**
     * Entre dans les options du jeu.
     *
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
     *
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

    /**
     * Actions effectuées lorsqu'une touche est pressée.
     *
     * @param keyCode
     */
    public void onKeyPressed(int keyCode) {
        if (keyCode == KeyMap.UP) {
            indexSelection--;
            if (indexSelection < 0) {
                indexSelection = listeBtn.size() - 1;
            }
            selectButton(indexSelection);
        }

        if (keyCode == KeyMap.DOWN) {
            indexSelection++;
            if (indexSelection >= listeBtn.size()) {
                indexSelection = 0;
            }
            selectButton(indexSelection);
        }

        if (keyCode == KeyMap.ENTER) {
            listeBtn.get(indexSelection).doClick();
        }
    }

    /**
     * Fais défiler le sélecteur lorsqu'on appuie sur les flèches de direction.
     *
     * @param buttonNumber
     */
    public void selectButton(int buttonNumber) {
        for (int i = 0; i < listeBtn.size(); i++) {
            listeBtn.get(i).setBackground(Color.LIGHT_GRAY);
        }

        listeBtn.get(buttonNumber).setBackground(Color.GREEN);
    }
}