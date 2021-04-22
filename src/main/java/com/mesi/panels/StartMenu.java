package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.panels.maps.MapGenerator;
import com.mesi.resources.Fonts;
import com.mesi.resources.Images;
import com.mesi.params.*;
import com.mesi.resources.Player;
import com.mesi.resources.Sounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartMenu extends JPanel {

    /**********  Attributes  **********/

    private static final Logger logger = LogManager.getLogger(StartMenu.class);

    private final Integer TITLE_WIDTH = 600;
    private final Integer TITLE_HEIGHT = 300;
    private final Font ITEM_FONT = Fonts.FLORANTE;
    private final Color ITEM_SELECTED = Color.LIGHT_GRAY.brighter();
    private final BufferedImage MENU_ITEM_L = Images.MENU_ITEM_L;

    private JLabel title = new JLabel(new ImageIcon(Images.TITLE.getScaledInstance(TITLE_WIDTH, TITLE_HEIGHT, Image.SCALE_SMOOTH)));

    private JButton btnNouvellePartie = new JButton("NOUVELLE PARTIE");
    private JButton btnContinuer = new JButton("CONTINUER");
    private JButton btnOptions = new JButton("OPTIONS");
    private JButton btnQuitter = new JButton("QUITTER");

    private List<JButton> listeBtn = new ArrayList<>();

    private int indexSelection = 0;

    /**********  Constructors  **********/

    /**
     * Menu du jeu.
     */
    public StartMenu() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        title.setBounds(0, 0, TITLE_WIDTH, TITLE_HEIGHT);

        setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        setBackground(Color.CYAN);

        GroupLayout layout = new GroupLayout(this);

        int largeurBtn = 250;
        int hauteurBtn = 40;

        listeBtn.add(btnNouvellePartie);
        listeBtn.add(btnContinuer);
        listeBtn.add(btnOptions);
        listeBtn.add(btnQuitter);

        for (int i = 0; i < listeBtn.size(); i++) {
            listeBtn.get(i).setFocusable(false);
            listeBtn.get(i).setBackground(Color.WHITE);
            listeBtn.get(i).setFont(ITEM_FONT.deriveFont(15f));
            listeBtn.get(i).setOpaque(false);
            listeBtn.get(i).setBorderPainted(false);
        }

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(title)
                        .addGap(10, 10, Short.MAX_VALUE)
                        .addComponent(getBtnNouvellePartie(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnContinuer(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnOptions(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getBtnQuitter(), largeurBtn, largeurBtn, largeurBtn)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(120)
                .addComponent(title)
                .addGap(105, 105, Short.MAX_VALUE)
                .addComponent(btnNouvellePartie, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnContinuer, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnOptions, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnQuitter, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(150)
        );

        setLayout(layout);

        btnNouvellePartie.setHorizontalTextPosition(SwingConstants.CENTER);
        btnNouvellePartie.setForeground(ITEM_SELECTED);
        btnNouvellePartie.setIcon(new ImageIcon(MENU_ITEM_L));

        logger.info("User in START_MENU");
    }

    /**********  Methods  **********/

    /**
     * Affiche l'image de fond de l'écran titre.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(Images.TITLE_SCREEN, 0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, this);
    }

    /**
     * Démarre une nouvelle partie.
     *
     * @return
     */
    public JButton getBtnNouvellePartie() {
        btnNouvellePartie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logger.debug("Clic on 'Nouvelle Partie'");

                MainZeldo.generic.stop();
                try {
                    new Backup().startNewGame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
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
        btnContinuer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logger.debug("Clic on 'Continuer'");

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
        btnOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logger.debug("Clic on 'Options'");
            }
        });

        return btnOptions;
    }

    /**
     * Quitte le jeu.
     *
     * @return
     */
    public JButton getBtnQuitter() {
        btnQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logger.debug("Clic on 'Quitter'");

                logger.info("<--------------- GAME END --------------->");
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
    public void onKeyPressed(int keyCode) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (keyCode == KeyMap.UP) {
            new Player(Sounds.MENU, false);
            indexSelection--;
            if (indexSelection < 0) {
                indexSelection = listeBtn.size() - 1;
            }
            selectButton(indexSelection);
        }

        if (keyCode == KeyMap.DOWN) {
            new Player(Sounds.MENU, false);
            indexSelection++;
            if (indexSelection >= listeBtn.size()) {
                indexSelection = 0;
            }
            selectButton(indexSelection);
        }

        if (keyCode == KeyMap.ENTER) {
            new Player(Sounds.MENU_CLIC, false);
            listeBtn.get(indexSelection).doClick();
        }
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
        listeBtn.get(buttonNumber).setIcon(new ImageIcon(MENU_ITEM_L));
        listeBtn.get(buttonNumber).setHorizontalTextPosition(SwingConstants.CENTER);
    }
}
