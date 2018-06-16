package ru.vood.Plugin.admPlugin.sql.dbms.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.LimitingNameDBMS;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.core.runtime.exception.ApplicationErrorException;

@Service
public class AddIndexSql {

    @Autowired
    private PluginTunes pluginTunes;

    @Autowired
    private LimitingNameDBMS limitingNameDBMS;

    public String generateSys(String tableName, boolean isUnique, String... colomns) {
        return generateSys(tableName, isUnique, false, colomns);
    }

    public String generateSys(String tableName, String... colomns) {
        return generateSys(tableName, false, false, colomns);
    }

    public String generateUser(String tableName, boolean isUnique, String... colomns) {
        return generateUser(tableName, isUnique, false, colomns);
    }

    public String generateUser(String tableName, String... colomns) {
        return generateUser(tableName, false, false, colomns);
    }


    public String generateSys(String tableName, boolean isUnique, boolean isReverse, String... colomns) {
        return generate(tableName, isUnique, isReverse, pluginTunes.getTableSpaseSysIndex(), colomns);
    }

    public String generateSys(String tableName, boolean isUnique, boolean isReverse, String colomns) {
        return generate(tableName, isUnique, isReverse, pluginTunes.getTableSpaseSysIndex(), colomns);
    }

    public String generateUser(String tableName, boolean isUnique, boolean isReverse, String... colomns) {
        return generate(tableName, isUnique, isReverse, pluginTunes.getTableSpaseSysIndex(), colomns);
    }

    private String generate(String tableName, boolean isUnique, boolean isReverse, String tableSpace, String... colomns) {
        if (colomns == null) {
            throw new ApplicationErrorException("Не определен список колонок для индекса.");
        }

        StringBuffer nameIndex = new StringBuffer("IDX_Z#");
        nameIndex.append(tableName.toUpperCase());

        StringBuffer col = new StringBuffer(" (");

        StringBuffer res;
        if (!isUnique) {
            res = new StringBuffer("create index ");
        } else {
            res = new StringBuffer("create unique index ");
        }

        for (int i = 0; i < colomns.length; i++) {
            nameIndex.append("_");
            nameIndex.append(colomns[i]);
            if (i == 0) {
                col.append(colomns[i]);
            } else {
                col.append(", ");
                col.append(colomns[i]);
            }
        }

        res.append(limitingNameDBMS.getNameObj(nameIndex.toString()));
        res.append(" on ");
        res.append(tableName);
        res.append(col);
        res.append(" ) ");
        //res.append(AppConst.getTune(ListTunes.TABLE_SPASE_SYS_TABLE));
        res.append(" tablespace ");
        res.append(tableSpace);
        res.append(" ");
        res.append(pluginTunes.getStorageIndex());

        if (isReverse) {
            res.append(" REVERSE");
        }

        return res.toString();
    }

}
