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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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

    private JPanel questionPanel = new JPanel();
    private JPanel responsePanel = new JPanel();
    private Dialogue dialogue;

    private ArrayList<JButton> listeBtn = new ArrayList<>();
    private int indexSelection = 0;


    /**
     * JDialog popup qui disparaisse automatiquement a la fin du timer
     * le jeux n'est pas mis en pause pour autant
     * @param text : texte du popup
     * @param time : temps en seconde avant disparition du popup
     */
    public DialoguePanel(String text,Integer time){

        setFocusableWindowState(false);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        getParent().requestFocus();
        setAlwaysOnTop(true);
        setSize(800,200);;
        setLocation((screen.width-800)/2,screen.height/12);
        setUndecorated(true);
        setFocusable(false);

        getRootPane().setOpaque(false);
        setModal(false);
        setBackground(new Color(0, 0, 0, 0));

        add(getPanelPrincPopup(text));

        setVisible(true);

        CompletableFuture.delayedExecutor(time, TimeUnit.SECONDS).execute(()->{
            dispose();
        });

    }

    /**
     * JDialog qui met le jeux en pause en attendant une action du joueur soit pas la touche entrer soit par la touche ESC
     * @param text : texte de la fenêtre
     */
    public DialoguePanel(String text){

        Game.setPause(true);

        setSize(800,400);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setFocusable(true);
        getRootPane().setOpaque(false);
        setModal(true);
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

    /**
     * JDialog de dialogue avec les PNJ
     * le jeux est mis en pause durant le temps de vie de la fenêtre
     * @param dialogue : sequence de questions réponses
     */
    public DialoguePanel(Dialogue dialogue) {
        this.dialogue = dialogue;

        Game.setPause(true);

        dialogue.checkChange();


        setSize(800,400);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setFocusable(true);
        setBackground(new Color(0, 0, 0, 0));
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

        GroupLayout layout = new GroupLayout(panelPrinc);

        int largeurBtn = 800;
        int hauteurBtn = 200;

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(getQuestionPanel(), largeurBtn, largeurBtn, largeurBtn)
                        .addComponent(getResponsePanel(), largeurBtn, largeurBtn, largeurBtn)
                )
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(questionPanel, 180, 180, 180)
                .addComponent(responsePanel, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(10, 10, Short.MAX_VALUE)
        );

        panelPrinc.setLayout(layout);

        return panelPrinc;
    }



    public JPanel getQuestionPanel() {



        JTextArea jTextArea = new JTextArea(dialogue.getQuestionsList().get(dialogue.getCurrentQuestion()).getMessage());
        jTextArea.setFont(ITEM_FONT.deriveFont(20f));
        jTextArea.setBackground(new Color(0,0,0,0));
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);

        GroupLayout layout = new GroupLayout(questionPanel);
        questionPanel.setBackground(new Color(0,0,0,0));


        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextArea,600,600,600)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(50)
                .addComponent(jTextArea)
                .addGap(50)
        );

        questionPanel.setLayout(layout);

        return questionPanel;
    }



    public JPanel getResponsePanel() {
        responsePanel.setBackground(new Color(0,0,0,0));
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

            jButton.setFocusable(false);
            jButton.setBackground(Color.LIGHT_GRAY);
            jButton.setFont(ITEM_FONT.deriveFont(15f));
            jButton.setOpaque(false);
            jButton.setBorderPainted(false);

            jButton.setHorizontalTextPosition(SwingConstants.CENTER);
            jButton.setForeground(ITEM_SELECTED);
            jButton.setIcon(new ImageIcon(MENU_ITEM_S));

            responsePanel.add(jButton);
            listeBtn.add(jButton);
        }

        responsePanel.setLayout(gridLayout);

        return responsePanel;
    }



    public JPanel getPanelPrincPopup(String text){

        GroupLayout layout = new GroupLayout(panelPrincPopup);

        JLabel labelText = new JLabel(text ,SwingConstants.CENTER);
        labelText.setFont(ITEM_FONT.deriveFont(20f));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelText)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(labelText)
                .addGap(10, 10, Short.MAX_VALUE)
        );

        panelPrincPopup.setLayout(layout);

        return panelPrincPopup;
    }


    public JPanel getPanelPrincText(String text){

        GroupLayout layout = new GroupLayout(panelPrinc);

        int largeurBtn = 220;
        int hauteurBtn = 40;

//        JLabel labelText = new JLabel(text ,SwingConstants.CENTER);
//        labelText.setFont(ITEM_FONT.deriveFont(30f));

        JTextArea jTextArea = new JTextArea(text);
        jTextArea.setFont(ITEM_FONT.deriveFont(20f));
        jTextArea.setBackground(new Color(0,0,0,0));
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);

        JButton button = new JButton("Fin");
        button.addActionListener(e -> {
            Game.setPause(false);
            dispose();
        });

        button.setFocusable(false);
        button.setBackground(new Color(0,0,0,0));
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
            listeBtn.get(i).setForeground(null);
        }

        listeBtn.get(buttonNumber).setForeground(ITEM_SELECTED);
    }




}
