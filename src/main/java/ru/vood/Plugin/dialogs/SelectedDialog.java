package ru.vood.Plugin.dialogs;

import com.google.gson.Gson;
import ru.vood.Plugin.admPlugin.gson.GsonTune;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.dialogs.ExtSwing.JAddDialog;
import ru.vood.Plugin.dialogs.ExtSwing.JDBTree;
import ru.vood.Plugin.dialogs.ExtSwing.treeWhithCheckBox.CheckNode;
import ru.vood.Plugin.dialogs.ExtSwing.treeWhithCheckBox.CheckRenderer;
import ru.vood.Plugin.dialogs.ExtSwing.treeWhithCheckBox.NodeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SelectedDialog extends JAddDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTree allTree;
    private JButton saveButton;

    public SelectedDialog() {
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
        saveButton.addActionListener(new SaveActionListener());
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
        this.setSize(new Dimension(500, 500));

    }

    private void createUIComponents() {
        allTree = JDBTree.getInstance();
        ((JDBTree) allTree).loadTree(false);
        //allTree.setCellRenderer(new DBTreeCellRenderer());

        allTree.setCellRenderer(new CheckRenderer());
        allTree.addMouseListener(new NodeSelectionListener(allTree));

    }

    private class SaveActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) allTree.getModel().getRoot();
            System.out.println("======================selected---------------------------");
            List<VBdObjectEntity> entityList = new ArrayList<>();

            while (root != null) {
                CheckNode checkNode = (CheckNode) root.getUserObject();
                if (checkNode.isSelected()) {
                    entityList.add((VBdObjectEntity) checkNode.getUserObject());
                    System.out.println(this.getClass().toString() + " " + checkNode);
                }
                root = root.getNextNode();
            }
            Gson gson = GsonTune.getGson();

            System.out.println("======================selected---------------------------");
            String s = gson.toJson(entityList);


//            List<VBdObjectEntity>
            //         List<VBdObjectEntity> dsadsadasd= gson.fromJson(s,VBdObjectEntity.class );
            System.out.println(gson.toJson(entityList));
        }
    }
}
