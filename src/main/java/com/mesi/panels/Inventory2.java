package com.mesi.panels;

import com.mesi.params.Constant;
import com.mesi.params.KeyMap;
import com.mesi.resources.Images;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Inventory2 extends JDialog {

    private static final Logger logger = LogManager.getLogger(Inventory2.class);

    private static final Integer UPPER_BAND_HEIGTH = Constant.FRAME_HEIGHT / 6;
    private static final Integer LOWER_BAND_HEIGTH = Constant.FRAME_HEIGHT / 10;
    private static final Integer MID_BAND_HEIGTH = Constant.FRAME_HEIGHT - UPPER_BAND_HEIGTH - LOWER_BAND_HEIGTH;

    private JPanel itemGrid = new JPanel();

    private JPanel itemTypePanel = new JPanel();

    private JPanel upperBand = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            setOpaque(true);
            setBackground(new Color(0, 0, 0, 0.30f));
            //setBackground(Color.RED);
            setBounds(0, 0, Constant.FRAME_WIDTH, UPPER_BAND_HEIGTH);
        }
    };

    private JPanel westBand = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            setOpaque(true);
            setBackground(new Color(0, 0, 0, 0));
            //setBackground(Color.CYAN);
            setBounds(0, UPPER_BAND_HEIGTH, Constant.FRAME_WIDTH / 2, MID_BAND_HEIGTH);

            //add(createItemtypePanel());
            add(createGridPanel());

            setVisible(true);
        }
    };
    private JPanel eastBand = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            setOpaque(true);
            setBackground(new Color(0, 0, 0, 0));
            //setBackground(Color.YELLOW);
            setBounds(Constant.FRAME_WIDTH / 2, UPPER_BAND_HEIGTH, Constant.FRAME_WIDTH / 2, MID_BAND_HEIGTH);
        }
    };


    private JPanel lowerBand = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            setOpaque(true);
            setBackground(new Color(0, 0, 0, 0.30f));
            //setBackground(Color.GREEN);
            setBounds(0, Constant.FRAME_HEIGHT - LOWER_BAND_HEIGTH, Constant.FRAME_WIDTH, LOWER_BAND_HEIGTH);
        }
    };

    public Inventory2() {
        logger.debug("Inventory opened");

        setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        setUndecorated(true);
        setModal(false);
        setFocusable(true);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0.55f));
        setLayout(new BorderLayout());

        add(upperBand, BorderLayout.NORTH);
        add(westBand, BorderLayout.WEST);
        add(eastBand, BorderLayout.EAST);
        add(lowerBand, BorderLayout.SOUTH);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Méthode inutilisée
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyMap.ESCAPE) {
                    new GameMenu();
                    dispose();
                }

                if (e.getKeyCode() == KeyMap.UP) {
//                    indexSelection--;
//                    if (indexSelection < 0) {
//                        indexSelection = listeBtn.size() - 1;
//                    }
//                    selectButton(indexSelection);
                }

                if (e.getKeyCode() == KeyMap.DOWN) {
//                    indexSelection++;
//                    if (indexSelection >= listeBtn.size()) {
//                        indexSelection = 0;
//                    }
//                    selectButton(indexSelection);
                }

                if (e.getKeyCode() == KeyMap.ENTER) {
//                    listeBtn.get(indexSelection).doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Méthode inutilisée
            }
        });

        setVisible(true);
    }

    private JPanel createGridPanel() {
        Integer gridRows = 4;
        Integer gridCols = 6;
        Integer gridGap = 15;
        Integer itemSize = 100;
        Integer gridWidth = itemSize * gridCols + gridGap * (gridCols - 1);
        Integer gridHeight = itemSize * gridRows + gridGap * (gridRows - 1);

        itemGrid.setLayout(new GridLayout(gridRows, gridCols, gridGap, gridGap));
        itemGrid.setBounds(Constant.FRAME_WIDTH / 2 - gridWidth - 10, (MID_BAND_HEIGTH - gridHeight) / 2 +50, gridWidth, gridHeight);
//        itemGrid.setBounds(20, 20, gridWidth, gridHeight);
        itemGrid.setBackground(new Color(0, 0, 0, 0));
//        itemGrid.setBackground(Color.MAGENTA);
        for (int i = 1; i <= (gridRows * gridCols); i++) {
            System.out.println("button " +i+ " added");
            JButton item = new JButton(String.valueOf(i));
            item.setPreferredSize(new Dimension(itemSize, itemSize));
            itemGrid.add(item);
        }

        itemGrid.setVisible(true);

        return itemGrid;
    }

    private JPanel createItemtypePanel() {
        Integer gridRows = 1;
        Integer gridCols = 3;
        Integer gridGap = 30;
        Integer itemSize = 70;
        Integer gridWidth = itemSize * gridCols + gridGap * (gridCols - 1);
        Integer gridHeight = itemSize * gridRows + gridGap * (gridRows - 1);

        JButton weapon = new JButton("Armes");
        JButton clothes = new JButton("Tenues");
        JButton equipement = new JButton("Equipement");
        weapon.setPreferredSize(new Dimension(itemSize, itemSize));
        clothes.setPreferredSize(new Dimension(itemSize, itemSize));
        equipement.setPreferredSize(new Dimension(itemSize, itemSize));

        itemTypePanel.setLayout(new GridLayout(1, 3, gridGap, gridGap));
        itemTypePanel.setBounds(Constant.FRAME_WIDTH / 2 - itemGrid.getWidth() - 10, 30, gridWidth, gridHeight);
        itemTypePanel.setBackground(Color.BLACK);

        itemTypePanel.add(weapon);
        itemTypePanel.add(clothes);
        itemTypePanel.add(equipement);

        itemTypePanel.setVisible(true);

        return itemTypePanel;
    }
}
