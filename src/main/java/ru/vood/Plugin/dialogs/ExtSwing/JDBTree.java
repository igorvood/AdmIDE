package ru.vood.Plugin.dialogs.ExtSwing;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectEntityService;
import ru.vood.core.runtime.exception.ApplicationErrorException;

import javax.persistence.PersistenceException;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * ВАЖНО - работа с уже готовым деревом может производится только через модель дерева.
 * Только в этом случае гарантируется правильная работа и вызов событий
 * В противном случае новые узлы могут быть не прорисованы
 * <p/>
 * show JTtree from ResultSet.
 *
 * @see JTree
 */

public class JDBTree extends JTree {
    private static JDBTree tree;
    private static boolean loaded = false;
    //private ResultSet _r = null;

    private JDBTree() {
        super();
        this.setRootVisible(false);
        //this.loadTree();
    }

    public static JDBTree getInstance() {
        if (tree == null) {
            tree = new JDBTree();
        }
        return tree;
    }

    /**
     * Добавляет в дерево класс ac, место куда нужно его добавть ищется по свойству родительский класс
     *
     * @param bdObject
     */
   /* private void addToTree(VBdObjectEntity bdObject) {
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        DefaultMutableTreeNode node = ((DefaultMutableTreeNode) model.getRoot());
        while (node.getNextNode() != null && node.getUserObject() != bdObject.getParent()) {
            node = (DefaultMutableTreeNode) node.getNextNode();
        }
        DefaultMutableTreeNode sel = node;
        DefaultMutableTreeNode tmp = new DefaultMutableTreeNode(bdObject);
        model.insertNodeInto(tmp, sel, sel.getChildCount());

        model.reload();
    }*/
    public void refresh() {
        loaded = false;
        loadTree();
    }

    public void loadTree() {
        if (loaded) {
            return;
        }
        VBdObjectEntityService bdObjectEntityService = LoadedCTX.getService(VBdObjectEntityService.class);

        String[] typeObjectCodeS = {"TABLE", "REFERENCE", "ARRAY", "STRING", "NUMBER", "DATE", "BOOLEAN"};

        ArrayList<VBdObjectEntity> bdObjects = null;
        try {
            bdObjects = (ArrayList<VBdObjectEntity>) bdObjectEntityService.findByTypeObjectCodeIn(typeObjectCodeS);
        } catch (PersistenceException e) {
            throw new ApplicationErrorException("Не удалось загрузить дерево");
        }

        TreeMap<BigDecimal, DefaultMutableTreeNode> nodeTreeMap = new TreeMap<>();

        DefaultTreeModel model = (DefaultTreeModel) this.getModel();

        DefaultMutableTreeNode sel;
        DefaultMutableTreeNode tmp;

        for (VBdObjectEntity obj1 : bdObjects) {

            if (obj1.getParent() != null) {
                sel = nodeTreeMap.get(obj1.getParent().getId());
                tmp = new DefaultMutableTreeNode(obj1);

                model.insertNodeInto(tmp, sel,
                        sel.getChildCount());
                model.nodeStructureChanged(sel);
                nodeTreeMap.put(obj1.getId(), tmp);
            } else {
                //добавление корневых элементов
                sel = new DefaultMutableTreeNode(obj1);
                model = new DefaultTreeModel(sel);
                this.setModel(model);
                this.setRootVisible(true);
                model.nodeStructureChanged(sel);
                nodeTreeMap.put(obj1.getId(), sel);
            }
        }
        loaded = true;
    }

    public void addToTree(VBdObjectEntity bdTable) {
        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) model.getRoot();
        while (defaultMutableTreeNode != null) {
            if (bdTable.getParent() != null && bdTable.getParent().equals(defaultMutableTreeNode.getUserObject())) {
                DefaultMutableTreeNode tmp = new DefaultMutableTreeNode(bdTable);
                model.insertNodeInto(tmp, defaultMutableTreeNode, defaultMutableTreeNode.getChildCount());
                model.nodeChanged(tmp);
                break;
            }
            defaultMutableTreeNode = defaultMutableTreeNode.getNextNode();
        }
    }

    public void gotoObjectOnTree(VBdObjectEntity entity) {
        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) model.getRoot();
        while (defaultMutableTreeNode != null) {
            if (defaultMutableTreeNode.getUserObject().equals(entity)) {
                break;
            }
            defaultMutableTreeNode = defaultMutableTreeNode.getNextNode();
        }

        TreeNode[] nodes = ((DefaultTreeModel) tree.getModel()).getPathToRoot(defaultMutableTreeNode);
        TreePath tpath = new TreePath(nodes);
        tree.setSelectionPath(tpath);
    }


}