package ru.vood.Plugin.dialogs;

import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.admPlugin.tune.WorkWithTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.applicationConst.TypeObject;
import ru.vood.Plugin.dialogs.ExtSwing.DBTreeCellRenderer;
import ru.vood.Plugin.dialogs.ExtSwing.JAddDialog;
import ru.vood.Plugin.dialogs.ExtSwing.JDBTableModel;
import ru.vood.Plugin.dialogs.ExtSwing.JDBTree;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad.TuneChainStepsFirstLoad;
import ru.vood.core.runtime.exception.ApplicationErrorException;
import ru.vood.core.runtime.exception.CoreRuntimeException;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ADMDialog extends JAddDialog {
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem menuFileExit;
    private JMenuItem menuFileTune;
    private JMenuItem menuFileSave;
    private JMenuItem menuFileLoad;
    private JMenu menuTools;
    private JMenuItem menuFileToosRefresh;
    private JMenuItem menuFileToosFirstLoad;

    private JPopupMenu jPopupMenu;
    private JMenuItem jMenuItemAddSubType;
    private JMenuItem jMenuItemEditType;
    private JMenuItem jMenuItemAddToList;

    private JPopupMenu jPopupMenuTable;
    private JMenu menuAdd;
    private JMenuItem jMenuItemAddColomn;


    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JDBTree tree1;
    private JTable table1;
    private JProgressBar progressBar1;

    public ADMDialog() {
        //createUIComponents();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        tree1.addTreeSelectionListener(new TreeSelectionListener() {
            /**
             * Called whenever the value of the selection changes.
             *
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                try {
                    if (tree1.getLastSelectedPathComponent() != null && ((DefaultMutableTreeNode) tree1.getLastSelectedPathComponent()).getUserObject() != null) {
                        ((JDBTableModel) table1.getModel()).clear();

                        ((JDBTableModel) table1.getModel()).loadTableByObj((VBdObjectEntity) ((DefaultMutableTreeNode) tree1.getLastSelectedPathComponent()).getUserObject());
                        table1.updateUI();

                        System.out.println(this.getClass() + " Выбрали " + ((DefaultMutableTreeNode) tree1.getLastSelectedPathComponent()).getUserObject());
                    }

                } catch (CoreRuntimeException qw) {
                    System.out.println(qw.toString());
                    qw.printStackTrace();
                    //System.out.println(qw.getStackTrace().toString());
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    @Override
    protected void extension() {
        this.setSize(new Dimension(1200, 550));
        workMenuBar();
        workPopupMenu();
        workTree();
    }

    private void workMenuBar() {
        menuBar = new JMenuBar();
        menuFile = new JMenu();
        menuFile.setText("File");
        {
            menuFileSave = new JMenuItem();
            menuFileSave.setText("Save");
            menuFileSave.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    fileTune_ActionPerformed(ae);
                }

                private void fileTune_ActionPerformed(ActionEvent ae) {

                    String defaultFolder = AppConst.getTune(ListTunes.DEFAULT_FOLDER);
                    File file;
                    JFileChooser fileopen = new JFileChooser(defaultFolder);
                    fileopen.setDialogType(JFileChooser.SAVE_DIALOG);
                    int ret = fileopen.showDialog(null, "Save File");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        file = fileopen.getSelectedFile();
                        WorkWithTunes.getInstance().setTuneValue(ListTunes.DEFAULT_FOLDER.getName(), file.getParent());
                        //ExportInterface save = new Save();
                        //Todo кусок для проверки, удалить его
/*
                        ExportObjects exportObjects = new ExportObjects();
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree1.getModel().getRoot();
                        while (node.getNextNode() != null) {
                            VBdObjectEntity bdObject = (VBdObjectEntity) node.getUserObject();
                            exportObjects.set(new Varchar2(bdObject.getId().toString()), bdObject);
                            node = node.getNextNode();
                        }
*/

                        //
                        //save.putOut(exportObjects, file, null);

                    }

                    /*File f = new File("Tunes.xml");
                    ADMTuneDialog tuneDialog = new ADMTuneDialog(f);
                    tuneDialog.pack();
                    tuneDialog.setVisible(true);*/
                    //todo Пока это действие не закодировано, реализовать
                    System.out.println("Пока это действие не закодировано");
                }
            });
            menuFile.add(menuFileSave);

            menuFileLoad = new JMenuItem();
            menuFileLoad.setText("Load");
            menuFileLoad.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    fileTune_ActionPerformed(ae);
                }

                private void fileTune_ActionPerformed(ActionEvent ae) {
                    /*File f = new File("Tunes.xml");
                    ADMTuneDialog tuneDialog = new ADMTuneDialog(f);
                    tuneDialog.pack();
                    tuneDialog.setVisible(true);*/
                    //todo Пока это действие не закодировано, реализовать
                    System.out.println("Пока это действие не закодировано");
                }
            });
            menuFile.add(menuFileLoad);


            menuFileTune = new JMenuItem();
            menuFileTune.setText("Tune");
            menuFileTune.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    fileTune_ActionPerformed(ae);
                }

                private void fileTune_ActionPerformed(ActionEvent ae) {
                    throw new ApplicationErrorException("Изменение конфигурации на лету не возможною");
//                    File f = new File("Tunes.xml");
//                    ADMTuneDialog tuneDialog = new ADMTuneDialog(f);
//                    tuneDialog.pack();
//                    tuneDialog.setVisible(true);
                }
            });
            menuFile.add(menuFileTune);

            menuFileExit = new JMenuItem();
            menuFileExit.setText("Exit");
            menuFileExit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    fileExit_ActionPerformed(ae);
                }

                private void fileExit_ActionPerformed(ActionEvent ae) {
                    onCancel();
                }
            });
            menuFile.add(menuFileExit);
        }

        menuTools = new JMenu();
        menuTools.setText("Tools");
        {
            menuFileToosRefresh = new JMenuItem();
            menuFileToosRefresh.setText("Refresh");
            menuFileToosRefresh.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        tree1.refresh();
                    } catch (CoreRuntimeException e) {
                        new ErrWin(null, "Обновление структуры данных не удалось.", true, "Обновление структуры данных не удалось. \n" + e.getErrorMessage(), e);
                    }
                }

                private void FirstLoad_ActionPerformed(ActionEvent ae) {
                    TuneChainStepsFirstLoad.runChain();
                    workTree();
                }
            });
            menuTools.add(menuFileToosRefresh);
        }

        {
            menuFileToosFirstLoad = new JMenuItem();
            menuFileToosFirstLoad.setText("First Load");
            menuFileToosFirstLoad.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                        FirstLoad_ActionPerformed(ae);
                    } catch (CoreRuntimeException e) {
                        new ErrWin(null, "Первоначальная загрузка не удалась.", true, "Первоначальная загрузка не удалась. " + e.getErrorMessage(), e);
                    }
                }

                private void FirstLoad_ActionPerformed(ActionEvent ae) {
                    TuneChainStepsFirstLoad.runChain();
                    workTree();
                }
            });
            menuTools.add(menuFileToosFirstLoad);
        }
        this.setJMenuBar(menuBar);
        menuBar.add(menuFile);
        menuBar.add(menuTools);
    }

    /*private void createUIComponents() {
        //table1 = new JTable(new DBTableModel());
        s = JDBTree.getInstance();
        tree1.setCellRenderer(new DBTreeCellRenderer());

    }*/

    private void workTree() {
        try {

            tree1.loadTree();
            tree1.setCellRenderer(new DBTreeCellRenderer());
        } catch (ApplicationErrorException exception) {
            exception.printStackTrace();
            System.out.println(this.getClass() + " Необходимо загрузить талицы. ");
            new MessageWin("Необходимо загрузить талицы. " + exception.getMessage());

        }
    }

    private void workPopupMenu() {
        jPopupMenu = new JPopupMenu();
        {
            jMenuItemAddSubType = new JMenuItem();
            jMenuItemAddSubType.setText("Add");
            jMenuItemAddSubType.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addOrEdit((VBdObjectEntity) ((DefaultMutableTreeNode) tree1.getLastSelectedPathComponent()).getUserObject(), true);
                }
            });
        }
        {
            jMenuItemEditType = new JMenuItem();
            jMenuItemEditType.setText("Edit");
            jMenuItemEditType.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addOrEdit((VBdObjectEntity) ((DefaultMutableTreeNode) tree1.getLastSelectedPathComponent()).getUserObject(), false);
                }
            });
        }
        {
            jMenuItemAddToList = new JMenuItem();
            jMenuItemAddToList.setText("Add To List");
            jMenuItemAddToList.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //ToDo добавить обработку добавления в список
                    //jMenuItemAddToList_actionPerformed(e);
                }
            });
        }
        jPopupMenu.add(jMenuItemAddSubType);
        jPopupMenu.add(jMenuItemEditType);
        jPopupMenu.add(jMenuItemAddToList);

        tree1.setComponentPopupMenu(jPopupMenu);

        jPopupMenuTable = new JPopupMenu();

        menuAdd = new JMenu();
        menuAdd.setText("Add");
        {
            jMenuItemAddColomn = new JMenuItem();
            jMenuItemAddColomn.setText("Add Column");
            jMenuItemAddColomn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    fileTune_ActionPerformed(ae);
                }

                private void fileTune_ActionPerformed(ActionEvent ae) {
                    VBdObjectEntity object;
                    try {
                        object = (VBdObjectEntity) ((DefaultMutableTreeNode) tree1.getLastSelectedPathComponent()).getUserObject();
                    } catch (NullPointerException e) {
                        object = null;
                    }

                    if (object == null) {
                        new MessageWin("Не выбран тип, в который нужно дабавить колонку.");
                    } else if (!object.getTypeObject().getCode().equals("TABLE")) {
                        new MessageWin("Для объекта типа " + object.getTypeObject().getName() + " не предусмотрено добавление столбца.\nДобавление возможно только для таблицы");
                    } else {
                        //Todo  тут идет лишний запрос к BDObjType, далее в ЭФ добавления эта инфа не передается, надо пооптимальнее сделать
                        VBdColomnsEntity colomns = new VBdColomnsEntity();
                        colomns.setJavaClass(VBdColomnsEntity.class.toString());
                        colomns.setParent(object);
                        /*BDObjType objType = new BDObjType();
                        objType = (BDObjType) objType.selectFromBase("CODE", "COLOMN");
                        colomns.setTypeObject(objType);*/
                        addOrEdit(colomns, true);
                    }
                }
            });
            menuAdd.add(jMenuItemAddColomn);

            JMenuItem jMenuItemAddIndex = new JMenuItem();

            jMenuItemAddIndex.setText("Add Index");
            jMenuItemAddIndex.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    fileTune_ActionPerformed(ae);
                }

                private void fileTune_ActionPerformed(ActionEvent ae) {
                }
            });
            menuAdd.add(jMenuItemAddIndex);


        }
        jPopupMenuTable.add(menuAdd);
        table1.setComponentPopupMenu(jPopupMenuTable);
    }

    private void addOrEdit(VBdObjectEntity object, boolean adding) {
        /*if (object != null) {
            object.setLoaded(true);
        }*/
        JAddDialog dialog = null;
        if (object.getTypeObject() != null && object.getTypeObject().getCode().equals(TypeObject.DATE.getName())) {
            new MessageWin("Это же какую новую дату хотите добавить?");
        } else {
            if (adding) {
                if (object instanceof VBdColomnsEntity) {
                    if (object == null ||
                            object.getParent() == null ||
                            object.getParent().getTypeObject() == null ||
                            !object.getParent().getTypeObject().getCode().equals(TypeObject.TABLE.getName()) ||
                            object.getParent().getCode().equals(TypeObject.TABLE.getName())
                            ) {
                        new MessageWin("Не выбран тип для добавления колонки или тип не является таблицей.");
                    } else {
                        dialog = new NewOrEditColumn(null, object.getParent());
                    }
                } else if (object.getTypeObject().getCode().equals(TypeObject.TABLE.getName())) {
                    dialog = new NewOrEditTable(null, object);
                } else if (object.getTypeObject().getCode().equals(TypeObject.NUMBER.getName())) {
                    dialog = new NewOrEditNumber(null);
                } else if (object.getTypeObject().getCode().equals(TypeObject.STRING.getName())) {
                    dialog = new NewOrEditString(null);
                } else if (object.getTypeObject().getCode().equals(TypeObject.REFERENCE.getName())
                        || object.getTypeObject().getCode().equals(TypeObject.ARRAY.getName())) {
                    dialog = new NewOrEditRefArr(null, object.getTypeObject().getCode().equals(TypeObject.REFERENCE.getName()));
                }
            } else {
                if (object.getTypeObject().getCode().equals(TypeObject.TABLE.getName())) {
                    dialog = new NewOrEditTable(object, object.getParent());
                } else if (object.getTypeObject().getCode().equals(TypeObject.NUMBER.getName())) {
                    dialog = new NewOrEditNumber(object);
                } else if (object.getTypeObject().getCode().equals(TypeObject.STRING.getName())) {
                    dialog = new NewOrEditString(object);
                } else if (object.getTypeObject().getCode().equals(TypeObject.REFERENCE.getName())
                        || object.getTypeObject().getCode().equals(TypeObject.ARRAY.getName())) {
                    dialog = new NewOrEditRefArr(object, object.getTypeObject().getCode().equals(TypeObject.REFERENCE.getName()));
                }
            }
            if (dialog != null) {
                dialog.pack();
                dialog.setVisible(true);
                if ((object instanceof VBdTableEntity)) {
                    //VBdObjectEntity bdParentForAllObj = dialog.getAddedObj();
                    tree1.addToTree(dialog.getAddedObj());
                    //tree1.loadTree();
                }
            }
        }
    }

    private void createUIComponents() {
        table1 = new JTable(new JDBTableModel());
        tree1 = JDBTree.getInstance();
        // TODO: place custom component creation code here
    }

}
