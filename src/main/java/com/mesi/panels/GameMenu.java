package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.panels.maps.MapGenerator;
import com.mesi.saves.GameInit;
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


public class GameMenu extends JDialog {

    private static final long serialVersionUID = 1L;

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
        System.out.println("menu");
        //setOpaque(false);
        /*setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);*/
        setSize(300, 400);
        setUndecorated(true);
        setModal(false);
        setFocusable(true);

        add(getPanelPrinc());

        setLocationRelativeTo(null);

        setVisible(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode() + " Menu");

                if (e.getKeyCode() == KeyMap.ESCAPE) {
                    dispose();
                }

                if (e.getKeyCode() == KeyMap.UP) {
                    indexSelection--;
                    if (indexSelection < 0) {
                        indexSelection = listeBtn.size() - 1;
                    }
                    selectionButton(indexSelection);
                }

                if (e.getKeyCode() == KeyMap.DOWN) {
                    indexSelection++;
                    if (indexSelection >= listeBtn.size()) {
                        indexSelection = 0;
                    }
                    selectionButton(indexSelection);
                }

                if (e.getKeyCode() == KeyMap.ENTER) {
                    listeBtn.get(indexSelection).doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        initBtn();

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
                        .addComponent(getBtnRetourAuJeu(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnEnregistrer(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(btnCharger, largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnMenuPrincipal(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnQuitter(), largeurBtn, largeurBtn, largeurBtn)
                )
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(getBtnRetourAuJeu(), hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnEnregistrer, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnCharger, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(getBtnMenuPrincipal(), hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(getBtnQuitter(), hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(10, 10, Short.MAX_VALUE)
        );

        panelPrinc.setLayout(layout);

        return panelPrinc;
    }

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

    public JButton getBtnMenuPrincipal() {
        btnMenuPrincipal.setFocusable(false);
        btnMenuPrincipal.setBackground(Color.LIGHT_GRAY);

        btnMenuPrincipal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.pause = false;
                dispose();
                MainZeldo.state = MainZeldo.GameState.START_MENU;
                MainZeldo.onStateChange = true;
                GameInit.RestartGame();
            }
        });

        return btnMenuPrincipal;
    }

    public JButton getBtnEnregistrer() {
        btnEnregistrer.setFocusable(false);
        btnEnregistrer.setBackground(Color.LIGHT_GRAY);

        btnEnregistrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new GameInit();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        return btnEnregistrer;
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

    public void initBtn() {
        listeBtn.clear();

        listeBtn.add(btnRetourAuJeu);
        listeBtn.add(btnEnregistrer);
        listeBtn.add(btnCharger);
        listeBtn.add(btnMenuPrincipal);
        listeBtn.add(btnQuitter);

        btnRetourAuJeu.setBackground(Color.GREEN);
    }

    public void selectionButton(int buttonNumber) {
        for (int i = 0; i < listeBtn.size(); i++) {
            listeBtn.get(i).setBackground(Color.LIGHT_GRAY);
        }

        listeBtn.get(buttonNumber).setBackground(Color.GREEN);
    }
}