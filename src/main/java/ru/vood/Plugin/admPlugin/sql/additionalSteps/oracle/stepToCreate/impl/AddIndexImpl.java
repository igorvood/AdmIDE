package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateAndDropServise;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.AddIndexSql;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddIndexImpl implements StepsCreateAndDropServise {

    @Autowired
    @Qualifier("addArrayImpl")
    private StepsCreateAndDropServise nextStep;

    @Autowired
    private AddIndexSql indexSql;

    @Override
    public StepsCreateAndDropServise getNextStep() {
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
            List s = bdIndex.getColomnsEntities().stream()
                    .map((c) -> c.getColomnRef().getCode())
                    .collect(Collectors.toList());
            String sql = indexSql.generateUser(bdIndex.getParent().getCode(), bdIndex.getUniqueI().equals("1"), s, null);
            queryTable.add(sql);
        }

        return queryTable;

    }
}
