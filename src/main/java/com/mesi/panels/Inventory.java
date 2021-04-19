package com.mesi.panels;

import com.mesi.animation.Animation;
import com.mesi.equipement.RightHand;
import com.mesi.params.Constant;
import com.mesi.params.KeyMap;
import com.mesi.pojo.Item;
import com.mesi.resources.Player;
import com.mesi.resources.Sounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory extends JDialog {

    /**********  Attributes  **********/

    private static final Logger logger = LogManager.getLogger(GameMenu.class);

    private JPanel panelInventory = new JPanel();

    private JButton btnInventaire = new JButton("INVENTAIRE");
    private JButton btnEquipement = new JButton("EQUIPEMENT");
    private JButton btnRetourAuMenu = new JButton("RETOUR AU MENU");
    private JButton btnUtiliser = new JButton("UTILISER");
    private JButton btnJeter = new JButton("JETER");

    private JPanel panelItemList = new JPanel();
    private JPanel panelCharacterPic = new JPanel();

    Map<String, Integer> item = new HashMap();
    private InventoryModel itemListModel = new InventoryModel();

    private JTable itemList = new JTable(itemListModel);

    private TableColumn itemListCol;

    private ArrayList<JButton> listeBtn = new ArrayList<>();

    private int indexSelection = 0;

    /**********  Constructors  **********/

    public Inventory() {
        getItemlist();
        setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        setUndecorated(true);
        setModal(false);
        setFocusable(true);
        setLocationRelativeTo(null);

        this.itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //this.itemList.setCellSelectionEnabled(true);
        createPanelItemList();
        createPanelCharacterPic();

        listeBtn.add(btnRetourAuMenu);

        add(getPanelInventory());

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Méthode inutilisée
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyMap.ESCAPE) {
                    //Game.setPause(false);
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
                // Méthode inutilisée
            }
        });

        setVisible(true);

        btnRetourAuMenu.setBackground(Color.GREEN);
    }

    /**********  Methods  **********/

    /**
     * Configuration du menu in game.
     *
     * @return
     */
    public JPanel getPanelInventory() {
        panelInventory.setBackground(Color.LIGHT_GRAY);

        GroupLayout layout = new GroupLayout(panelInventory);

        int largeurBtn = 140;
        int hauteurBtn = 20;

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(40)
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnInventaire)
                                .addComponent(btnEquipement)
                                .addComponent(getBtnRetourAuMenu())
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelItemList)
                                .addGap(20)
                                .addComponent(panelCharacterPic)
                        )
                )
                .addGap(40)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(40)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(btnInventaire)
                                .addComponent(btnEquipement)
                                .addComponent(btnRetourAuMenu)
                        )
                        .addGap(5)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(panelItemList)
                                .addComponent(panelCharacterPic)
                        )
                )
                .addGap(40)
        );

        panelInventory.setLayout(layout);

        return panelInventory;
    }

    private JPanel createPanelItemList() {
        panelItemList.setBackground(Color.GRAY);
        panelItemList.add(new JScrollPane(itemList), BorderLayout.CENTER);
        JPanel btn = new JPanel();

        btn.add(btnUtiliser);
        btn.add(btnJeter);

        getContentPane().add(btn, BorderLayout.SOUTH);

        return panelItemList;
    }

    private JPanel createPanelCharacterPic() {
        panelCharacterPic.setBackground(Color.YELLOW);

        BufferedImage resizedImg = new BufferedImage(Constant.FRAME_HEIGHT/2, Constant.FRAME_HEIGHT/2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Game.charPic, 0, 0, Constant.FRAME_HEIGHT/2, Constant.FRAME_HEIGHT/2, null);
        g2.dispose();

        panelCharacterPic.setLayout(new GridBagLayout());
        panelCharacterPic.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel picLabel = new JLabel(new ImageIcon(resizedImg));
        panelCharacterPic.add(picLabel);

        return panelCharacterPic;
    }

    /**
     * Ferme le menu et retourne à la partie.
     *
     * @return
     */
    public JButton getBtnRetourAuMenu() {
        btnRetourAuMenu.setFocusable(false);
        btnRetourAuMenu.setBackground(Color.LIGHT_GRAY);

        btnRetourAuMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        return btnRetourAuMenu;
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

    public void getItemlist() {
        Game.getCharacter().itemList.forEach(e -> {
            item.computeIfPresent(e.getName(), (key, val) -> val + e.getQuantity());
            item.computeIfAbsent(e.getName(), val -> e.getQuantity());
        });
        this.item.forEach((key, val) -> {
            this.itemListModel.addItem(new Item(key, val));
        });

        itemListCol = itemList.getColumnModel().getColumn(1);
        JTextField textBox = new JTextField();
        itemListCol.setCellEditor(new DefaultCellEditor(textBox));
        itemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(itemList.getValueAt(itemList.getSelectedRow(), 0).toString());
                try {
                    setEquipement(itemList.getValueAt(itemList.getSelectedRow(), 0).toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }
        });

//        private void createKeybindings(JTable table) {
//            table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
//            table.getActionMap().put("Enter", new AbstractAction() {
//                @Override
//                public void actionPerformed(ActionEvent ae) {
//                    //do something on JTable enter pressed
//                }
//            });
//        }

    }

    public void setEquipement(String equipement) throws IOException {
        new Player(Sounds.EQUIP_WEAPON, false);
        Animation character = Game.getCharacter();
        switch (equipement) {
            case "épée": character.setRightHand(RightHand.SWORD); break;
            case "dague": character.setRightHand(RightHand.DAGGER); break;
            default: break;
        }
        character.createNewCharacter();
    }
}