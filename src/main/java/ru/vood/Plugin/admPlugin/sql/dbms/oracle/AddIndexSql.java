package ru.vood.Plugin.admPlugin.sql.dbms.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.LimitingNameDBMS;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.core.runtime.exception.ApplicationErrorException;

import java.util.List;

import static ru.vood.Plugin.admPlugin.sql.sqlInterfaces.SQLInterface.INDEX_PREFIX;

@Service
public class AddIndexSql {

    @Autowired
    private PluginTunes pluginTunes;

    @Autowired
    private LimitingNameDBMS limitingNameDBMS;

    public String generateSys(String tableName, boolean isUnique, List<String> colomns) {
        return generateSys(tableName, isUnique, false, colomns);
    }

    public String generateSys(String tableName, List<String> colomns) {
        return generateSys(tableName, false, false, colomns);
    }

    public String generateUser(String tableName, boolean isUnique, List<String> colomns, String nameIdx) {
        return generateUser(tableName, isUnique, false, colomns, nameIdx);
    }

    public String generateUser(String tableName, List<String> colomns, String nameIdx) {
        return generateUser(tableName, false, false, colomns, nameIdx);
    }


    public String generateSys(String tableName, boolean isUnique, boolean isReverse, List<String> colomns) {
        return generateAll(tableName, isUnique, isReverse, pluginTunes.getTableSpaseSysIndex(), colomns, null);
    }

    /*public String generateSys(String tableName, boolean isUnique, boolean isReverse, String colomns) {
        return generateAll(tableName, isUnique, isReverse, pluginTunes.getTableSpaseSysIndex(), colomns);
    }*/

    public String generateUser(String tableName, boolean isUnique, boolean isReverse, List<String> colomns, String nameIdx) {
        return generateAll(pluginTunes.getPrefixTable() + tableName, isUnique, isReverse, pluginTunes.getTableSpaseSysIndex(), colomns, nameIdx);
    }

    private String generateAll(String tableName, boolean isUnique, boolean isReverse, String tableSpace, List<String> colomns, String nameIdx) {
        if (colomns == null || colomns.isEmpty()) {
            throw new ApplicationErrorException("Не определен список колонок для индекса.");
        }

        StringBuffer nameIndex = new StringBuffer(INDEX_PREFIX);
        nameIndex.append(tableName.toUpperCase());

        StringBuffer col = new StringBuffer(" (");

        StringBuffer res;
        if (!isUnique) {
            res = new StringBuffer("create index ");
        } else {
            res = new StringBuffer("create unique index ");
        }

        nameIndex.append(colomns.stream().reduce((s1, s2) -> s1 + "_" + s2).orElse(" "));
        col.append(colomns.stream().reduce((s1, s2) -> s1 + ", " + s2).orElse(" "));

        if (nameIdx == null || nameIdx.length() == 0) {
            res.append(limitingNameDBMS.getNameObj(nameIndex.toString()));
        } else res.append(nameIdx);
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
