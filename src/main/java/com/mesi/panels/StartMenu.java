package com.mesi.panels;

import com.mesi.MainZeldo;
import com.mesi.decor.DecorObject;
import com.mesi.params.Constant;
import com.mesi.params.Couleur;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
                        .addComponent(getBtnQuitter(), largeurBtn, largeurBtn, largeurBtn)
                )
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(getBtnNouvellePartie(), hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(btnContinuer, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(getBtnQuitter(), hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(10, 10, Short.MAX_VALUE)
        );

        setLayout(layout);
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
                MainZeldo.state = MainZeldo.GameState.MAP_1;
                MainZeldo.onStateChange = true;
            }
        });

        return btnNouvellePartie;
    }

    /**
     * Continue la partie sauvegardée.
     *
     * @return
     */
    public JButton getBtnContinuer() throws IOException, ParseException {
        btnContinuer.setFocusable(false);
        btnContinuer.setBackground(Color.LIGHT_GRAY);

        btnContinuer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileReader reader = null;
                try {
                    reader = new FileReader("src/main/resources/saves/init_data.json");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                JSONParser jsonParser = new JSONParser();
                JSONObject save = null;
                try {
                    save = (JSONObject) jsonParser.parse(reader);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                JSONArray c = (JSONArray) save.get("characterPosition");
                //Integer[] c = (Integer[])save.get("characterPosition");
                Game.characterCoordinates[0] = Math.toIntExact((Long) c.get(0));
                Game.characterCoordinates[1] = Math.toIntExact((Long) c.get(1));
                //System.out.println(Math.toIntExact((Long) c.get(0)));

                JSONArray m = (JSONArray) ((JSONObject) save.get("maps")).get("MAP_1");
                System.out.println(m);
                MainZeldo.mapList.get("MAP_1").getDecorObjectArraylist().clear();
                for (Object n : m) {
                    String o = ((String) n).substring(1, ((String) n).length() - 1);
                    //MainZeldo.mapList.get("MAP_1").getDecorObjectArraylist().add((DecorObject) o);
                }
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
}
