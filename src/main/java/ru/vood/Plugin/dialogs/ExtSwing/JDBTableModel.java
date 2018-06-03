package ru.vood.Plugin.dialogs.ExtSwing;

import ru.vood.Plugin.admPlugin.entityHiber.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.entityHiber.intf.VBdColomnsEntityService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class JDBTableModel extends AbstractTableModel {
    ArrayList<VBdObjectEntity> rows = new ArrayList<VBdObjectEntity>();
    //ArrayList<Object> rows = new ArrayList<Object>();
    ArrayList<String> cols = new ArrayList<String>();

    public JDBTableModel() {
        cols.add("Короткое имя");
        cols.add("Длинное имя");
        cols.add("Тип");
        cols.add("Класс владелец");
        // Todo удалить
        cols.add("test");
        rows.add(new VBdObjectEntity());
    }

    /**
     * Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    @Override
    public int getRowCount() {
        return rows.size();
    }

    /**
     * Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    @Override
    public int getColumnCount() {
        return cols.size();
    }

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rows.get(rowIndex) != null && rows.get(rowIndex).getId() != null) {

            if (columnIndex == 0) {
                return rows.get(rowIndex).getCode();
            }
            if (columnIndex == 1) {
                return rows.get(rowIndex).getName();
            }
            if (columnIndex == 2) {
                if (rows.get(rowIndex).getTypeObject() == null) {
                    return null;
                } else {
                    return rows.get(rowIndex).getTypeObject().getName();
                }
            }
            if (columnIndex == 3) {
                if (rows.get(rowIndex).getParent() == null) {
                    return null;
                } else {
                    return rows.get(rowIndex).getParent().getCode();
                }
            }
            // Todo удалить
            if (columnIndex == 4) {
                JComboBox box = new JComboBox();
                box.addItem("asd");
                box.addItem("123");
                return box;
            }

        }
        return null;
    }

    /**
     * Returns a default name for the column using spreadsheet conventions:
     * A, B, C, ... Z, AA, AB, etc.  If <code>column</code> cannot be found,
     * returns an empty string.
     *
     * @param column the column being queried
     * @return a string containing the default name of <code>column</code>
     */
    @Override
    public String getColumnName(int column) {
        return cols.get(column);
    }

    /**
     * Returns false.  This is the default implementation for all cells.
     *
     * @param rowIndex    the row being queried
     * @param columnIndex the column being queried
     * @return false
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public boolean loadTableByObj(VBdObjectEntity bdObject) {
        boolean isEmpty = true;
        if (bdObject == null) {
            return false;
        }
        //if (bdObject instanceof BDTable) {

        /*VBdColomnsEntity bdColomns = new VBdColomnsEntity();
        Query<VBdColomnsEntity> bdColomnsResultSetToObject = new Query<VBdColomnsEntity>(bdColomns, AppConst.getTune(ListTunes.PREFIX_COLOMN));
        TreeSet<VBdColomnsEntity> colomnsTreeSet = bdColomnsResultSetToObject.viewToSet(SystemObject.VW_COLOMN_FOR_TABLE, new NameValuePair("PARENT", bdObject.getId().toString()));*/

        VBdColomnsEntityService vBdColomnsEntityService = (VBdColomnsEntityService) VBdColomnsEntity.getServise(VBdColomnsEntity.class);
        ArrayList<VBdColomnsEntity> colomns = (ArrayList<VBdColomnsEntity>) vBdColomnsEntityService.findByParent(bdObject);

        for (VBdColomnsEntity col : colomns) {
            rows.add(col);
            isEmpty = false;
        }
        // }
        if (isEmpty) {
            rows.add(new VBdObjectEntity());
        }
        return true;
    }

    public void clear() {
        rows.clear();
        //rows.add(new BDObject());
    }
}