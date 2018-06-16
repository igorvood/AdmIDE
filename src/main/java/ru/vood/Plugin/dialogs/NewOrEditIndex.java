package ru.vood.Plugin.dialogs;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectEntityService;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.dialogs.ExtSwing.JAddDialog;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class NewOrEditIndex extends JAddDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField indexField;
    private JTable ExcludeTable;
    private JTable includeTable;

    private VBdObjectEntity parentObject;
    private VBdIndexEntity indexEntity;

    public NewOrEditIndex(VBdIndexEntity indexEntity, VBdObjectEntity parent) {

        this.parentObject = parent;
        this.indexEntity = indexEntity;
        if (this.indexEntity == null) {
            this.setTitle("Создание индекса");
        } else {
            this.setTitle("Редактирование индекса");
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
        // add your code here
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
        //VBdTableEntityRepository bdTableEntityRepository= LoadedCTX.getService(VBdTableEntityRepository.class);
        //bdTableEntityRepository.
        VBdObjectEntityService service = LoadedCTX.getService(VBdObjectEntityService.class);
        List<VBdObjectEntity> vBdObjectEntityList = service.findByParentAndTypeObject(parentObject, ObjectTypes.getCOLOMN());
        System.out.println(vBdObjectEntityList);

        if (indexEntity != null) {
        }

        //this.setSize(new Dimension(1000, 500));
    }
}
