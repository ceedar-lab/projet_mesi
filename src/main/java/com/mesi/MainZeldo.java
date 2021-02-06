package com.mesi;

import com.mesi.panels.Game;
import com.mesi.panels.GameTitle;
import com.mesi.panels.StartMenu;
import com.mesi.panels.maps.MapGenerator;
import com.mesi.panels.maps.MapModel;
import com.mesi.params.Constant;
import com.mesi.params.KeyMap;
import org.json.simple.parser.ParseException;
import com.mesi.pnj.Pnj;
import com.mesi.pnj.PnjGenerator;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Hashtable;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainZeldo extends JPanel {

    /**********  Attributes  **********/

    public static Hashtable<String, MapModel> mapList = new Hashtable<String, MapModel>();
    public static Hashtable<String, Pnj> pnjList = new Hashtable<String, Pnj>();

    public static enum GameState {
        GAME_TITLE, START_MENU, MAP_1, MAP_2
    }

    public static GameState state = GameState.GAME_TITLE;
    public static boolean onStateChange = true;

    private static Game game;
    private static StartMenu startMenu;

    /**********  Constructors  **********/

    /**
     * Panel principal.
     * Le thread contrôle de l'état du panel à chaque rotation.
     * Si l'état change, un nouveau panel est affiché.
     */
    public MainZeldo() throws IOException, ParseException {
        setLayout(null);
        game = new Game();
        startMenu = new StartMenu();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (onStateChange) {
                            removeAll();
                            add(displayedPanel());
                            repaint();
                            revalidate();
                        }
                        Thread.sleep(Constant.FPS);
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
     *
     * @return JPanel.
     */
    public JPanel displayedPanel() throws IOException, ParseException, InterruptedException {
        onStateChange = false;

        switch (state) {
            case GAME_TITLE:
                return new GameTitle();

            case START_MENU:
                return startMenu = new StartMenu();

            default:
                return game = new Game(mapList.get(state.toString()));
        }
    }

    /**
     * Génération des maps, création du JFrame et lancement du jeu.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, ParseException {
        //new Images();//charge les images en buffer
        new PnjGenerator();//genere les PNJs
        new MapGenerator();//genere les maps
        JFrame f = new JFrame();
        //f.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        f.setLocationRelativeTo(null);
        f.setUndecorated(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setVisible(true);
        //f.pack();
        f.setExtendedState(f.MAXIMIZED_BOTH);
        f.add(new MainZeldo());

        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if (state == GameState.GAME_TITLE && e.getKeyCode() == KeyMap.ESCAPE) {
                    onStateChange = true;
                    state = GameState.START_MENU;
                }

                if (state == GameState.START_MENU) {
                    startMenu.onKeyPressed(e.getKeyCode());
                }


                if (state != GameState.GAME_TITLE && state != GameState.START_MENU) {
                    game.onKeyPressed(e.getKeyCode());
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
