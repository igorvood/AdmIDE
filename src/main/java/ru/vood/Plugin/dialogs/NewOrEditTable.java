package ru.vood.Plugin.dialogs;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdTableEntityService;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.dialogs.ExtSwing.EnglishFilter;
import ru.vood.Plugin.dialogs.ExtSwing.JAddDialog;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.event.*;

public class NewOrEditTable extends JAddDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField codeField;
    private JTextField nameField;
    private VBdObjectEntity object;
    private VBdObjectEntity parent;

    public NewOrEditTable(VBdObjectEntity object, VBdObjectEntity parent) {
        this.object = object;
        this.parent = parent;

        if (this.object == null) {
            this.setTitle("Создание таблицы");
        } else {
            this.setTitle("Редактирование таблицы");
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
    }

    private void onOK() {
        if (checkText(codeField) && checkText(nameField)) {
            PluginTunes pluginTunes = LoadedCTX.getService(PluginTunes.class);

            VBdTableEntity newBDTable = new VBdTableEntity();
            newBDTable.setParent(this.parent);
            newBDTable.setTypeObject(this.parent.getTypeObject());
            newBDTable.setCode(this.codeField.getText().toUpperCase().trim());
            newBDTable.setName(this.nameField.getText().trim());
            newBDTable.setJavaClass(newBDTable.getClass().toString());

            newBDTable.setTableSpace(pluginTunes.getTableSpaseUserTable());
            newBDTable.setStorage(pluginTunes.getStorageTable());
            VBdTableEntityService tableEntityService = LoadedCTX.getService(VBdTableEntityService.class);
            tableEntityService.save(newBDTable);
            this.setAddedObj(newBDTable);
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /*public static void main(String[] args) {
        NewOrEditTable dialog = new NewOrEditTable();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }*/

    @Override
    protected void extension() {
        PlainDocument doc = (PlainDocument) codeField.getDocument();
        doc.setDocumentFilter(new EnglishFilter());
    }

}
