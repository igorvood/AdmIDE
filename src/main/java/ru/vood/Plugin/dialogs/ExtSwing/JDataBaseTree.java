package ru.vood.Plugin.dialogs.ExtSwing;

import ru.vood.Plugin.logging.Log;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.sql.ResultSet;


/**
 * ВАЖНО - работа с уже готовым деревом может производится только через модель дерева.
 * Только в этом случае гарантируется правильная работа и вызов событий
 * В противном случае новые узлы могут быть не прорисованы
 * <p/>
 * show JTtree from ResultSet.
 *
 * @see JTree
 */

public class JDataBaseTree extends JTree {
    private static JDataBaseTree tree;
    private Log log = Log.getLogger(JDataBaseTree.class);
    private ResultSet _r = null;

    private JDataBaseTree() {
        super();
    }

    private JDataBaseTree(DefaultMutableTreeNode root) {
        super(root, false);
    }

    private JDataBaseTree(DefaultMutableTreeNode root, boolean asksAllowsChildren) {
        this(new DefaultMutableTreeNode(root, asksAllowsChildren));
    }


    /**
     * @param r
     */
    private JDataBaseTree(ResultSet r) {
        super();
        this.setRootVisible(false);

        // ToDo поработать с загрузкой дерева
        //loadTree(r);
    }

    public static JDataBaseTree getInstance() {
        if (tree == null) {
            tree = new JDataBaseTree();
        }
        return tree;
    }

    public static JDataBaseTree getInstance(ResultSet r) {
        if (tree == null) {
            tree = new JDataBaseTree(r);
        }
        return tree;
    }

    /**
     * Добавляет в дерево класс list, место куда нужно его добавть ищется по свойству родительский класс
     *
     * @param list
     */
    public static void addToTree(DataBaseTreeNode list) {
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode node = ((DefaultMutableTreeNode) model.getRoot());
        while (node.getNextNode() != null && node.getUserObject() != list.getParentNode()) {
            node = node.getNextNode();
        }
        DefaultMutableTreeNode sel = node;
        DefaultMutableTreeNode tmp = new DefaultMutableTreeNode(list);
        model.insertNodeInto(tmp, sel, sel.getChildCount());

        model.reload();
    }

/*
    public void loadTree(ResultSet r1) {
        this._r = r1;
        int q = 1;
        boolean isRoot = true;
        // ВАЖНО - работа с уже готовым деревом может производится только через модель дерева.
        // Только в этом случае гарантируется правильная работа и вызов событий
        // В противном случае новые узлы могут быть не прорисованы

        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
        DefaultMutableTreeNode tmp = null;
        DefaultMutableTreeNode sel = null;
        //ExtAllClass ac = null;
        Allclass ac = null;
        int prevLevel = 0;
        try {
            if (_r != null) {
                while (_r.next()) {
                    */
/*putToLog(q + "--------------- Очередная запись " + _r.getString(1) + "_" + _r.getString(5) +
                            "_" + _r.getString(3) + "_" + _r.getString(6));*//*

                    q++;
                    Query<ExtAllClass> resultSetToObject = new Query<ExtAllClass>(new ExtAllClass(), "");

                    ac = resultSetToObject.getOneObjFromBaseByWhere("ID_NUM = " + _r.getBigDecimal("ID_NUM"), false);
                    */
/*ExtAllClass arrClass = ExtAllClass.getById(_r.getString("ARR_CLASS"));
                    ExtAllClass refClass = ExtAllClass.getById(_r.getString("REF_CLASS"));*//*

                    // первый проход, это корень
                    if (isRoot) {
*/
/*                        ac = new ExtAllClass( _r.getString(2), _r.getString(3), _r.getString(4), _r.getString(5), null, _r.getString(7), arrClass,
                                refClass);*//*

                        */
/*ac = new ExtAllClass(_r.getString("HAS_INSTANCES"), _r.getString("ID"), _r.getString("IS_COLLECTION"), _r.getString("NAME"), null, _r.getString("TYPE"), arrClass,
                                refClass);*//*

                        sel = new DefaultMutableTreeNode(ac);
                        putToLog(" Корень " + ac, Level.INFO);
                        model = new DefaultTreeModel(sel);
                        this.setModel(model);
                        this.setRootVisible(true);
                        model.nodeStructureChanged(sel);

                        isRoot = false;
                        prevLevel = _r.getInt("c_level");
                    } else {

                        if (_r.getInt(1) > prevLevel) {
                           */
/* ac = new ExtAllClass(_r.getString("HAS_INSTANCES"), _r.getString("ID"), _r.getString("IS_COLLECTION"), _r.getString("NAME"), (ExtAllClass) sel.getUserObject(),
                                    _r.getString("TYPE"), arrClass, refClass);*//*

                            tmp = new DefaultMutableTreeNode(ac);
                            model.insertNodeInto(tmp, sel, sel.getChildCount());
                            model.nodeStructureChanged(tmp);
                            prevLevel = _r.getInt("c_level");
                            sel = tmp;
                        } else {
                            if (_r.getInt("c_level") < prevLevel) {
                                for (int i = prevLevel; _r.getInt("c_level") < i; i--) {
                                    sel = (DefaultMutableTreeNode) sel.getParent();
                                }
                                */
/*ac = new ExtAllClass(_r.getString("HAS_INSTANCES"), _r.getString("ID"), _r.getString("IS_COLLECTION"), _r.getString("NAME"),
                                        (ExtAllClass) ((DefaultMutableTreeNode) sel.getParent()).getUserObject(), _r.getString("TYPE"), arrClass,
                                        refClass);*//*

                                tmp = new DefaultMutableTreeNode(ac);
                                model.insertNodeInto(tmp, (DefaultMutableTreeNode) sel.getParent(),
                                        sel.getParent().getChildCount());
                                model.nodeStructureChanged(tmp);
                                prevLevel = _r.getInt("c_level");
                                sel = tmp;
                            } else {
                                if (_r.getInt("c_level") == prevLevel) {
                                   */
/* ac =
                                            new ExtAllClass(_r.getString("HAS_INSTANCES"), _r.getString("ID"), _r.getString("IS_COLLECTION"), _r.getString("NAME"),
                                                    (ExtAllClass) ((DefaultMutableTreeNode) sel.getParent()).getUserObject(), _r.getString("TYPE"), arrClass,
                                                    refClass);*//*


                                    tmp = new DefaultMutableTreeNode(ac);
                                    model.insertNodeInto(tmp, (DefaultMutableTreeNode) sel.getParent(),
                                            sel.getChildCount());
                                    model.nodeStructureChanged(tmp);
                                    sel = tmp;
                                }
                            }
                        }
                    }
                }
            }
            //------------------------------------------delete--------------------------------------------
            //Заполню по умолчанию, для тестирования без соединения
            if (_r == null) */
/*|| !_r.isFirst()*//*
 {
                DBConnect.closeConnection();
                ExtAllClass root = new ExtAllClass("0", "OBJECT", "0", "Объекты", null, "NUMBER", null, null);
                sel = new DefaultMutableTreeNode(root);
                this.setModel(new DefaultTreeModel(sel));

                ac = new ExtAllClass("0", "REFERENCE", "0", "Ссылки", root, "NUMBER", null, null);
                tmp = new DefaultMutableTreeNode(ac);
                model.insertNodeInto(tmp, sel, sel.getChildCount());


                ac = new ExtAllClass("0", "ARRAY", "0", "Массивы", root, "NUMBER", null, null);
                tmp = new DefaultMutableTreeNode(ac);
                model.insertNodeInto(tmp, sel, sel.getChildCount());

                ac = new ExtAllClass("0", "STRING", "0", "Строки", root, "varchar2", null, null);
                tmp = new DefaultMutableTreeNode(ac);
                model.insertNodeInto(tmp, sel, sel.getChildCount());

                ac = new ExtAllClass("0", "NUMBER", "0", "Числа", root, "NUMBER", null, null);
                tmp = new DefaultMutableTreeNode(ac);
                model.insertNodeInto(tmp, sel, sel.getChildCount());

                ac = new ExtAllClass("0", "DATE", "0", "Даты", root, "DATE", null, null);
                tmp = new DefaultMutableTreeNode(ac);
                model.insertNodeInto(tmp, sel, sel.getChildCount());

                ac = new ExtAllClass("0", "SPR", "0", "Справочники", root, "SPR", null, null);
                tmp = new DefaultMutableTreeNode(ac);
                model.insertNodeInto(tmp, sel, sel.getChildCount());
            }
            //------------------------------------------delete--------------------------------------------
        } catch (SQLException e) {
            putToLog(" Не удалось загрузить дерево.", Level.SEVERE, e);
        } catch (NullPointerException e) {
            putToLog(" Не удалось загрузить дерево.", Level.SEVERE, e);
        }
    }
*/

    /**
     * Returns the <code>TreeModel</code> that is providing the data.
     *
     * @return the <code>TreeModel</code> that is providing the data
     */
    @Override
    public javax.swing.tree.TreeModel getModel() {
        return super.getModel();
    }


}
