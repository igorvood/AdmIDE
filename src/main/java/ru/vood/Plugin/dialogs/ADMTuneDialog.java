package ru.vood.Plugin.dialogs;

import com.google.gson.Gson;
import jfork.nproperty.ConfigParser;
import ru.vood.Plugin.admPlugin.spring.gson.GsonTune;
import ru.vood.Plugin.admPlugin.tune.Configarations;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.db.SupportedDBMS;
import ru.vood.Plugin.dialogs.ExtSwing.JAddDialog;
import ru.vood.Plugin.logging.Log;
import ru.vood.core.runtime.exception.ApplicationErrorException;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.vood.Plugin.applicationConst.AppConst.CONNECTIONS;
import static ru.vood.Plugin.applicationConst.AppConst.DB_PROPERTUES;

public class ADMTuneDialog extends JAddDialog {
    private static Log log = Log.getLogger(ADMTuneDialog.class);
    private static PluginTunes defaultTunes = getDefaultTunes();
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField pakageField;
    private JTextField userField;
    private JTextField passwordField;
    private JTextField hostField;
    private JTextField portField;
    private JTextField sidField;
    private JTextField tableSpaceSysTableField;
    private JTextField codingField;
    private JTextField prefixSysTableField;
    private JTextField ownerField;
    private JTextField tableSpaceSysIndexField;
    private JTextField tableSpaceUserTableField;
    private JTextField tableSpaceUserIndexField;
    private JButton buttonAddCfg;
    private JButton buttonDeleteCfg;
    private JButton buttcomboBoxCfgNameonDeleteCfg;
    private JTextField textFieldNameCfg;
    private Configarations configurations;
    private JComboBox comboBoxCfgName;
    private JComboBox dataBaseBox;


    public ADMTuneDialog(Configarations configurations) {
        if (configurations == null) {
            configurations = new Configarations();
        }
        this.configurations = configurations;

        Set<PluginTunes> pluginTunes = configurations.stream()
                .peek(q -> comboBoxCfgName.addItem(q.getNameCurrentList()))
                .filter(pt -> pt.getDefaultConfiguration().booleanValue())
                .collect(Collectors.toSet());

        if (pluginTunes.size() > 1) {
            throw new ApplicationErrorException("В файле " + CONNECTIONS + "Найдено несколько конфигурайи по умолчанию");
        }

        if (pluginTunes.isEmpty()) {
            pluginTunes.add(defaultTunes);
        }

        PluginTunes tunes = pluginTunes.stream().filter(pt -> pt.getDefaultConfiguration().booleanValue()).findFirst().get();

        for (int i = 0; i <= SupportedDBMS.values().length - 1; i++) {
            this.dataBaseBox.addItem(SupportedDBMS.values()[i].getType());
        }

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.comboBoxCfgName.removeAll();

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

        buttonAddCfg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                addNewConfig();
            }
        });
        comboBoxCfgName.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    loadTune(comboBoxCfgName.getSelectedItem().toString());
                }
            }
        });

        loadTune(tunes);
        buttonDeleteCfg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCfg();
            }
        });
    }

    private static PluginTunes getDefaultTunes() {
        PluginTunes pluginTunes1 = new PluginTunes();
        pluginTunes1.setDbmsType(SupportedDBMS.ORACLE.getType());
        pluginTunes1.setDefaultConfiguration(true);
        pluginTunes1.setNameCurrentList("NoName");
        return pluginTunes1;
    }

    private void deleteCfg() {
        PluginTunes pt;
        for (Iterator<PluginTunes> it = configurations.iterator(); it.hasNext(); ) {
            PluginTunes p = it.next();
            p.setDefaultConfiguration(false);
            if (p.getNameCurrentList().equalsIgnoreCase(comboBoxCfgName.getSelectedItem().toString())) {
                comboBoxCfgName.removeItem(p.getNameCurrentList());
                configurations.remove(p);
            }
        }
        PluginTunes pluginTunes = null;
        try {
            pluginTunes = configurations.stream().findFirst().get();
        } catch (NoSuchElementException e) {
            configurations.add(defaultTunes);
        }
        pluginTunes.setDefaultConfiguration(true);
        loadTune(pluginTunes);
    }

    private void loadTune(String s) {
        PluginTunes pluginTunes1 = configurations.stream().
                filter(pt -> pt.getNameCurrentList().equalsIgnoreCase(s)).
                findFirst().get();
        loadTune(pluginTunes1);

    }

    private void addNewConfig() {
        configurations.stream().peek(pt -> pt.setDefaultConfiguration(false));
        PluginTunes pluginTunes = new PluginTunes();
        pluginTunes.setDefaultConfiguration(true);
        pluginTunes.setPackageIn(this.pakageField.getText());
        pluginTunes.setPassword(this.passwordField.getText());
        pluginTunes.setUser(this.userField.getText());
        pluginTunes.setHost(this.hostField.getText());
        pluginTunes.setPort(this.portField.getText());
        pluginTunes.setSid(this.sidField.getText());
        pluginTunes.setTableSpaseSysTable(this.tableSpaceSysTableField.getText());
        pluginTunes.setTableSpaseSysIndex(this.tableSpaceSysIndexField.getText());
        pluginTunes.setTableSpaseUserTable(this.tableSpaceUserTableField.getText());
        pluginTunes.setTableSpaseUserIndex(this.tableSpaceUserIndexField.getText());
        pluginTunes.setEncodding(this.codingField.getText());
        pluginTunes.setPrefixTable(this.prefixSysTableField.getText());
        pluginTunes.setOwner(this.ownerField.getText());
        pluginTunes.setDbmsType(this.dataBaseBox.getSelectedItem().toString());
        pluginTunes.setNameCurrentList(textFieldNameCfg.getText());

        PluginTunes pluginTunes1;
        try {

            pluginTunes1 = configurations.stream().peek(pt -> pt.setDefaultConfiguration(false))
                    .filter(pt -> pt.getNameCurrentList().equalsIgnoreCase(pluginTunes.getNameCurrentList()))
                    .findFirst().get();
            configurations.remove(pluginTunes1);
        } catch (NoSuchElementException e) {
        } finally {
            configurations.add(pluginTunes);
        }

        addItemToBoxCfgName(textFieldNameCfg.getText());

        loadTune(pluginTunes);
    }

    private void addItemToBoxCfgName(String item) {
        boolean isExists = false;
        for (int i = 0; i < comboBoxCfgName.getModel().getSize(); i++) {
            isExists = comboBoxCfgName.getItemAt(i).toString().equalsIgnoreCase(item);
            if (isExists) {
                break;
            }
        }
        if (!isExists) {
            comboBoxCfgName.addItem(item);
        }
    }

    private void loadTune(PluginTunes tunes) {
        this.pakageField.setText(tunes.getPackageIn());
        this.passwordField.setText(tunes.getPassword());
        this.userField.setText(tunes.getUser());
        this.hostField.setText(tunes.getHost());
        this.portField.setText(tunes.getPort());
        this.sidField.setText(tunes.getSid());
        this.tableSpaceSysTableField.setText(tunes.getTableSpaseSysTable());
        this.tableSpaceSysIndexField.setText(tunes.getTableSpaseSysIndex());
        this.tableSpaceUserTableField.setText(tunes.getTableSpaseUserTable());
        this.tableSpaceUserIndexField.setText(tunes.getTableSpaseUserIndex());
        this.codingField.setText(tunes.getEncodding());
        this.prefixSysTableField.setText(tunes.getPrefixTable());

        boolean isExists = false;
        for (int i = 0; i < dataBaseBox.getModel().getSize(); i++) {
            isExists = dataBaseBox.getItemAt(i).toString().equalsIgnoreCase(tunes.getDbmsType());
            if (isExists) {
                this.dataBaseBox.setSelectedItem(tunes.getDbmsType());
                break;
            }
        }
        if (!isExists) {
            this.dataBaseBox.setSelectedItem(SupportedDBMS.ORACLE.getType());
        }

        this.ownerField.setText(tunes.getOwner());
        this.textFieldNameCfg.setText(tunes.getNameCurrentList());
        if (!defaultTunes.equals(tunes)) {
            addItemToBoxCfgName(textFieldNameCfg.getText());
        }
        this.comboBoxCfgName.setSelectedItem(tunes.getNameCurrentList());
    }

    private void saveTune() {
        addNewConfig();
        Gson gson = GsonTune.getGson();
        Properties properties = System.getProperties();

        PluginTunes pluginTunes = configurations.stream().filter(pt -> pt.getDefaultConfiguration()).findFirst().get();

        File file = new File(properties.getProperty("user.dir") + "\\src\\main\\resources", DB_PROPERTUES);
        try {
            ConfigParser.store(pluginTunes, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = gson.toJson(configurations);
        try {
            Files.write(Paths.get(CONNECTIONS), s.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onOK() {

        saveTune();
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
