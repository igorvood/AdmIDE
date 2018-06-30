package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToEdit.abstr.StepsEditServise;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;

@Component
public class EditColomnImpl implements StepsEditServise {

//    @Autowired
//    @Qualifier("addIndexImpl")
//    private StepsCreateServise nextStep;

    @Autowired
    private PluginTunes tunes;

//    @Autowired
//    private AddConstraintSql constraintSql;
//
//    @Autowired
//    private TuneChainStepsCreate stepsCreate;


    @Override
    public QueryTableNew editDDL(VBdObjectEntity bdObjectOld, VBdObjectEntity bdObjectNew) {
        QueryTableNew queryTable = new QueryTableNew();
        if (!(bdObjectOld instanceof VBdColomnsEntity) || !(bdObjectNew instanceof VBdColomnsEntity)) {
            return queryTable;
        }

        VBdColomnsEntity bdColomnOld = (VBdColomnsEntity) bdObjectOld;
        VBdColomnsEntity bdColomnNew = (VBdColomnsEntity) bdObjectNew;


        if (!bdColomnOld.getCode().equals(bdObjectNew.getCode()) || !bdColomnOld.getNotNull() == bdColomnNew.getNotNull()) {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBegin = new StringBuffer();
            stringBegin.append(" ALTER TABLE " + tunes.getOwner() + "." + tunes.getPrefixTable() + bdColomnNew.getParent().getCode());
            //stringBegin.append(" MODIFY ( " + bdColomnOld.getCode() + " ");
            stringBuffer.append(stringBegin);
            // если переименование идет
            if (!bdColomnOld.getCode().equals(bdObjectNew.getCode())) {
                stringBuffer.append(" RENAME COLUMN " + bdColomnOld.getCode() + " to " + bdObjectNew.getCode());
                queryTable.add(stringBuffer.toString());
                stringBuffer = new StringBuffer(stringBegin);
            }

            // если ставится или убирается not null
            if (!bdColomnOld.getNotNull() == bdColomnNew.getNotNull()) {
                VBdTableEntity vBdTableEntity = (VBdTableEntity) bdColomnNew.getTypeValue();
                stringBuffer.append(" MODIFY ( " + bdColomnNew.getCode() + " ");
                if (bdColomnNew.getTypeValue().getTypeObject().equals(ObjectTypes.getSTRING())) {
                    String length = vBdTableEntity.getLength() == null ? " " : "(" + vBdTableEntity.getLength() + ")";
                    stringBuffer.append(" VARCHAR2 " + length + " " + ((bdColomnNew.getNotNull()) ? " not null" : " ") + " )");
                    queryTable.add(stringBuffer.toString());
                } else if (bdColomnNew.getTypeValue().getTypeObject().equals(ObjectTypes.getNUMBER())) {
                    Long len = vBdTableEntity.getLength();
                    Long pres = vBdTableEntity.getPrecision();
                    String paramNum = "";
                    if (len > 0 && pres > 0) {
                        paramNum = "(" + len + "," + pres + ")";
                    } else if (len > 0) {
                        paramNum = "(" + len + ")";
                    }
                    stringBuffer.append(" NUMBER" + paramNum + " " + ((bdColomnNew.getNotNull()) ? "not null" : "" + ")"));
                    queryTable.add(stringBuffer.toString());
                } else if (bdColomnNew.getTypeValue().getTypeObject().equals(ObjectTypes.getDATE())) {
                    stringBuffer.append(" DATE " + ((bdColomnNew.getNotNull()) ? "not null" : "") + ")");
                    queryTable.add(stringBuffer.toString());
                } else if (bdColomnNew.getTypeValue().getTypeObject().equals(ObjectTypes.getREFERENCE())) {
                    stringBuffer.append(" NUMBER " + ((bdColomnNew.getNotNull()) ? "not null" : "") + ")");
                    queryTable.add(stringBuffer.toString());
                } else if (bdColomnNew.getTypeValue().getTypeObject().equals(ObjectTypes.getARRAY())) {
                    // МАссив всегда not null
                }
            }
        }
        return queryTable;
    }
}
