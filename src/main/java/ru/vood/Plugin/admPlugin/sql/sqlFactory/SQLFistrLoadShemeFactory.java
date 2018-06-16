package ru.vood.Plugin.admPlugin.sql.sqlFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.SQLFistrLoadShemeOra;
import ru.vood.Plugin.admPlugin.sql.sqlInterfaces.SQLFistrLoadShemeInterface;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.applicationConst.SupportedDBMS;

@Service
public class SQLFistrLoadShemeFactory implements SQLFistrLoadShemeInterface {

    private static SQLFistrLoadShemeInterface sql;
    @Autowired
    private PluginTunes pluginTunes;

    public SQLFistrLoadShemeInterface getInstance() {
        if (sql == null) {
            if (pluginTunes.getDbmsType().equals(SupportedDBMS.ORACLE.getType())) {
                sql = new SQLFistrLoadShemeOra();
            } else sql = null;
        }
        return sql;
    }

    @Override
    public void getSQL() {
        sql.getSQL();
    }

}
