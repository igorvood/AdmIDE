package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.spring.referenceBook.Tables;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;

@Component
public class AddForeignKeyForParentImpl implements StepsCreateServise {

    @Autowired
    @Qualifier("addArrayTypeImpl")
    private StepsCreateServise nextStep;


    @Autowired
    private PluginTunes pluginTunes;

    @Autowired
    private AddConstraintSql constraintSql;


    public QueryTableNew createDDL(VBdObjectEntity bdObject) {

        if (!(bdObject instanceof VBdTableEntity)) {
            return null;
        }
        QueryTableNew queryTable = new QueryTableNew();

        VBdTableEntity bdTable = (VBdTableEntity) bdObject;
        if (bdTable.getParent() != null && !bdTable.getParent().getCode().equals(Tables.getTABLE().getCode()) && bdTable.getTypeObject().equals(ObjectTypes.getTABLE())) {
            String pref = pluginTunes.getPrefixTable();
            String forKey = constraintSql.getSql(pref + bdTable.getCode(), "ID", pref + bdTable.getParent().getCode(), "ID");
            queryTable.add(forKey);
        }

        return queryTable;

    }

    public StepsCreateServise getNextStep() {
        return nextStep;
    }
}
