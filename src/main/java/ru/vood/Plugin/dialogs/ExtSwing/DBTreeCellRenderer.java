package ru.vood.Plugin.dialogs.ExtSwing;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.applicationConst.TypeObject;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class DBTreeCellRenderer extends DefaultTreeCellRenderer {
    /**
     *
     */


    private static final long serialVersionUID = 707287337017494764L;

    public DBTreeCellRenderer() {
        super();
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean isSelected, boolean expanded, boolean leaf, int row,
                                                  boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, isSelected, expanded,
                leaf, row, hasFocus);
        setForeground(getTextSelectionColor());
        DefaultMutableTreeNode treenode = (DefaultMutableTreeNode) value;
        VBdObjectEntity ob;
        //if (leaf) {
        if (treenode.getUserObject() instanceof VBdObjectEntity) {
            ob = (VBdObjectEntity) treenode.getUserObject();
            String userDir = System.getProperty("user.dir");
            try {
                if (ob.getTypeObject().getCode().equals(TypeObject.TABLE.getName()) && !ob.getCode().equals("OBJECT")) {
                    setIcon(new ImageIcon(userDir + "\\src\\main\\resources\\images\\tree\\_Table16.png"));
                } else if (ob.getTypeObject().getCode().equals(TypeObject.REFERENCE.getName())) {
                    setIcon(new ImageIcon(userDir + "\\src\\main\\resources\\images\\tree\\_reference16.png"));
                } else if (ob.getTypeObject().getCode().equals(TypeObject.DATE.getName())) {
                    setIcon(new ImageIcon(userDir + "\\src\\main\\resources\\images\\tree\\_Date16.png"));
                } else if (ob.getTypeObject().getCode().equals(TypeObject.ARRAY.getName())) {
                    setIcon(new ImageIcon(userDir + "\\src\\main\\resources\\images\\tree\\array16.png"));
                } else if (ob.getTypeObject().getCode().equals(TypeObject.NUMBER.getName())) {
                    setIcon(new ImageIcon(userDir + "\\src\\main\\resources\\images\\tree\\_number16.png"));
                } else if (ob.getTypeObject().getCode().equals(TypeObject.STRING.getName())) {
                    setIcon(new ImageIcon(userDir + "\\src\\main\\resources\\images\\tree\\_Text16.png"));
                }
            } catch (NullPointerException e) {
                System.out.println(this.getClass() + " " + e.getMessage());
            }
        }

        return this;
    }

    /**
     * Returns the icon used to represent leaf nodes.
     */
   /* @Override
    public Icon getLeafIcon() {
        this.
        return super.getLeafIcon();
    }*/
}