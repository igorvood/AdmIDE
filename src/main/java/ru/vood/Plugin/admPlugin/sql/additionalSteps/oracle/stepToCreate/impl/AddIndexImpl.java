package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;

@Component
public class AddIndexImpl implements StepsCreateServise {

    @Autowired
    @Qualifier("addArrayTypeImpl")
    private StepsCreateServise nextStep;

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
       /* StringBuffer stringBuffer = new StringBuffer();


        TableDBToJavaClass tableDBToJavaClass = TableDBToJavaClass.gettToJc(bdIndex);

        stringBuffer.append("alter table " + tableDBToJavaClass.getDbTable() + "\n");
        stringBuffer.append("add constraint PK#" + tableDBToJavaClass.getDbTable() + "_ID primary key (" + tableDBToJavaClass.getIdColomn().getDbField() + ")\n");
        stringBuffer.append(" using index tablespace \n" + AppConst.getTune(ListTunes.TABLE_SPASE_USER_INDEX) + AppConst.getTune(ListTunes.STORAGE_INDEX));
        queryTable.set(queryTable.count().add(1), new Varchar2(stringBuffer.toString()));*/

        return queryTable;

    }
}
