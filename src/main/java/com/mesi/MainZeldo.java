package com.mesi;

import com.mesi.dialogue.Dialogue;
import com.mesi.dialogue.DialogueGenerator;
import com.mesi.panels.Game;
import com.mesi.panels.GameTitle;
import com.mesi.panels.StartMenu;
import com.mesi.panels.maps.MapGenerator;
import com.mesi.panels.maps.MapModel;
import com.mesi.params.Constant;
import com.mesi.params.KeyMap;
import com.mesi.resources.Player;
import com.mesi.resources.Sounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import com.mesi.pnj.Pnj;
import com.mesi.pnj.PnjGenerator;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainZeldo extends JPanel {

    public static Player generic;

    /**********  Attributes  **********/

    private static final Logger logger = LogManager.getLogger(MainZeldo.class);

    public static final Map<String, MapModel> mapList = new HashMap<>();
    public static final Map<String, Dialogue> dialogueList = new HashMap<>();
    public static final Map<String, Pnj> pnjList = new HashMap<>();


    public enum GameState {
        GAME_TITLE, START_MENU, MAP_1, MAP_2
    }

    private static GameState gameState = GameState.GAME_TITLE;
    private static boolean gameStateChange = true;

    private static Game game;
    private static StartMenu startMenu;

    static {
        try {
            startMenu = new StartMenu();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            logger.error("Error initializing start menu : " + e.getMessage());
        }
        try {
            game = new Game();
        } catch (IOException e) {
            logger.error("Error initializing new game : " + e.getMessage());
        }
    }

    /**********  Constructors  **********/

    /**
     * Panel principal.
     * Le thread contrôle de l'état du panel à chaque rotation.
     * Si l'état change, un nouveau panel est affiché.
     */
    public MainZeldo() {
        logger.debug("Starting MainZeldo on " + Thread.currentThread().getName());
        setLayout(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (gameStateChange) {
                            removeAll();
                            add(displayedPanel());
                            repaint();
                            revalidate();
                        }
                        Thread.sleep(Constant.FPS);
                    }
                } catch (Exception e) {
                    logger.error("Error loading thread : " +e.getMessage());
                }
            }
        }).start();
    }

    /**********  Getters / Setters  **********/

    public static final GameState getGameState() { return gameState; }
    public static final void setGameState(GameState newState) { gameState = newState; }
    public static final boolean isGameStateChange() { return gameStateChange; }
    public static final void setGameStateChange(boolean bool) { gameStateChange = bool; }

    /**********  Methods  **********/

    /**
     * Retourne le panel demandé en fonction de l'état de la variable state.
     *
     * @return JPanel.
     */
    private static JPanel displayedPanel() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        gameStateChange = false;
        logger.debug(gameState+ " panel called");

        switch (gameState) {
            case GAME_TITLE:
                return new GameTitle();

            case START_MENU:
                startMenu = new StartMenu();
                return startMenu;

            default:
                game = new Game(mapList.get(gameState.toString()));
                return game;
        }
    }

    /**
     * Génération des maps, création du JFrame et lancement du jeu.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, ParseException {
        generic = new Player(Sounds.GENERIC_START, true);
        new DialogueGenerator();
        new PnjGenerator();//genere les PNJs
        new MapGenerator();//genere les maps
        JFrame f = new JFrame();
        f.setLocationRelativeTo(null);
        f.setUndecorated(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setExtendedState(Frame.MAXIMIZED_BOTH);
        f.add(new MainZeldo());

        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Méthode inutilisée
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (gameState == GameState.GAME_TITLE && e.getKeyCode() == KeyMap.ESCAPE) {
                    gameStateChange = true;
                    gameState = GameState.START_MENU;
                }

                if (gameState == GameState.START_MENU) {
                    try {
                        startMenu.onKeyPressed(e.getKeyCode());
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    }
                }

                if (gameState != GameState.GAME_TITLE && gameState != GameState.START_MENU) {
                    try {
                        game.onKeyPressed(e.getKeyCode());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (gameState != GameState.GAME_TITLE && gameState != GameState.START_MENU) {
                    game.onKeyReleased(e.getKeyCode());
                }
            }
        });
    }
}
