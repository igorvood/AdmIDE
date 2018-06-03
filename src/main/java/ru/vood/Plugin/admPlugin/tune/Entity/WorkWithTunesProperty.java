package ru.vood.Plugin.admPlugin.tune.Entity;

import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.core.runtime.type.Varchar2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Deprecated
public class WorkWithTunesProperty {
    //путь к нашему файлу конфигураций
    //public static final String PATH_TO_PROPERTIES = "resources/config.properties";
    public static final String PATH_TO_PROPERTIES = "config.properties";
    public static final String PREFIX_FOR_PROPERTIES = "ru.vood.admIde.config.";

    private static WorkWithTunesProperty workWithTune;

    public static WorkWithTunesProperty getInstance() {
        return new WorkWithTunesProperty();
    }

    public String getTuneVal(String name) {
        FileInputStream fileInputStream;
        //инициализируем специальный объект Properties
        //типа Hashtable для удобной работы с данными
        Properties prop = new Properties();
        String val = null;

        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

            val = prop.getProperty(PREFIX_FOR_PROPERTIES + name);
            prop.propertyNames();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return val;
    }


    private void saveTune(ListTunes listTunes) {
        WorkWithTunesProperty tunes = WorkWithTunesProperty.getInstance();
        AppConst.nameValuePair.set(new Varchar2(listTunes.getName()), new Varchar2(tunes.getTuneVal(listTunes.getName())));
    }

    public void saveTunesContext() {
        saveTune(ListTunes.PACKAGE);
        saveTune(ListTunes.PASSWORD);
        saveTune(ListTunes.USER);
        saveTune(ListTunes.HOST);
        saveTune(ListTunes.PORT);
        saveTune(ListTunes.SID);
        saveTune(ListTunes.TABLE_SPASE_SYS_TABLE);
        saveTune(ListTunes.TABLE_SPASE_SYS_INDEX);
        saveTune(ListTunes.TABLE_SPASE_USER_TABLE);
        saveTune(ListTunes.TABLE_SPASE_USER_INDEX);
        saveTune(ListTunes.ENCODDING);
        saveTune(ListTunes.PREFIX_TABLE);
        saveTune(ListTunes.DBMS_TYPE);
        saveTune(ListTunes.OWNER);
    }


}
