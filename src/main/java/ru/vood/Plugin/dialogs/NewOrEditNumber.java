package ru.vood.Plugin.dialogs;

import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectEntityService;
import ru.vood.Plugin.dialogs.ExtSwing.JAddDialog;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class NewOrEditNumber extends JAddDialog {
    private JPanel contentPane;

    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField codeField;
    private JTextField nameField;
    private JTextField lengthField;
    private JTextField precisionField;
    private VBdObjectEntity object;

    public NewOrEditNumber(VBdObjectEntity object) {
        this.object = object;
        if (this.object == null) {
            this.setTitle("Создание числа");
        } else {
            this.setTitle("Редактирование числа");
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
        VBdObjectEntity num = new VBdObjectEntity();

        VBdObjectEntityService objectEntityServ = num.getServise();
        List<VBdObjectEntity> vBdObjectEntityList = (List<VBdObjectEntity>) objectEntityServ.findByTypeObjectCodeIn("NUMBER");
        num = vBdObjectEntityList.get(0);

        VBdTableEntity newBDTable = new VBdTableEntity();
        newBDTable.setParent(num);
        newBDTable.setTypeObject(num.getTypeObject());
        newBDTable.setCode(this.codeField.getText().toUpperCase().trim());
        newBDTable.setName(this.nameField.getText().trim());
        newBDTable.setJavaClass(newBDTable.getClass().toString());
        newBDTable.setLength(Long.getLong(lengthField.getText()));
        newBDTable.setPrecision(Long.getLong(precisionField.getText()));
        newBDTable = newBDTable.save();

        this.setAddedObj(newBDTable);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    /**
     * Дополнительные действия, запускается при вызове setVisible
     */
    @Override
    protected void extension() {

    }
}
