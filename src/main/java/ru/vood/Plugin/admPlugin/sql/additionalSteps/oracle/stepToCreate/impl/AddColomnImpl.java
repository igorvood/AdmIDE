package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateAndDropServise;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.TuneChainStepsCreateServise;
import ru.vood.Plugin.admPlugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;

@Component
public class AddColomnImpl implements StepsCreateAndDropServise {

    @Autowired
    @Qualifier("addIndexImpl")
    private StepsCreateAndDropServise nextStep;

    @Autowired
    private PluginTunes tunes;

    @Autowired
    private AddConstraintSql constraintSql;

    @Autowired
    @Qualifier("tuneChainStepsCreate")
    private TuneChainStepsCreateServise stepsCreate;


    @Override
    public QueryTableNew createDDL(VBdObjectEntity bdObject) {
        QueryTableNew queryTable = new QueryTableNew();
        if (!(bdObject instanceof VBdColomnsEntity)) {
            return queryTable;
        }

        VBdColomnsEntity bdColomns = (VBdColomnsEntity) bdObject;
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("ALTER TABLE " + tunes.getPrefixTable() + bdColomns.getParent().getCode() + "\n");
        stringBuffer.append("ADD " + bdColomns.getCode());

        VBdTableEntity vBdTableEntity = (VBdTableEntity) bdColomns.getTypeValue();

        if (bdColomns.getTypeValue().getTypeObject().equals(ObjectTypes.getSTRING())) {
            String length = vBdTableEntity.getLength() == null ? " " : "(" + vBdTableEntity.getLength() + ")";
            stringBuffer.append(" VARCHAR2" + length + " " + ((bdColomns.getNotNull()) ? " not null" : " "));
        } else if (bdColomns.getTypeValue().getTypeObject().equals(ObjectTypes.getNUMBER())) {
            Long len = vBdTableEntity.getLength();
            Long pres = vBdTableEntity.getPrecision();
            String paramNum = "";
            if (len == null && pres == null) {
                stringBuffer.append(" NUMBER" + " " + ((bdColomns.getNotNull()) ? "not null" : ""));
            } else {
                if (len > 0 && pres > 0) {
                    paramNum = "(" + len + "," + pres + ")";
                } else if (len > 0) {
                    paramNum = "(" + len + ")";
                }
                stringBuffer.append(" NUMBER" + paramNum + " " + ((bdColomns.getNotNull()) ? "not null" : ""));
            }
        } else if (bdColomns.getTypeValue().getTypeObject().equals(ObjectTypes.getDATE())) {
            stringBuffer.append(" DATE " + ((bdColomns.getNotNull()) ? "not null" : ""));
        } else if (bdColomns.getTypeValue().getTypeObject().equals(ObjectTypes.getREFERENCE())) {
            stringBuffer.append(" NUMBER " + ((bdColomns.getNotNull()) ? "not null" : ""));
            queryTable.add(stringBuffer.toString());
            String pref = tunes.getPrefixTable();
            stringBuffer = new StringBuffer(constraintSql.getSql(pref + (bdColomns).getParent().getCode(), (bdColomns).getCode(), pref + vBdTableEntity.getToType().getCode(), "ID"));
        } else if (bdColomns.getTypeValue().getTypeObject().equals(ObjectTypes.getARRAY())) {
            //Если работа идет с массивом то сначала добавить колонку, потом заполнить ее значениями, и поптом ее сделать не пустой.
            stringBuffer.append(" NUMBER ");
            queryTable.add(stringBuffer.toString());
            stepsCreate.runQueryes(queryTable);
            stringBuffer = new StringBuffer();
            stringBuffer.append(" UPDATE  " + tunes.getOwner() + "." + tunes.getPrefixTable() + bdColomns.getParent().getCode() + "\n");
            stringBuffer.append(" SET " + bdColomns.getCode() + " = SEQ_ID.nextval  ");

            queryTable = new QueryTableNew();
            queryTable.add(stringBuffer.toString());
            queryTable.add("commit ");

            stringBuffer = new StringBuffer();
//            stringBuffer.append(" ALTER TABLE " + tunes.getOwner() + "." + tunes.getPrefixTable() + bdColomns.getParent().getCode());
//            stringBuffer.append(" MODIFY  " + bdColomns.getCode() + " NUMBER not null ");
//            queryTable.add(stringBuffer.toString());

            stepsCreate.runQueryes(queryTable);
        }
        if (!bdColomns.getTypeValue().getTypeObject().equals(ObjectTypes.getARRAY())) {
            queryTable.add(stringBuffer.toString());
        }
        return queryTable;

    }

    @Override
    public StepsCreateAndDropServise getNextStep() {
        return nextStep;
    }
}
