package ru.vood.Plugin.dialogs.ExtSwing;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class JDBTableIndexModel extends AbstractTableModel {

    ArrayList<VBdObjectEntity> rows = new ArrayList<VBdObjectEntity>();

    ArrayList<String> cols = new ArrayList<String>();

    public JDBTableIndexModel(ArrayList<VBdObjectEntity> rows, String col) {
        this.rows = rows;
        this.cols.add(col);
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return cols.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rows.get(rowIndex) != null && rows.get(rowIndex).getId() != null) {
            if (columnIndex == 0) {
                return rows.get(rowIndex).getCode();
            }
        }
        return null;
    }
}
