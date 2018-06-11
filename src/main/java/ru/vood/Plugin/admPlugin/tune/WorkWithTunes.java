package ru.vood.Plugin.admPlugin.tune;

import ru.vood.Plugin.admPlugin.tune.entity.TuneT;
import ru.vood.Plugin.admPlugin.tune.entity.Tunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.dialogs.ErrWin;
import ru.vood.Plugin.logging.Log;
import ru.vood.core.runtime.type.Varchar2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

@Deprecated
public class WorkWithTunes {
    //private JAXBContextFactory

    private static final TuneT NULL_TUNE = new TuneT();
    private static Log log = Log.getLogger(WorkWithTunes.class);
    private static WorkWithTunes workWithTune;
    private Marshaller marsh;
    private Unmarshaller unMarsh;
    private File f;
    private Tunes tunes;


    /**
     * загружает настройки из xml файла f
     *
     * @param f хмл файл с наименованиями и значениями настроек формат описан в {@link ru.vood.Plugin.admPlugin.tune.Entity.Tunes}
     */
    private WorkWithTunes(File f) {
        //org.eclipse.persistence.jaxb.JAXBContextFactory
        JAXBContext jbctx = null;
        try {
            jbctx = JAXBContext.newInstance("ru.vood.Plugin.admPlugin.tune.entity");
        } catch (JAXBException e) {
            new ErrWin(null, "Не удалось создать jbctx", true, "Не удалось создать jbctx", e);
        }
        if (jbctx != null) {
            try {
                marsh = jbctx.createMarshaller();
            } catch (JAXBException e) {
                new ErrWin(null, "Не удалось создать marsh", true, "Не удалось создать marsh", e);
                //log.putToLog("Не удалось создать marsh ",e);
            }

            try {
                unMarsh = jbctx.createUnmarshaller();
            } catch (JAXBException e) {
                new ErrWin(null, "Не удалось создать marsh", true, "Не удалось создать marsh", e);
            }
            this.f = f;
            tunes = getTunes();
        }
/*        if (jbctx == null || unMarsh == null || marsh == null){


        }*/
    }

    public static WorkWithTunes getInstance(File f) {
        if (WorkWithTunes.workWithTune == null || !f.equals(workWithTune.f)) {
            WorkWithTunes.workWithTune = new WorkWithTunes(f);
        }

        return WorkWithTunes.workWithTune;
    }

    public static WorkWithTunes getInstance() {
        return WorkWithTunes.workWithTune;
    }


    private Tunes getTunes() {
        Tunes t;
        try {
            t = (Tunes) unMarsh.unmarshal(this.f);
        } catch (JAXBException e) {
            t = null;
        } catch (NullPointerException e) {
            t = null;
        }
        return t;
    }

    private void saveTunes() {
        boolean create;

        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                log.putToLog("Не удалось создать файл с настройками ", e);
            }
        }
        log.putToLog("Файл с настройками " + f.getPath() + "/" + f.getName(), Level.FINER);
        try {
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marsh.marshal(tunes, f);
        } catch (JAXBException e) {
            log.putToLog("Не удалось сохранить файл с настройками ", e);
        }
    }

    /**
     * возвращает настройку с именем name
     *
     * @param name наименование настройки
     * @return значение настройки
     */
    public TuneT getTune(String name) {
        try {
            for (TuneT t : tunes.getTune()) {
                if (t.getNameTune().equalsIgnoreCase(name)) {
                    return t;
                }
            }
        } catch (Exception e) {

            return NULL_TUNE;
        }
        return NULL_TUNE;
    }

    /**
     * Возвращает значение настройки с именем  name
     *
     * @param name наименование настройки
     * @return значение настройки
     */
    public String getTuneValue(String name) {
        TuneT t = getTune(name);
        if (t != null) {
            return getTune(name).getValue();
        }
        return null;
    }

    /**
     * Устанавливает новое значение value настройки с именем name
     *
     * @param name  наименование гастройки
     * @param value значение настройки
     */
    public void setTuneValue(String name, String value) {
        boolean fl = true;
        if (tunes == null) {
            tunes = new Tunes();
        }
        for (TuneT t : tunes.getTune()) {
            if (t.getNameTune().equalsIgnoreCase(name)) {
                t.setValue(value);
                fl = false;
            }
        }
        if (fl) {
            TuneT nTune = new TuneT();
            nTune.setNameTune(name);
            nTune.setValue(value);
            tunes.getTune().add(nTune);
        }
        saveTunes();
    }

    private void saveTune(ListTunes listTunes) {
        WorkWithTunes tunes = WorkWithTunes.getInstance(f);
        AppConst.nameValuePair.set(new Varchar2(listTunes.getName()), new Varchar2(tunes.getTuneValue(listTunes.getName())));
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
