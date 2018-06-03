package ru.vood.Plugin.dialogs;

import ru.vood.Plugin.dialogs.ExtSwing.JAddDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageWin extends JAddDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea textArea1;

    public MessageWin(String textMessage) {
        setContentPane(contentPane);
        setModal(true);
        textArea1.setText(textMessage);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        this.setSize(new Dimension(500, 300));
        this.pack();
        this.setVisible(true);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    /**
     * Дополнительные действия, запускается при вызове setVisible
     */
    @Override
    protected void extension() {

    }
}
