package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.resources.Fonts;
import com.mesi.resources.Images;
import com.mesi.params.*;
import com.mesi.resources.Player;
import com.mesi.resources.Sounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GameMenu extends JDialog {

    /**********  Attributes  **********/

    private static final Logger logger = LogManager.getLogger(GameMenu.class);

    private final Integer MENU_WIDTH = 360;
    private final Integer MENU_HEIGHT = 480;
    private final Font ITEM_FONT = Fonts.FLORANTE;
    private final Color ITEM_SELECTED = Color.LIGHT_GRAY.brighter();
    private final BufferedImage MENU_ITEM_S = Images.MENU_ITEM_S;

    private JPanel menuPanel = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            setOpaque(false);
            setBackground(Color.BLACK);
            g.drawImage(Images.MENU_BACKGROUND, 0, 0, MENU_WIDTH, MENU_HEIGHT, this);
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

        setSize(MENU_WIDTH, MENU_HEIGHT);
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
            listeBtn.get(i).setFont(ITEM_FONT.deriveFont(15f));
            listeBtn.get(i).setOpaque(false);
            listeBtn.get(i).setBorderPainted(false);
        }

        add(getMenuPanel());

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

        btnRetourAuJeu.setHorizontalTextPosition(SwingConstants.CENTER);
        btnRetourAuJeu.setForeground(ITEM_SELECTED);
        btnRetourAuJeu.setIcon(new ImageIcon(MENU_ITEM_S));
    }

    /**********  Methods  **********/

    /**
     * Configuration du menu in game.
     *
     * @return
     */
    public JPanel getMenuPanel() {
        GroupLayout layout = new GroupLayout(menuPanel);

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

        menuPanel.setLayout(layout);

        return menuPanel;
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
                logger.debug("Clic on 'Retour au jeu'");

                logger.info("Game resumed");
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
                logger.debug("Clic on 'Menu principal'");

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
                logger.debug("Clic on 'Enregistrer'");

                try {
                    new Backup().save("save_1");
                } catch (IOException ioException) {
                    logger.error("Error while saving : " + ioException.getMessage());
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
                logger.debug("Clic on 'Charger'");

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
                logger.debug("Clic on 'Inventaire'");

                new Inventory2();
                dispose();
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
                logger.debug("Clic on 'Quitter'");

                logger.info("<--------------- GAME END --------------->");
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

        listeBtn.get(buttonNumber).setForeground(ITEM_SELECTED);
        listeBtn.get(buttonNumber).setIcon(new ImageIcon(MENU_ITEM_S));
        listeBtn.get(buttonNumber).setHorizontalTextPosition(SwingConstants.CENTER);
    }
}