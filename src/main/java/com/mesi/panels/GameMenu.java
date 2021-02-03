package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.params.Backup;
import com.mesi.params.Couleur;
import com.mesi.params.KeyMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameMenu extends JDialog {

    /**********  Attributes  **********/

    private JPanel panelPrinc = new JPanel();

    private JButton btnRetourAuJeu = new JButton("RETOUR AU JEU");
    private JButton btnEnregistrer = new JButton("ENREGISTRER");
    private JButton btnCharger = new JButton("CHARGER");
    private JButton btnMenuPrincipal = new JButton("MENU PRINCIPAL");
    private JButton btnQuitter = new JButton("QUITTER");

    private ArrayList<JButton> listeBtn = new ArrayList<JButton>();

    private int indexSelection = 0;

    /**********  Constructors  **********/

    public GameMenu() {
        //setOpaque(false);
        /*setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);*/
        setSize(300, 400);
        setUndecorated(true);
        setModal(false);
        setFocusable(true);
        setLocationRelativeTo(null);

        add(getPanelPrinc());

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyMap.ESCAPE) {
                    Game.pause = false;
                    dispose();
                }

                if (e.getKeyCode() == KeyMap.UP) {
                    indexSelection--;
                    if (indexSelection < 0) {
                        indexSelection = listeBtn.size() - 1;
                    }
                    selectButton(indexSelection);
                }

                if (e.getKeyCode() == KeyMap.DOWN) {
                    indexSelection++;
                    if (indexSelection >= listeBtn.size()) {
                        indexSelection = 0;
                    }
                    selectButton(indexSelection);
                }

                if (e.getKeyCode() == KeyMap.ENTER) {
                    listeBtn.get(indexSelection).doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        initButtonList();

        setVisible(true);

    }

    /**********  Methods  **********/

    /**
     * Configuration du menu in game.
     *
     * @return
     */
    public JPanel getPanelPrinc() {
        panelPrinc.setBackground(Couleur.Brown_1);

        GroupLayout layout = new GroupLayout(panelPrinc);

        int largeurBtn = 140;
        int hauteurBtn = 20;

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(getBtnRetourAuJeu(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnEnregistrer(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnCharger(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnMenuPrincipal(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnQuitter(), largeurBtn, largeurBtn, largeurBtn)
                )
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(btnRetourAuJeu, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnEnregistrer, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnCharger, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnMenuPrincipal, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnQuitter, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(10, 10, Short.MAX_VALUE)
        );

        panelPrinc.setLayout(layout);

        return panelPrinc;
    }

    /**
     * Ferme le menu et retourne à la partie.
     *
     * @return
     */
    public JButton getBtnRetourAuJeu() {
        btnRetourAuJeu.setFocusable(false);
        btnRetourAuJeu.setBackground(Color.LIGHT_GRAY);

        btnRetourAuJeu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.pause = false;
                dispose();
            }
        });

        return btnRetourAuJeu;
    }

    /**
     * Retour au menu principal.
     *
     * @return
     */
    public JButton getBtnMenuPrincipal() {
        btnMenuPrincipal.setFocusable(false);
        btnMenuPrincipal.setBackground(Color.LIGHT_GRAY);

        btnMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.pause = false;
                Game.killThread = true;
                dispose();
                MainZeldo.state = MainZeldo.GameState.START_MENU;
                MainZeldo.onStateChange = true;
            }
        });

        return btnMenuPrincipal;
    }

    /**
     * Enregistre la partie en cours.
     *
     * @return
     */
    public JButton getBtnEnregistrer() {
        btnEnregistrer.setFocusable(false);
        btnEnregistrer.setBackground(Color.LIGHT_GRAY);

        btnEnregistrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new Backup().save("save_1");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        return btnEnregistrer;
    }

    /**
     * Charge la dernière partie sauvegardée.
     *
     * @return
     */
    public JButton getBtnCharger() {
        btnCharger.setFocusable(false);
        btnCharger.setBackground(Color.LIGHT_GRAY);

        btnCharger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.pause = false;
                Game.killThread = true;
                try {
                    TimeUnit.MILLISECONDS.sleep(15);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                dispose();
                new Backup().load("save_1");
            }
        });

        return btnCharger;
    }

    /**
     * Retourne à Windows.
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
     * Initialise la liste des boutons.
     */
    public void initButtonList() {
        listeBtn.clear();

        listeBtn.add(btnRetourAuJeu);
        listeBtn.add(btnEnregistrer);
        listeBtn.add(btnCharger);
        listeBtn.add(btnMenuPrincipal);
        listeBtn.add(btnQuitter);

        btnRetourAuJeu.setBackground(Color.GREEN);
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