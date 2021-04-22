package com.mesi.panels;

import com.mesi.animation.Animation;
import com.mesi.decor.collectable.CollectableItem;
import com.mesi.equipement.Feet;
import com.mesi.equipement.Legs;
import com.mesi.equipement.RightHand;
import com.mesi.equipement.Torso;
import com.mesi.params.Constant;
import com.mesi.params.KeyMap;
import com.mesi.pojo.Item;
import com.mesi.resources.Images;
import com.mesi.resources.Player;
import com.mesi.resources.Sounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory extends JDialog {

    /**********  Attributes  **********/

    private static final Logger logger = LogManager.getLogger(Inventory.class);

    private static final Integer UPPER_BAND_HEIGTH = Constant.FRAME_HEIGHT / 6;
    private static final Integer LOWER_BAND_HEIGTH = Constant.FRAME_HEIGHT / 10;
    private static final Integer MID_BAND_HEIGTH = Constant.FRAME_HEIGHT - UPPER_BAND_HEIGTH - LOWER_BAND_HEIGTH;

    private List<JButton> listTypeBtn = new ArrayList<>();
    private List<JButton> listItemBtn = new ArrayList<>();

    private Integer[] rows = {4, 1};
    private Integer[] cols = {6, 5};
    private Integer[] gap = {30, 40};
    private Integer[] size = {Math.round(Constant.FRAME_HEIGHT / 11), Math.round(Constant.FRAME_HEIGHT / 14)};

    private Integer iconTypeSize = size[1] - 10;
    private Integer iconItemSize = size[0];

    private final ImageIcon CLOTHES = convertImageToIcon(Images.ICON_CLOTHES);
    private final ImageIcon CLOTHES_SELECTED = convertImageToIcon(Images.ICON_CLOTHES_SELECTED);
    private final ImageIcon OTHERS = convertImageToIcon(Images.ICON_OTHERS);
    private final ImageIcon OTHERS_SELECTED = convertImageToIcon(Images.ICON_OTHERS_SELECTED);
    private final ImageIcon WEAPON = convertImageToIcon(Images.ICON_WEAPON);
    private final ImageIcon WEAPON_SELECTED = convertImageToIcon(Images.ICON_WEAPON_SELECTED);
    private final ImageIcon ITEM = convertImageToIcon(Images.ICON_ITEM_BG);
    private final ImageIcon LEFT_ARROW = convertImageToIcon(Images.ICON_LEFT_ARROW);
    private final ImageIcon LEFT_ARROW_SELECTED = convertImageToIcon(Images.ICON_LEFT_ARROW_SELECTED);
    private final ImageIcon RIGHT_ARROW = convertImageToIcon(Images.ICON_RIGHT_ARROW);
    private final ImageIcon RIGHT_ARROW_SELECTED = convertImageToIcon(Images.ICON_RIGHT_ARROW_SELECTED);

    private List<CollectableItem> weaponList = Game.getCharacter().weaponsList;
    private List<CollectableItem> armorList = Game.getCharacter().armorsList;

    private Integer cursorLevel;
    private Integer indexLevel0 = 0;
    private Integer indexLevel1 = 0;
    private Integer cursor;

    private Integer i;

    private JPanel itemGrid = createItemPanel();
    private JPanel itemTypePanel = createTypePanel();

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
            validate();

            setVisible(true);
        }
    };

    private JPanel eastBand = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            setOpaque(false);
            g.clearRect(0, 0, getWidth(), getHeight());
            setBounds(Constant.FRAME_WIDTH / 2, UPPER_BAND_HEIGTH, Constant.FRAME_WIDTH / 2, MID_BAND_HEIGTH);
            g.drawImage(Game.setCharPic(), Constant.FRAME_WIDTH / 14, ((MID_BAND_HEIGTH - (Constant.FRAME_HEIGHT/2)) / 2) - Math.round((MID_BAND_HEIGTH - (Constant.FRAME_HEIGHT/2)) / 10), Constant.FRAME_HEIGHT/2, Constant.FRAME_HEIGHT/2, null);

            repaint();
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

    /**********  Constructors  **********/

    public Inventory() {
        logger.debug("Inventory opened");

        cursorLevel = 0;

        Integer itemGridWidth = getGridWidth(cols[0], size[0], gap[0]);
        Integer itemGridHeight = getGridHeight(rows[0], size[0], gap[0]);
        Integer typeGridWidth = getGridWidth(cols[1], size[1], gap[1]);
        Integer typeGridHeight = getGridHeight(rows[1], size[1], gap[1]);

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

        GroupLayout layout = new GroupLayout(westBand);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap((itemGridWidth - typeGridWidth) / 2)
                                .addComponent(itemTypePanel, typeGridWidth, typeGridWidth, typeGridWidth)
                                .addGap((itemGridWidth - typeGridWidth) / 2)
                        )
                        .addGap(itemGridWidth)
                        .addComponent(itemGrid, itemGridWidth, itemGridWidth, itemGridWidth)
                        .addGap(0, 0, Short.MAX_VALUE)
                )
                .addGap(0)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup()
                        .addGap(typeGridHeight)
                        .addComponent(itemTypePanel, typeGridHeight, typeGridHeight, typeGridHeight)
                        .addGap(typeGridHeight)
                )
                .addGap(45)
                .addComponent(itemGrid, itemGridHeight, itemGridHeight, itemGridHeight)
                .addGap(0, 0, Short.MAX_VALUE)
        );

        westBand.setLayout(layout);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Méthode inutilisée
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyMap.ESCAPE) {
                    logger.debug("Inventory closed");
                    new GameMenu();
                    dispose();
                }
                if (e.getKeyCode() == KeyMap.LEFT) {
                    new Player(Sounds.MENU, false);
                    if (cursorLevel == 0) {
                        indexLevel0--;
                        if (indexLevel0 < 0) indexLevel0 = 2;
                        selectCategory();
                    } else {
                        indexLevel1--;
                        if (indexLevel1 < 0) indexLevel1 = cols[0] - 1;
                        selectEquipement();
                    }
                }
                if (e.getKeyCode() == KeyMap.RIGHT) {
                    new Player(Sounds.MENU, false);
                    if (cursorLevel == 0) {
                        indexLevel0++;
                        if (indexLevel0 >= 3) indexLevel0 = 0;
                        selectCategory();
                    } else {
                        indexLevel1++;
                        if (indexLevel1 >= cols[0]) indexLevel1 = 0;
                        selectEquipement();
                    }
                }
                if (e.getKeyCode() == KeyMap.UP) {
                    new Player(Sounds.MENU, false);
                    cursorLevel--;
                    if (cursorLevel < 0) {
                        cursorLevel = 4;
                        indexLevel1 = 0;
                        selectEquipement();
                        selectCategory();
                    } else if (cursorLevel == 0) {
                        selectEquipement();
                        selectCategory();
                        indexLevel1 = 0;
                    } else selectEquipement();
                }
                if (e.getKeyCode() == KeyMap.DOWN) {
                    new Player(Sounds.MENU, false);
                    cursorLevel++;
                    if (cursorLevel == 5) cursorLevel = 1;
                    if (cursorLevel > 0) {
                        selectCategory();
                        selectEquipement();
                    }
                }
                if (e.getKeyCode() == KeyMap.ENTER) {
                    try {
                        if (cursorLevel != 0) {
                            if (indexLevel0 == 0 && cursor < weaponList.size()) setEquipement(weaponList.get(cursor).getClass().toString().split("\\.")[4]);
                            else if (indexLevel0 == 1 && cursor < armorList.size()) setEquipement(armorList.get(cursor).getClass().toString().split("\\.")[4]);
                        }
                        Game.changePicture = true;
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Méthode inutilisée
            }
        });

        selectEquipement();

        setVisible(true);
    }

    /**********  Methods  **********/

    private JPanel createItemPanel() {
        JPanel itemGrid = new JPanel();

        itemGrid.setLayout(new GridLayout(rows[0], cols[0], gap[0], gap[0]));
        itemGrid.setBackground(new Color(0, 0, 0, 0));
//        itemGrid.setBackground(Color.MAGENTA);
        for (int i = 1; i <= (rows[0] * cols[0]); i++) {
            JButton item = new JButton();
            item.setBackground(new Color(0, 0, 0, 0));
            item.setOpaque(false);
            item.setBorderPainted(false);
            item.setIcon(ITEM);
            listItemBtn.add(item);
        }

        listItemBtn.forEach(btn -> itemGrid.add(btn));

        itemGrid.setVisible(true);

        return itemGrid;
    }

    private JPanel createTypePanel() {
        JPanel itemTypePanel = new JPanel();

        JButton leftArrow = new JButton();
        JButton weapon = new JButton();
        JButton clothes = new JButton();
        JButton others = new JButton();
        JButton rightArrow = new JButton();

        leftArrow.setIcon(LEFT_ARROW_SELECTED);
        weapon.setIcon(WEAPON_SELECTED);
        clothes.setIcon(CLOTHES);
        others.setIcon(OTHERS);
        rightArrow.setIcon(RIGHT_ARROW_SELECTED);

        listTypeBtn.add(leftArrow);
        listTypeBtn.add(weapon);
        listTypeBtn.add(clothes);
        listTypeBtn.add(others);
        listTypeBtn.add(rightArrow);

        itemTypePanel.setLayout(new GridLayout(rows[1], cols[1], gap[1], gap[1]));
        itemTypePanel.setBackground(new Color(0, 0, 0, 0));

        listTypeBtn.forEach(btn -> {
            btn.setBackground(new Color(0, 0, 0, 0));
            btn.setOpaque(false);
            btn.setBorderPainted(false);
            itemTypePanel.add(btn);
        });

        itemTypePanel.setVisible(true);

        return itemTypePanel;
    }

    public void selectCategory() {
        if (cursorLevel == 1 || cursorLevel == 4) {
            listTypeBtn.get(0).setIcon(LEFT_ARROW);
            listTypeBtn.get(4).setIcon(RIGHT_ARROW);
        }
        if (cursorLevel == 0) {
            listTypeBtn.get(0).setIcon(LEFT_ARROW_SELECTED);
            listTypeBtn.get(4).setIcon(RIGHT_ARROW_SELECTED);
            switch (indexLevel0) {
                case 0:
                    listTypeBtn.get(1).setIcon(WEAPON_SELECTED);
                    listTypeBtn.get(2).setIcon(CLOTHES);
                    listTypeBtn.get(3).setIcon(OTHERS);
                    break;
                case 1:
                    listTypeBtn.get(1).setIcon(WEAPON);
                    listTypeBtn.get(2).setIcon(CLOTHES_SELECTED);
                    listTypeBtn.get(3).setIcon(OTHERS);
                    break;
                default:
                    listTypeBtn.get(1).setIcon(WEAPON);
                    listTypeBtn.get(2).setIcon(CLOTHES);
                    listTypeBtn.get(3).setIcon(OTHERS_SELECTED);
            }
            selectEquipement();
        }
    }

    public ImageIcon convertImageToIcon(BufferedImage image) {
        ImageIcon icon = new ImageIcon(image.getScaledInstance(iconTypeSize, iconTypeSize, java.awt.Image.SCALE_SMOOTH ));;

        return icon;
    }

    /**
     * Charge l'image de la liste d'inventaire, qu'il y ait un équipement ou non.
     */
    public void selectEquipement() {
        cursor = (cursorLevel - 1) * 6 + indexLevel1;

        if (indexLevel0 == 0) getEquipementList("weapon");
        else if (indexLevel0 == 1) getEquipementList("armor");

        for (int y = i; y < cols[0] * rows[0]; y++) {
            if (y == cursor) listItemBtn.get(y).setIcon(setItemIcon("null", true));
            else listItemBtn.get(y).setIcon(setItemIcon("null", false));
        }
    }

    /**
     * Récupère la liste des équipements pour chaque catégorie de matériel.
     * 
     * @param category
     */
    public void getEquipementList(String category) {
        switch (category) {
            case "weapon":
                for (i = 0; i < weaponList.size(); i++) getItemPicture(i, weaponList); break;
            case "armor":
                for (i = 0; i < armorList.size(); i++) getItemPicture(i, armorList); break;
        }
    }

    /**
     * Récupère la classe du matériel à l'index i et charge l'image correspondante. 
     * 
     * @param index
     * @param list
     */
    public void getItemPicture(Integer index, List list) {
        String itemClass = list.get(index).getClass().toString().split("\\.")[4];
        if (index == cursor) listItemBtn.get(index).setIcon(setItemIcon(itemClass, true));
        else listItemBtn.get(index).setIcon(setItemIcon(itemClass, false));
    }

//    public void setWeaponPicture(Integer index) {
//        String itemClass = weaponList.get(index).getClass().toString().split("\\.")[4];
//        if (index == cursor) listItemBtn.get(index).setIcon(getWeaponIcon(itemClass, true));
//        else listItemBtn.get(index).setIcon(getWeaponIcon(itemClass, false));
//    }

    /**
     * Retourne l'image en fonction de la position du curseur.
     * 
     * @param itemClass
     * @param selected
     * @return
     */
    public Icon setItemIcon(String itemClass, boolean selected) {
        Integer size = 80;
        BufferedImage img = new BufferedImage(size, size, 2);

        Graphics2D g = img.createGraphics();
        g.fillRect(0, 0, size, size);
        g.drawImage(Images.ICON_ITEM_BG, null, 0, 0);
        switch (itemClass) {
            case "BootsLeather": g.drawImage(Images.ARMOR_LEATHER_BOOTS, null, 0, 0); break;
            case "PantsBlue": g.drawImage(Images.ARMOR_PANTS_BLUE, null, 0, 0); break;
            case "TShirtGreen": g.drawImage(Images.ARMOR_TSHIRT_GREEN, null, 0, 0); break;
            case "Dagger": g.drawImage(Images.WEAPON_DAGGER, null, 0, 0); break;
            case "Sword": g.drawImage(Images.WEAPON_SWORD, null, 0, 0); break;
            default: break;
        }
        if (selected) g.drawImage(Images.ICON_ITEM_BG_SELECTED, null, 0, 0);
        Icon icon = new ImageIcon(img.getScaledInstance(iconItemSize, iconItemSize, java.awt.Image.SCALE_SMOOTH ));

        return icon;
    }

    private Integer getGridWidth(Integer cols, Integer itemSize, Integer gap) {
        Integer width = itemSize * cols + gap * (cols - 1);

        return width;
    }

    private Integer getGridHeight(Integer rows, Integer itemSize, Integer gap) {
        Integer height = itemSize * rows + gap * (rows - 1);

        return height;
    }

    public void setEquipement(String equipement) throws IOException {
        new Player(Sounds.EQUIP_WEAPON, false);
        Animation character = Game.getCharacter();
        switch (equipement) {
            case "Sword": character.setRightHand(RightHand.SWORD); break;
            case "Dagger": character.setRightHand(RightHand.DAGGER); break;
            case "TShirtGreen": character.setTorso(Torso.TSHIRT_GREEN); break;
            case "PantsBlue": character.setLegs(Legs.BLUE_PANTS); break;
            case "BootsLeather": character.setFeet(Feet.LEATHER_BOOTS); break;
            default: break;
        }
        character.createNewCharacter();
    }

//    public void getItemlist() {
//        Game.getCharacter().itemList.forEach(e -> {
//            item.computeIfPresent(e.getName(), (key, val) -> val + e.getQuantity());
//            item.computeIfAbsent(e.getName(), val -> e.getQuantity());
//        });
//        this.item.forEach((key, val) -> {
//            this.itemListModel.addItem(new Item(key, val));
//        });
//
//        itemListCol = itemList.getColumnModel().getColumn(1);
//        JTextField textBox = new JTextField();
//        itemListCol.setCellEditor(new DefaultCellEditor(textBox));
//        itemList.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                System.out.println(itemList.getValueAt(itemList.getSelectedRow(), 0).toString());
//                try {
//                    setEquipement(itemList.getValueAt(itemList.getSelectedRow(), 0).toString());
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
//            }
//        });
//    }
}
