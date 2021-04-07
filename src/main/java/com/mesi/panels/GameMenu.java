package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.params.Backup;
import com.mesi.params.Constant;
import com.mesi.params.Images;
import com.mesi.params.KeyMap;
import com.mesi.sound.Player;
import com.mesi.sound.Sounds;
import org.apache.log4j.Logger;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameMenu extends JDialog {

    /**********  Attributes  **********/

    private Font custom;

    private static Logger logger = Logger.getLogger(GameMenu.class);

    private JPanel panelPrinc = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            setOpaque(false);
            setBackground(Color.BLACK);
            g.drawImage(Images.GAME_MENU, 0, 0, 360, 480, this);
        }
    };

    private JButton btnRetourAuJeu = new JButton("RETOUR AU JEU");
    private JButton btnEnregistrer = new JButton("ENREGISTRER");
    private JButton btnCharger = new JButton("CHARGER");
    private JButton btnInventory = new JButton("INVENTAIRE");
    private JButton btnMenuPrincipal = new JButton("MENU PRINCIPAL");
    private JButton btnQuitter = new JButton("QUITTER");

    private ArrayList<JButton> listeBtn = new ArrayList<>();

    private int indexSelection = 0;

    /**********  Constructors  **********/

    public GameMenu() {
        try {
            custom = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/font/florante.ttf")).deriveFont(28f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(custom);
        } catch (IOException |FontFormatException e) {
            //Handle exception
        }

        setSize(360, 480);
        getRootPane().setOpaque(false);
        setUndecorated(true);
        setModal(false);
        setFocusable(true);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));

        listeBtn.add(btnRetourAuJeu);
        listeBtn.add(btnEnregistrer);
        listeBtn.add(btnCharger);
        listeBtn.add(btnInventory);
        listeBtn.add(btnMenuPrincipal);
        listeBtn.add(btnQuitter);

        for (int i = 0; i < listeBtn.size(); i++) {
            listeBtn.get(i).setFocusable(false);
            listeBtn.get(i).setBackground(Color.LIGHT_GRAY);
            listeBtn.get(i).setFont(custom.deriveFont(15f));
            listeBtn.get(i).setOpaque(false);
            listeBtn.get(i).setBorderPainted(false);
        }

        add(getPanelPrinc());

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Méthode inutilisée
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyMap.ESCAPE) {
                    Game.setPause(false);
                    dispose();
                }

                if (e.getKeyCode() == KeyMap.UP) {
                    new Player(Sounds.MENU, false);
                    indexSelection--;
                    if (indexSelection < 0) {
                        indexSelection = listeBtn.size() - 1;
                    }
                    selectButton(indexSelection);
                }

                if (e.getKeyCode() == KeyMap.DOWN) {
                    new Player(Sounds.MENU, false);
                    indexSelection++;
                    if (indexSelection >= listeBtn.size()) {
                        indexSelection = 0;
                    }
                    selectButton(indexSelection);
                }

                if (e.getKeyCode() == KeyMap.ENTER) {
                    new Player(Sounds.MENU_CLIC, false);
                    listeBtn.get(indexSelection).doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Méthode inutilisée
            }
        });

        setVisible(true);

//        btnRetourAuJeu.setOpaque(true);
//        btnRetourAuJeu.setBackground(Color.LIGHT_GRAY.brighter());
        btnRetourAuJeu.setHorizontalTextPosition(SwingConstants.CENTER);
        btnRetourAuJeu.setForeground(Color.LIGHT_GRAY.brighter());
        btnRetourAuJeu.setIcon( new ImageIcon("src/main/resources/images/menu/menu_item.png"));
    }

    /**********  Methods  **********/

    /**
     * Configuration du menu in game.
     *
     * @return
     */
    public JPanel getPanelPrinc() {
        GroupLayout layout = new GroupLayout(panelPrinc);

        int largeurBtn = 220;
        int hauteurBtn = 40;

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(getBtnRetourAuJeu(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnEnregistrer(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnCharger(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnInventory(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnMenuPrincipal(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnQuitter(), largeurBtn, largeurBtn, largeurBtn)
                )
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(btnRetourAuJeu, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(15)
                .addComponent(btnEnregistrer, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(15)
                .addComponent(btnCharger, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(15)
                .addComponent(btnInventory, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(15)
                .addComponent(btnMenuPrincipal, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(15)
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

        btnRetourAuJeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.setPause(false);
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
        btnMenuPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.setPause(false);
                Game.setKillThread(true);
                dispose();
                Game.music.stop();
                MainZeldo.generic = new Player(Sounds.GENERIC_START, true);
                MainZeldo.setGameState(MainZeldo.GameState.GAME_TITLE);
                MainZeldo.setGameStateChange(true);
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
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Backup().save("save_1");
                } catch (IOException ioException) {
                    logger.error("Erreur lors de la sauvegarde");
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
        btnCharger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.setPause(false);
                Game.setKillThread(true);
                dispose();
                new Backup().load("save_1");
            }
        });

        return btnCharger;
    }

    /**
     * Ouvre l'inventaire.
     *
     * @return
     */
    public JButton getBtnInventory() {
        btnInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Inventory();
            }
        });

        return btnInventory;
    }

    /**
     * Retourne à Windows.
     *
     * @return
     */
    public JButton getBtnQuitter() {
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return btnQuitter;
    }

    /**
     * Fais défiler le sélecteur lorsqu'on appuie sur les flèches de direction.
     *
     * @param buttonNumber
     */
    public void selectButton(int buttonNumber) {
        listeBtn.forEach(btn -> {
            btn.setIcon(null);
            btn.setForeground(Color.DARK_GRAY);
        });

        listeBtn.get(buttonNumber).setForeground(Color.LIGHT_GRAY.brighter());
        listeBtn.get(buttonNumber).setIcon( new ImageIcon("src/main/resources/images/menu/menu_item.png"));
        listeBtn.get(buttonNumber).setHorizontalTextPosition(SwingConstants.CENTER);
    }
}