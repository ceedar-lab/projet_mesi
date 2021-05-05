package com.mesi.panels;

import com.mesi.dialogue.Dialogue;
import com.mesi.params.KeyMap;
import com.mesi.resources.Fonts;
import com.mesi.resources.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DialoguePanel extends JDialog {


    private final Font ITEM_FONT = Fonts.FLORANTE;
    private final Color ITEM_SELECTED = Color.LIGHT_GRAY.brighter();
    private final BufferedImage MENU_ITEM_S = Images.MENU_ITEM_S;


    private JPanel panelPrinc = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            setSize(800,400);
            setOpaque(false);
            setBackground(Color.BLACK);
            g.drawImage(Images.MENU_BACKGROUND_800_400, 0, 0, 800, 400, this);
        }
    };
    private JPanel panelPrincPopup = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            setSize(800,200);
            setOpaque(false);
            setBackground(Color.BLACK);
            g.drawImage(Images.MENU_BACKGROUND_800_200, 0, 0, 800, 200, this);
        }
    };

    //    private JPanel panelPrinc = new JPanel();
    private JPanel questionPanel = new JPanel();
    private JPanel responsePanel = new JPanel();
    private Dialogue dialogue;

    private ArrayList<JButton> listeBtn = new ArrayList<>();
    private int indexSelection = 0;



    public DialoguePanel(String text,Integer time){

        setFocusableWindowState(false);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        getParent().requestFocus();
        setAlwaysOnTop(true);
        setSize(1000,200);
//        setLocationRelativeTo(null);
        setLocation((screen.width-1000)/2,screen.height/12);
        setUndecorated(true);
        setFocusable(false);

        getRootPane().setOpaque(false);
        setModal(false);
        setBackground(new Color(0, 0, 0, 0));

        add(getPanelPrincPopup(text));

        setVisible(true);


    }

    public DialoguePanel(String text){

        Game.setPause(true);

        setSize(800,400);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setFocusable(true);
        getRootPane().setOpaque(false);
        setModal(false);
        setBackground(new Color(0, 0, 0, 0));

        add(getPanelPrincText(text));

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Méthode inutilisée
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyMap.ESCAPE || e.getKeyCode() == KeyMap.ENTER ) {
                    Game.setPause(false);
                    dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setVisible(true);

    }


    public DialoguePanel(Dialogue dialogue) {
        this.dialogue = dialogue;

        Game.setPause(true);

        Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
//        setSize(sizeScreen.width,200);

        setSize(800,400);
        setUndecorated(true);
        setFocusable(true);

        setModal(false);

        add(getPanelPrinc());
        selectButton(0);

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
                    indexSelection--;
                    if (indexSelection < 0) {
                        indexSelection = listeBtn.size() - 1;
                    }
                    selectButton(indexSelection);
                }

                if (e.getKeyCode() == KeyMap.DOWN) {
                    indexSelection++;
                    if (indexSelection >= listeBtn.size()) {
                        indexSelection = 0;
                    }
                    selectButton(indexSelection);
                }

                if (e.getKeyCode() == KeyMap.ENTER) {
                    listeBtn.get(indexSelection).doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });



        setVisible(true);


    }

    public JPanel getPanelPrinc() {
//        panelPrinc = new JPanel();
        panelPrinc.setBackground(Color.LIGHT_GRAY);
//        panelPrinc.setBackground(new Color(0,0,255));
        GroupLayout layout = new GroupLayout(panelPrinc);



        int largeurBtn = 800;
        int hauteurBtn = 100;

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(getQuestionPanel(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getResponsePanel(), largeurBtn, largeurBtn, largeurBtn)
                )
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
//                .addGap(10, 10, Short.MAX_VALUE)
                        .addComponent(questionPanel, hauteurBtn, hauteurBtn, hauteurBtn)
//                .addGap(20)
                        .addComponent(responsePanel, hauteurBtn, hauteurBtn, hauteurBtn)
//                .addGap(10, 10, Short.MAX_VALUE)
        );

        panelPrinc.setLayout(layout);

        return panelPrinc;
    }



    public JPanel getQuestionPanel() {
        questionPanel.setBackground(Color.LIGHT_GRAY);
//        questionPanel.setBackground(new Color(255,0,0));
        JLabel jLabel = new JLabel();
        jLabel.setText(dialogue.getQuestionsList().get(dialogue.getCurrentQuestion()).getMessage());
        questionPanel.add(jLabel);
        return questionPanel;
    }

    public void setQuestionPanel(JPanel questionPanel) {
        this.questionPanel = questionPanel;
    }

    public JPanel getResponsePanel() {
        responsePanel.setBackground(Color.LIGHT_GRAY);
//        responsePanel.setBackground(new Color(255,255,0));
        GridLayout gridLayout = new GridLayout(dialogue.getQuestionsList().get(dialogue.getCurrentQuestion()).getResponseList().size(),1);

        for (String mapKey : dialogue.getQuestionsList().get(dialogue.getCurrentQuestion()).getResponseList().keySet()) {

            String reponse = dialogue.getQuestionsList().get(dialogue.getCurrentQuestion()).getResponseList().get(mapKey);
            JButton jButton = new JButton(reponse);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(dialogue.checkNext(dialogue.getCurrentQuestion(), mapKey)){
                        new DialoguePanel(dialogue);
                    }
                    else{
                        Game.setPause(false);
                    }
                    dispose();
                }
            });
            responsePanel.add(jButton);
            listeBtn.add(jButton);
        }

        responsePanel.setLayout(gridLayout);

        return responsePanel;
    }

    public void setResponsePanel(JPanel responsePanel) {
        this.responsePanel = responsePanel;
    }



    public JPanel getPanelPrincPopup(String text){

        GroupLayout layout = new GroupLayout(panelPrincPopup);

        int largeurBtn = 220;
        int hauteurBtn = 40;

        JLabel labelText = new JLabel(text ,SwingConstants.CENTER);


        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelText)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(labelText, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(10, 10, Short.MAX_VALUE)
        );

        panelPrincPopup.setLayout(layout);

        return panelPrincPopup;
    }

    public JPanel getPanelPrincText(String text){

        GroupLayout layout = new GroupLayout(panelPrinc);

        int largeurBtn = 220;
        int hauteurBtn = 40;

        JLabel labelText = new JLabel(text ,SwingConstants.CENTER);
        labelText.setFont(ITEM_FONT.deriveFont(30f));

        JTextArea jTextArea = new JTextArea(text);
        jTextArea.setFont(ITEM_FONT.deriveFont(30f));
        jTextArea.setBackground(new Color(0,0,0,0));
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);


        JButton button = new JButton("Fin");
        button.addActionListener(e -> {
            Game.setPause(false);
            dispose();
        });

        button.setFocusable(false);
        button.setBackground(Color.LIGHT_GRAY);
        button.setFont(ITEM_FONT.deriveFont(15f));
        button.setOpaque(false);
        button.setBorderPainted(false);

        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setForeground(ITEM_SELECTED);
        button.setIcon(new ImageIcon(MENU_ITEM_S));


        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(jTextArea,700,700,700)
                        .addComponent(button, largeurBtn, largeurBtn, largeurBtn)
                )
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(jTextArea)
                .addGap(15)
                .addComponent(button, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(10, 10, 40)
        );

        panelPrinc.setLayout(layout);

        return panelPrinc;
    }




    /**
     * Fais défiler le sélecteur lorsqu'on appuie sur les flèches de direction.
     *
     * @param buttonNumber
     */
    public void selectButton(int buttonNumber) {
        for (int i = 0; i < listeBtn.size(); i++) {
            listeBtn.get(i).setBackground(Color.LIGHT_GRAY);
        }

        listeBtn.get(buttonNumber).setBackground(Color.GREEN);
    }




}
