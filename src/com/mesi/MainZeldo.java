package com.mesi;

import com.mesi.panels.GameTitle;
import com.mesi.panels.Game;
import com.mesi.panels.StartMenu;
import com.mesi.panels.maps.Map_0_0;
import com.mesi.params.Constant;
import com.mesi.params.KeyMap;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainZeldo extends JPanel {

    /**********  Attributes  **********/

    /*BufferedImage trunk = ImageIO.read(new File("res/images/tree-trunk.png"));
    BufferedImage foliage = ImageIO.read(new File("res/images/tree-foliage.png"));
    g.drawImage(trunk, 2*TILE_SIZE, 6*TILE_SIZE, this);
    g.drawImage(foliage, TILE_SIZE + TILE_SIZE/2, 4*TILE_SIZE + TILE_SIZE/2, this);*/

    public static enum GameState {
        GAME_TITLE, START_MENU, GAME
    }

    public static GameState state = GameState.GAME;
    public static Integer onStateChange = 1;

    public static Game game;

    static {
        try {
            game = new Game(new Map_0_0(10, 10, KeyMap.DOWN));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**********  Constructors  **********/

    public MainZeldo()  {
        setLayout(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        if (onStateChange == 1) {
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

    public JPanel displayedPanel() {

        onStateChange = 0;

        switch (state) {
            case GAME_TITLE:
                return new GameTitle();

            case START_MENU:
                return new StartMenu();

            case GAME:
                return game;

            default:
                return new GameTitle();
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("MyGame");
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
                //System.out.println("Touche pressée : " +e.getKeyCode());

                if (state == GameState.GAME_TITLE && e.getKeyCode() == KeyMap.ESCAPE) {
                    onStateChange = 1;
                    state = GameState.START_MENU;
                }

                if (state == GameState.GAME) {
                    game.onKeyPressed(e.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (state == GameState.GAME) {
                    game.onKeyReleased(e.getKeyCode());
                }
            }
        });
    }
}
