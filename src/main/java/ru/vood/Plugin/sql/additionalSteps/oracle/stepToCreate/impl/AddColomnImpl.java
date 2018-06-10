package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.QueryTableNew;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;
import ru.vood.Plugin.sql.dbms.oracle.AddConstraintSql;

@Component
public class AddColomnImpl implements StepsCreateServise {

    @Autowired
    @Qualifier("addIndexImpl")
    private StepsCreateServise nextStep;

    @Autowired
    private PluginTunes tunes;

    @Autowired
    private AddConstraintSql constraintSql;

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

        if (bdColomns.getTypeValue().equals(ObjectTypes.getSTRING())) {
            stringBuffer.append(" VARCHAR2(" + vBdTableEntity.getLength() + ") " + ((bdColomns.getNotNull().equals("1")) ? "not null" : ""));
        } else if (bdColomns.getTypeValue().equals(ObjectTypes.getNUMBER())) {
            Long len = vBdTableEntity.getLength();
            Long pres = vBdTableEntity.getPrecision();
            String paramNum = "";
            if (len > 0 && pres > 0) {
                paramNum = "(" + len + "," + pres + ")";
            } else if (len > 0) {
                paramNum = "(" + len + ")";
            }
            stringBuffer.append(" NUMBER" + paramNum + " " + ((bdColomns.getNotNull().equals("1")) ? "not null" : ""));
        } else if (bdColomns.getTypeValue().equals(ObjectTypes.getDATE())) {
            stringBuffer.append(" DATE " + ((bdColomns.getNotNull().equals("1")) ? "not null" : ""));
        } else if (bdColomns.getTypeValue().equals(ObjectTypes.getREFERENCE())) {
            stringBuffer.append(" NUMBER " + ((bdColomns.getNotNull().equals("1")) ? "not null" : ""));
            queryTable.add(stringBuffer.toString());
            String pref = tunes.getPrefixTable();
            stringBuffer = new StringBuffer(constraintSql.getSql(pref + (bdColomns).getParent().getCode(), (bdColomns).getCode(), pref + vBdTableEntity.getToType().getCode(), "ID"));
        } else if (bdColomns.getTypeValue().equals(ObjectTypes.getARRAY())) {
            stringBuffer.append(" NUMBER not null");

            /*Это перенести в создание типа массив
            String tmp = AddIndexSql.generateSys(AppConst.getTune(ListTunes.PREFIX_TABLE)+ bdColomns.getParent().getCode(),AppConst.getTune(ListTunes.PREFIX_COLOMN)+bdColomns.getCode());
            queryTable.set(queryTable.count().add(1), new Varchar2(tmp));

            */
        }
        queryTable.add(stringBuffer.toString());
        return queryTable;

    }

    @Override
    public StepsCreateServise getNextStep() {
        return nextStep;
    }
}
