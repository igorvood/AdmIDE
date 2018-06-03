package ru.vood.Plugin.dialogs;

import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.entityHiber.VBdTableEntity;
import ru.vood.Plugin.admPlugin.entityHiber.intf.VBdObjectEntityService;
import ru.vood.Plugin.admPlugin.entityHiber.intf.VBdObjectTypeEntityService;
import ru.vood.Plugin.dialogs.ExtSwing.DBTreeCellRenderer;
import ru.vood.Plugin.dialogs.ExtSwing.JAddDialog;
import ru.vood.Plugin.dialogs.ExtSwing.JDBTree;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;

public class NewOrEditRefArr extends JAddDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTree tree1;

    private JTextField codeField;
    private JTextField nameField;
    private VBdObjectEntity object;
    private boolean isRef;

    public NewOrEditRefArr(VBdObjectEntity object, boolean isRef) {
        this.object = object;
        this.isRef = isRef;

        if (isRef) {
            if (this.object == null) {
                this.setTitle("Создание ссылки");
            } else {
                this.setTitle("Редактирование ссылки");
            }
        } else {
            if (this.object == null) {
                this.setTitle("Создание массива");
            } else {
                this.setTitle("Редактирование массива");
            }
        }

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
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                VBdObjectEntity bdTable = ((VBdObjectEntity) ((DefaultMutableTreeNode) tree1.getLastSelectedPathComponent()).getUserObject());
                if (bdTable.getTypeObject().getCode().equals("TABLE")) {
                    String postfix;
                    if (isRef) {
                        postfix = "_REF";
                        nameField.setText("Ссылна на <" + bdTable.getName() + ">");
                    } else {
                        postfix = "_ARR";
                        nameField.setText("Массив <" + bdTable.getName() + ">");
                    }
                    codeField.setText(bdTable.getCode() + postfix);
                }
            }
        });
    }

    private void onOK() {
        if (tree1.getLastSelectedPathComponent() == null) {
            new ErrWin(this, "Не выбран тип.", true, "Не выбран тип.", null);
        } else {
            VBdObjectEntity bdTable = ((VBdObjectEntity) ((DefaultMutableTreeNode) tree1.getLastSelectedPathComponent()).getUserObject());
            VBdObjectEntity parent = new VBdObjectEntity();

            if (bdTable.getTypeObject().getCode().equals("TABLE")) {
                VBdTableEntity table = new VBdTableEntity();
                table.setCode(codeField.getText());
                table.setName(nameField.getText());
                VBdObjectTypeEntity bdObjType = new VBdObjectTypeEntity();
                VBdObjectTypeEntityService vBdObjectTypeEntityService = bdObjType.getServise();
                VBdObjectEntityService vBdObjectEntityService = parent.getServise();
                if (isRef) {
                    bdObjType = vBdObjectTypeEntityService.findByCodeS("REFERENCE").iterator().next();
                    parent = vBdObjectEntityService.findByTypeObjectCodeIn("REFERENCE").iterator().next();
                } else {
                    bdObjType = vBdObjectTypeEntityService.findByCodeS("ARRAY").iterator().next();
                    parent = vBdObjectEntityService.findByTypeObjectCodeIn("ARRAY").iterator().next();
                }
                table.setTypeObject(bdObjType);
                table.setJavaClass(table.getClass().toString());
                table.setParent(parent);
                table.setToType((VBdTableEntity) bdTable);
                table.save();

                this.setAddedObj(table);
                dispose();
            } else {
                new MessageWin("Тип объекта " + bdTable.getTypeObject().getName() + " не предполагает создание ссылки или массива.");
            }
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        tree1 = JDBTree.getInstance();
        ((JDBTree) tree1).loadTree();
        tree1.setCellRenderer(new DBTreeCellRenderer());
        // TODO: place custom component creation code here
    }

    @Override
    protected void extension() {
        this.setSize(new Dimension(500, 500));
    }

}
