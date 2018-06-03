package ru.vood.Plugin.dialogs.ExtSwing;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectEntityService;
import ru.vood.Plugin.logging.Log;
import ru.vood.core.runtime.exception.ApplicationErrorException;

import javax.persistence.PersistenceException;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
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
    private static Log log = Log.getLogger(JDBTree.class);
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
//        VBdObjectEntityService sdds = (VBdObjectEntityService) VBdObjectEntity.getServise(VBdObjectEntity.class);
//        VBdObjectEntityService bdObjectEntityService = ADMMain.getCtx().getBean("jpaVBdObjectEntityService", VBdObjectEntityService.class);
        VBdObjectEntityService bdObjectEntityService = LoadedCTX.getService(VBdObjectEntityService.class);

        String[] typeObjectCodeS = {"TABLE", "REFERENCE", "ARRAY", "STRING", "NUMBER", "DATE", "BOOLEAN"};

        ArrayList<VBdObjectEntity> bdObjects = null;
        try {
            bdObjects = (ArrayList<VBdObjectEntity>) bdObjectEntityService.findByTypeObjectCodeIn(typeObjectCodeS);
        } catch (PersistenceException e) {
            throw new ApplicationErrorException("Не удалось загрузить дерево");
        }

        /*BDObject bdObject = new BDObject();
        Query<BDObject> setToObject = new Query<BDObject>(bdObject);
        TreeSet<BDObject> bdObjects = setToObject.viewToSetWithContext(SystemObject.VW_CLASS_FOR_TREE, null);
        */


        TreeMap<BigDecimal, DefaultMutableTreeNode> nodeTreeMap = new TreeMap<>();

        DefaultTreeModel model = (DefaultTreeModel) this.getModel();

        DefaultMutableTreeNode sel = null;
        DefaultMutableTreeNode tmp = null;
        DefaultMutableTreeNode node;
        //VBdObjectEntity objectTemp = null;

        for (VBdObjectEntity obj1 : bdObjects) {
/*            if (!obj1.getJavaClass().equals(BDObject.class.toString().substring(6))) {
                try {
                    objectTemp = (BDObject) Class.forName(obj1.getJavaClass()).newInstance();
                    objectTemp = (BDObject) objectTemp.selectFromBase("ID", obj1.getId().toString());
                    //ToDo тут ломается
                } catch (InstantiationException e) {
                    throw new ApplicationErrorException("Не удалось получить объект типа " + obj1.getJavaClass() + " и ID = " + obj1.getId().toString() + " причина " + e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    throw new ApplicationErrorException("Не удалось получить объект типа " + obj1.getJavaClass() + " и ID = " + obj1.getId().toString() + " причина " + e.getMessage(), e);
                } catch (ClassNotFoundException e) {
                    throw new ApplicationErrorException("Не удалось получить объект типа " + obj1.getJavaClass() + " и ID = " + obj1.getId().toString() + " причина " + e.getMessage(), e);
                }
            } else {
                objectTemp = obj1;
            }*/

            if (obj1.getParent() != null) {
                /*for (VBdObjectEntity obj2 : bdObjects) {
                    if (objectTemp.getParent().equals(obj2)) {
                        objectTemp.setParent(obj2);
                        break;
                    }
                }*/
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
        DefaultMutableTreeNode defaultMutableTreeNode =
                ((DefaultMutableTreeNode) model.getRoot()).getNextNode();
        defaultMutableTreeNode = (DefaultMutableTreeNode) model.getRoot();
        while (defaultMutableTreeNode != null) {
            if (bdTable.getParent() != null && bdTable.getParent().equals(defaultMutableTreeNode.getUserObject())) {
                DefaultMutableTreeNode tmp = new DefaultMutableTreeNode(bdTable);
                model.insertNodeInto(tmp, defaultMutableTreeNode, defaultMutableTreeNode.getChildCount());
                break;
            }
            defaultMutableTreeNode = defaultMutableTreeNode.getNextNode();
        }
    }

  /*  DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer()
    {
        Color draggingBackground = new Color(0, 0, 128);
        Color draggingForeground = Color.WHITE;
        Color standardBackground = getBackgroundNonSelectionColor();
        Color standardForeground = getTextNonSelectionColor();
        Icon homeIcon = new ImageIcon("C:\\_developer\\java\\Plugins\\ADMPluginIDE\\resourses\\70262443.jpg");
        /*Icon trashIcon = new ImageIcon("Delete.gif");
        Icon inboxIcon = new ImageIcon("Import24.gif");
        Icon outboxIcon = new ImageIcon(ICON_DIR + "SendMail24.gif");
        Icon recentIcon = new ImageIcon(ICON_DIR + "History24.gif");
        Icon helpIcon = new ImageIcon(ICON_DIR + "TipOfTheDay24.gif");*/

    /*    public Component getTreeCellRendererComponent(JTree tree,
                                                      Object value, boolean sel, boolean expanded,
                                                      boolean leaf, int row, boolean hasFocus)
        {

            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;

            // Set colors for dragging
            if (treeMouseListener != null)
            {
                Node dragOverNode = treeMouseListener.getDraggingOverNode();
                if (dragOverNode != null && value.equals(dragOverNode))
                {
                    setBackgroundNonSelectionColor(draggingBackground);
                    setTextNonSelectionColor(draggingForeground);
                    sel = false;
                }
                else
                {
                    setBackgroundNonSelectionColor(standardBackground);
                    setTextNonSelectionColor(standardForeground);
                }
            }
            Component res = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

            setIcon(homeIcon);

            // TODO Trash icon
            return res;
        }
    };
*/
}
/*DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer()
        {
            Color draggingBackground = new Color(0, 0, 128);
            Color draggingForeground = Color.WHITE;
            Color standardBackground = getBackgroundNonSelectionColor();
            Color standardForeground = getTextNonSelectionColor();
            Icon homeIcon = new ImageIcon(ICON_DIR + "Home24.gif");
            Icon trashIcon = new ImageIcon(ICON_DIR + "Delete.gif");
            Icon inboxIcon = new ImageIcon(ICON_DIR + "Import24.gif");
            Icon outboxIcon = new ImageIcon(ICON_DIR + "SendMail24.gif");
            Icon recentIcon = new ImageIcon(ICON_DIR + "History24.gif");
            Icon helpIcon = new ImageIcon(ICON_DIR + "TipOfTheDay24.gif");

            public Component getTreeCellRendererComponent(JTree tree,
                            Object value, boolean sel, boolean expanded,
    boolean leaf, int row, boolean hasFocus)
            {

                Node node = (Node)value;

                // Set colors for dragging
                if (treeMouseListener != null)
                {
                    Node dragOverNode = treeMouseListener.getDraggingOverNode();
                    if (dragOverNode != null && value.equals(dragOverNode))
                    {
                        setBackgroundNonSelectionColor(draggingBackground);
                        setTextNonSelectionColor(draggingForeground);
                        sel = false;
                    }
                    else
                    {
                        setBackgroundNonSelectionColor(standardBackground);
                        setTextNonSelectionColor(standardForeground);
                    }
                }
                Component res = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

                // Set icons for system folders
                if (node instanceof FolderNode)
                {
                    if (node.equals(Repository.getHomeNode()))
                    {
                        setIcon(homeIcon);
                    }
                    else
                    if (node.equals(Repository.getInboxNode()))
                    {
                        setIcon(inboxIcon);
                    }
                    else
                    if (node.equals(Repository.getOutboxNode()))
                    {
                        setIcon(outboxIcon);
                    }
                    else
                    if (node.equals(Repository.getHelpNode()))
                    {
                        setIcon(helpIcon);
                    }
                    else
                    if (node.equals(Repository.getRecentNode()))
                    {
                        setIcon(recentIcon);
                    }
                    else
                    if (node.equals(Repository.getTrashNode()))
                    {
                        setIcon(trashIcon);
                    }
                    else
                    {
                        setIcon(expanded ? openIcon : closedIcon);
                    }
                }
                else
                {
                    setIcon(leafIcon);
                }
                // TODO Trash icon
                return res;
            }
        };
        tree.setCellRenderer(renderer);
* */