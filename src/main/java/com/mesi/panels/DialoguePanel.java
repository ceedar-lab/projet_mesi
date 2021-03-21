package com.mesi.panels;

import com.mesi.dialogue.Dialogue;
import com.mesi.params.KeyMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class DialoguePanel extends JDialog {

    private JPanel panelPrinc = new JPanel();
    private JPanel questionPanel = new JPanel();
    private JPanel responsePanel = new JPanel();
    private Dialogue dialogue;

    private ArrayList<JButton> listeBtn = new ArrayList<>();
    private int indexSelection = 0;

    public DialoguePanel(Dialogue dialogue) {
        this.dialogue = dialogue;
        Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(sizeScreen.width,200);
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

                if (e.getKeyCode() == KeyMap.LEFT) {
                    indexSelection--;
                    if (indexSelection < 0) {
                        indexSelection = listeBtn.size() - 1;
                    }
                    selectButton(indexSelection);
                }

                if (e.getKeyCode() == KeyMap.RIGHT) {
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
        panelPrinc.setBackground(new Color(0,0,255));
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
                .addGap(10, 10, Short.MAX_VALUE)
                .addComponent(questionPanel, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(20)
                .addComponent(responsePanel, hauteurBtn, hauteurBtn, hauteurBtn)
                .addGap(10, 10, Short.MAX_VALUE)
        );

        panelPrinc.setLayout(layout);

        return panelPrinc;
    }



    public JPanel getQuestionPanel() {
        questionPanel.setBackground(new Color(255,0,0));
        JLabel jLabel = new JLabel();
        jLabel.setText(dialogue.getQuestionsList().get(dialogue.getCurrentQuestion()).getMessage());
        questionPanel.add(jLabel);
        return questionPanel;
    }

    public void setQuestionPanel(JPanel questionPanel) {
        this.questionPanel = questionPanel;
    }

    public JPanel getResponsePanel() {
        responsePanel.setBackground(new Color(255,255,0));


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


        return responsePanel;
    }

    public void setResponsePanel(JPanel responsePanel) {
        this.responsePanel = responsePanel;
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
