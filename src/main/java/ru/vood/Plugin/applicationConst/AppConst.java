package ru.vood.Plugin.applicationConst;

import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.runtimeAdm.NameValuePair;
import ru.vood.core.runtime.type.Varchar2;

import java.util.logging.Level;

@Deprecated
public class AppConst {
    public static final String CONNECTIONS = "Connections.gson";
    public static final String DB_PROPERTUES = "DBProperty.properties";

    public static final String LOG_FILE_DIR = "Log";
    public static final Level DEFAULT_LOG_LEVEL = Level.INFO;
    public static final String DEFUALT_LOG_FILE_NAME = "log.log";
    public static final ModeLogging defaultModeLogging = ModeLogging.SINGLY_FOR_EACH_CLASS;
    public static NameValuePair nameValuePair;

    static {
        nameValuePair = new NameValuePair();
        String s = //" tablespace T_USR\n" +
                "  pctfree 5\n" +
                        "  initrans 1\n" +
                        "  maxtrans 255\n" +
                        "  storage\n" +
                        "  (\n" +
                        "    initial 256K\n" +
                        "    next 1M\n" +
                        "    minextents 1\n" +
                        "    maxextents unlimited\n" +
                        "  )";
        AppConst.nameValuePair.set(new Varchar2(ListTunes.STORAGE_TABLE.getName()), new Varchar2(s));
        s = //" tablespace I_USR\n" +
                "  pctfree 20\n" +
                        "  initrans 2\n" +
                        "  maxtrans 255\n" +
                        "  storage\n" +
                        "  (\n" +
                        "    initial 32K\n" +
                        "    next 1M\n" +
                        "    minextents 1\n" +
                        "    maxextents unlimited\n" +
                        "  )\n";
        AppConst.nameValuePair.set(new Varchar2(ListTunes.STORAGE_INDEX.getName()), new Varchar2(s));

    }

    public static String getTune(Varchar2 nameTune) {
        try {
            return nameValuePair.get(nameTune).getValue();
        } catch (ru.vood.core.runtime.exception.NoDataFoundException e) {
            return null;
        }
    }

    public static String getTune(ListTunes nameTune) {

        String s = getTune(new Varchar2(nameTune.getName()));

        return (s == null) ? "" : s;
    }

    public static String getTune(String nameTune) {
        return getTune(new Varchar2(nameTune));
    }

    public enum ModeLogging {
        SINGLY_FOR_EACH_CLASS("SINGLY_FOR_EACH_CLASS"), COLLECTIVEL_FOR_ALL_CLASS("COLLECTIVEL_FOR_ALL_CLASS")/*, DUBBLE_LOGGING("DUBBLE_LOGGING")*/;

        private String type;

        private ModeLogging(String name) {
            this.type = name;
        }

        public String getType() {
            return type;
        }
    }
}
