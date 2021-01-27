package com.mesi;

import com.mesi.panels.GameTitle;
import com.mesi.panels.Game;
import com.mesi.panels.StartMenu;
import com.mesi.panels.maps.MapGenerator;
import com.mesi.panels.maps.MapModel;
import com.mesi.params.Constant;
import com.mesi.params.KeyMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Hashtable;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainZeldo extends JPanel {

    /**********  Attributes  **********/

    public static Hashtable<String, MapModel> mapList = new Hashtable<String, MapModel>();

    public static enum GameState {
        GAME_TITLE, START_MENU, MAP_0_0, MAP_0_1,MAP_1
    }

    public static GameState state = GameState.MAP_1;
    public static boolean onStateChange = true;

    private static Game game;

    /**********  Constructors  **********/

    /**
     * Panel principal.
     * Le thread contrôle de l'état du panel à chaque rotation.
     * Si l'état change, un nouveau panel est affiché.
     */
    public MainZeldo() throws IOException {
        setLayout(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        if (onStateChange) {
                            removeAll();
                            add(displayedPanel());
                            repaint();
                            revalidate();
                        }
                        Thread.sleep(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**********  Methods  **********/

    /**
     * Retourne le panel demandé en fonction de l'état de la variable state.
     * @return JPanel.
     */
    public JPanel displayedPanel() throws IOException {
        onStateChange = false;

        switch (state) {
            case GAME_TITLE:
                return new GameTitle();

            case START_MENU:
                return new StartMenu();

            default:
                this.game = new Game(mapList.get(state.toString()));
                return game;
        }
    }

    /**
     * Génération des maps, création du JFrame et lancement du jeu.
     * @param args
     */
    public static void main(String[] args) throws IOException {
        new MapGenerator();
        JFrame f = new JFrame();
        f.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        f.setLocationRelativeTo(null);
        f.setUndecorated(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setVisible(true);
        f.add(new MainZeldo());

        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (state == GameState.GAME_TITLE && e.getKeyCode() == KeyMap.ESCAPE) {
                    onStateChange = true;
                    state = GameState.START_MENU;
                }

                if (state != GameState.GAME_TITLE && state != GameState.START_MENU) {
                    game.onKeyPressed(e.getKeyCode());
                }

                if (state == GameState.MAP_0_0 && e.getKeyCode() == KeyMap.ESCAPE) {
                    onStateChange = true;
                    state = GameState.MAP_0_1;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (state != GameState.GAME_TITLE && state != GameState.START_MENU) {
                    game.onKeyReleased(e.getKeyCode());
                }
            }
        });
    }
}
