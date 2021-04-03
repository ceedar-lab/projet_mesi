package com.mesi.panels;

import com.mesi.pojo.Item;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class InventoryModel extends AbstractTableModel {

    private final List<Item> items = new ArrayList<>();

    private String[] entete = {"équipement", "nombre"};

    public InventoryModel() {
        super();
    }

    public int getRowCount() {
        return items.size();
    }

    public int getColumnCount() {
        return entete.length;
    }

    public String getColumnName(int columnIndex) {
        return entete[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return items.get(rowIndex).getNom();
            case 1:
                return items.get(rowIndex).getQuantite();
            default:
                return null; //Ne devrait jamais arriver
        }
    }

    public void addItem(Item item) {
        items.add(item);

        fireTableRowsInserted(items.size() -1, items.size() -1);
    }

    public void removeItem(int rowIndex) {
        items.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
