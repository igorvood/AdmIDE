package ru.vood.Plugin.sql.sqlFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.applicationConst.SupportedDBMS;
import ru.vood.Plugin.sql.dbms.oracle.SQLFistrLoadShemeOra;
import ru.vood.Plugin.sql.sqlInterfaces.SQLFistrLoadShemeInterface;

@Service
public class SQLFistrLoadShemeFactory implements SQLFistrLoadShemeInterface {

    @Autowired
    private PluginTunes pluginTunes;

    private static SQLFistrLoadShemeInterface sql;

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
