package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.AddIndexSql;

@Component
public class AddIndexImpl implements StepsCreateServise {

    @Autowired
    @Qualifier("addArrayImpl")
    private StepsCreateServise nextStep;

    @Autowired
    private AddIndexSql indexSql;

    @Override
    public StepsCreateServise getNextStep() {
        return nextStep;
    }

    @Override
    public QueryTableNew createDDL(VBdObjectEntity bdObject) {
        if (!(bdObject instanceof VBdIndexEntity)) {
            return null;
        }

        QueryTableNew queryTable = new QueryTableNew();
        VBdIndexEntity bdIndex = (VBdIndexEntity) bdObject;
        if (bdIndex.getColomnsEntities() != null) {
            String s = bdIndex.getColomnsEntities().stream()
                    .map((c) -> c.getColomnRef().getCode())
                    .reduce((s1, s2) -> s1 + ", " + s2).orElse(" ");
            String sql = indexSql.generateUser(bdIndex.getParent().getCode(), bdIndex.getUniqueI().equals("1"), s);
            queryTable.add(sql);
        }

        return queryTable;

    }
}
