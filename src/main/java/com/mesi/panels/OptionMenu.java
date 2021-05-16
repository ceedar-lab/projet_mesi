package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.params.Backup;
import com.mesi.params.KeyMap;
import com.mesi.resources.Fonts;
import com.mesi.resources.Images;
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

public class OptionMenu extends JDialog {

    /**********  Attributes  **********/

    private static final Logger logger = LogManager.getLogger(OptionMenu.class);

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

    private JButton btnMusic = new JButton("MUSIC");
    private JButton btnCollision = new JButton("COLLISION");
    private JButton btnTileNumber = new JButton("TILE NUMBER");

    private JLabel statutMusic = new JLabel(Game.sound ? "ON" :"OFF");
    private JLabel statutCollision = new JLabel(Game.collision ? "ON" :"OFF");
    private JLabel statutTileNumber = new JLabel(Game.tileNumber ? "ON" :"OFF");

    private ArrayList<JButton> listeBtn = new ArrayList<>();

    private int indexSelection = 0;

    /**********  Constructors  **********/

    public OptionMenu() {

        setSize(MENU_WIDTH, MENU_HEIGHT);
        getRootPane().setOpaque(false);
        setUndecorated(true);
        setModal(false);
        setFocusable(true);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));

        listeBtn.add(btnMusic);
        listeBtn.add(btnCollision);
        listeBtn.add(btnTileNumber);


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

        btnMusic.setHorizontalTextPosition(SwingConstants.CENTER);
        btnMusic.setForeground(ITEM_SELECTED);
        btnMusic.setIcon(new ImageIcon(MENU_ITEM_S));
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
                        .addComponent(getBtnMusic(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnCollision(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnTileNumber(), largeurBtn, largeurBtn, largeurBtn)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(statutMusic)
                        .addComponent(statutCollision)
                        .addComponent(statutTileNumber)
                )
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(btnMusic, hauteurBtn, hauteurBtn, hauteurBtn)
                        .addComponent(statutMusic, hauteurBtn, hauteurBtn, hauteurBtn)
                )
                .addGap(15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(btnCollision, hauteurBtn, hauteurBtn, hauteurBtn)
                        .addComponent(statutCollision, hauteurBtn, hauteurBtn, hauteurBtn)
                )
                .addGap(15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(btnTileNumber, hauteurBtn, hauteurBtn, hauteurBtn)
                        .addComponent(statutTileNumber, hauteurBtn, hauteurBtn, hauteurBtn)
                )
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
    public JButton getBtnMusic() {

        btnMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.sound = !Game.sound;
                if(!Game.sound && Game.music!=null){
                    Game.music.stop();
                }
                statutMusic.setText(statutMusic.getText().equals("ON") ? "OFF" :"ON");
            }
        });

        return btnMusic;
    }

    /**
     * Retour au menu principal.
     *
     * @return
     */
    public JButton getBtnCollision() {
        btnCollision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                logger.debug("Clic on 'Menu principal'");
                Game.collision = !Game.collision;
                statutCollision.setText(statutCollision.getText().equals("ON") ? "OFF" :"ON");
            }
        });

        return btnCollision;
    }

    /**
     * Enregistre la partie en cours.
     *
     * @return
     */
    public JButton getBtnTileNumber() {
        btnTileNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.tileNumber = !Game.tileNumber;
                statutTileNumber.setText(statutTileNumber.getText().equals("ON") ? "OFF" :"ON");
            }
        });

        return btnTileNumber;
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