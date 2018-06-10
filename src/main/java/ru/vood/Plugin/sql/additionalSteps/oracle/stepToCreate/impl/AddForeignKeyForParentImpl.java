package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.QueryTableNew;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;
import ru.vood.Plugin.sql.dbms.oracle.AddConstraintSql;

@Component
public class AddForeignKeyForParentImpl implements StepsCreateServise {

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
        if (bdTable.getParent() != null && !bdTable.getParent().getCode().equals("TABLE") && bdTable.getTypeObject().getCode().equals(ObjectTypes.getTABLE())) {
            String pref = pluginTunes.getPrefixTable();
            String forKey = constraintSql.getSql(pref + bdTable.getCode(), "ID", pref + bdTable.getParent().getCode(), "ID");
            queryTable.add(forKey);
        }

        return queryTable;

    }

    public StepsCreateServise getNextStep() {
        return null;
    }
}