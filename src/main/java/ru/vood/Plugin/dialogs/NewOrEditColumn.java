package ru.vood.Plugin.dialogs;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdColomnsEntityService;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.dialogs.ExtSwing.DBTreeCellRenderer;
import ru.vood.Plugin.dialogs.ExtSwing.EnglishFilter;
import ru.vood.Plugin.dialogs.ExtSwing.JAddDialog;
import ru.vood.Plugin.dialogs.ExtSwing.JDBTree;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.PlainDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;

public class NewOrEditColumn extends JAddDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTree tree1;
    private JTextField codeField;
    private JTextField nameField;
    private JTextField textField1;
    private JCheckBox notNullCheckBox;
    private JLabel isEmptyLable;
    private VBdObjectEntity parentObject;

    public NewOrEditColumn(VBdColomnsEntity object, VBdObjectEntity parent) {
        this.parentObject = parent;
        if (object == null) {
            this.setTitle("Создание столбца таблицы");
        } else {
            this.setTitle("Редактирование столбца таблицы");
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
                if (!bdTable.getTypeObject().getCode().equals("TABLE")) {
                    textField1.setText(bdTable.getName());
                } else {
                    new MessageWin("Тип " + bdTable.getTypeObject().getCode() + " не может бить типом столбца.");
                }
            }
        });
    }

    private void onOK() {

        // add your code here
        if (checkText(codeField) && checkText(nameField) && tree1.getLastSelectedPathComponent() != null) {
            VBdColomnsEntity colomns = new VBdColomnsEntity();
            colomns.setJavaClass(VBdColomnsEntity.class.toString());
            colomns.setParent(this.parentObject);
            colomns.setCode(codeField.getText().toUpperCase());
            colomns.setName(nameField.getText());
            colomns.setNotNull(((notNullCheckBox.isSelected()) ? "1" : "0"));

            colomns.setTypeObject(ObjectTypes.getCOLOMN());
            colomns.setTypeValue((VBdTableEntity) ((DefaultMutableTreeNode) tree1.getLastSelectedPathComponent()).getUserObject());
            VBdColomnsEntityService colomnsEntityService = LoadedCTX.getService(VBdColomnsEntityService.class);
            VBdColomnsEntity newColomn = (VBdColomnsEntity) colomnsEntityService.save(colomns);
            this.setAddedObj(newColomn);

            dispose();
        } else {
            isEmptyLable.setVisible(true);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    @Override
    protected void extension() {
        PlainDocument doc = (PlainDocument) codeField.getDocument();
        doc.setDocumentFilter(new EnglishFilter());

        this.setSize(new Dimension(500, 500));

    }

    private void createUIComponents() {
        tree1 = JDBTree.getInstance();
        ((JDBTree) tree1).loadTree(true);
        tree1.setCellRenderer(new DBTreeCellRenderer());
    }


}

