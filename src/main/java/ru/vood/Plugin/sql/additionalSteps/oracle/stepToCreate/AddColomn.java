package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate;

import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.applicationConst.TypeObject;
import ru.vood.Plugin.db.QueryTable;
import ru.vood.Plugin.sql.dbms.oracle.AddConstraintSql;
import ru.vood.core.runtime.type.Varchar2;

//import ru.vood.Plugin.entity.bdEntity.BDColomns;

public class AddColomn extends StepsCreate {

    /**
     * @param bdObject - объкет по которому генерируется запрос
     */
    public AddColomn(Object bdObject) {
        super(bdObject);
    }

    /**
     * @param bdObject         - объкет по которому генерируется запрос
     * @param bdObjectIndirect - объкект необходимый для создания запроса, например для созданий внешнего ключа нужна вторая таблица к которой устанавливается связь
     */
    public AddColomn(Object bdObject, Object bdObjectIndirect) {
        super(bdObject, bdObjectIndirect);
    }

    @Override
    QueryTable additionOne(QueryTable queryTable) {
        if (!(bdObject instanceof VBdColomnsEntity)) {
            return queryTable;
        }

        VBdColomnsEntity bdColomns = (VBdColomnsEntity) bdObject;
        StringBuffer stringBuffer = new StringBuffer();


        // TableDBToJavaClass tableDBToJavaClass = TableDBToJavaClass.gettToJc(bdColomns);

        //stringBuffer.append("ALTER TABLE " + AppConst.getTune(ListTunes.PREFIX_TABLE) + tableDBToJavaClass.getDbTable() + "\n");
        stringBuffer.append("ALTER TABLE " + AppConst.getTune(ListTunes.PREFIX_TABLE) + bdColomns.getParent().getCode() + "\n");
        TuneChainStepsCreate.runChain(this, null);

        stringBuffer.append("ADD " + bdColomns.getCode());

        VBdTableEntity vBdTableEntity = (VBdTableEntity) bdColomns.getTypeValue();

        if (bdColomns.getTypeValue().getTypeObject().getCode().equals(TypeObject.STRING.getName())) {
            stringBuffer.append(" VARCHAR2(" + vBdTableEntity.getLength() + ") " + ((bdColomns.getNotNull().equals("1")) ? "not null" : ""));
        } else if (bdColomns.getTypeValue().getTypeObject().getCode().equals(TypeObject.NUMBER.getName())) {
            Long len = vBdTableEntity.getLength();
            Long pres = vBdTableEntity.getPrecision();
            String paramNum = "";
            if (len > 0 && pres > 0) {
                paramNum = "(" + len + "," + pres + ")";
            } else if (len > 0) {
                paramNum = "(" + len + ")";
            }
            stringBuffer.append(" NUMBER" + paramNum + " " + ((bdColomns.getNotNull().equals("1")) ? "not null" : ""));
        } else if (bdColomns.getTypeValue().getTypeObject().getCode().equals(TypeObject.DATE.getName())) {
            stringBuffer.append(" DATE " + ((bdColomns.getNotNull().equals("1")) ? "not null" : ""));
        } else if (bdColomns.getTypeValue().getTypeObject().getCode().equals(TypeObject.REFERENCE.getName())) {
            stringBuffer.append(" NUMBER " + ((bdColomns.getNotNull().equals("1")) ? "not null" : ""));
            queryTable.set(queryTable.count().add(1), new Varchar2(stringBuffer.toString()));
            String pref = AppConst.getTune(ListTunes.PREFIX_TABLE);
            stringBuffer = new StringBuffer(AddConstraintSql.getSql(pref + (bdColomns).getParent().getCode(), (bdColomns).getCode(), pref + vBdTableEntity.getToType().getCode(), "ID"));
        } else if (bdColomns.getTypeValue().getTypeObject().getCode().equals(TypeObject.ARRAY.getName())) {
            stringBuffer.append(" NUMBER not null");

            /*Это перенести в создание типа массив
            String tmp = AddIndexSql.generateSys(AppConst.getTune(ListTunes.PREFIX_TABLE)+ bdColomns.getParent().getCode(),AppConst.getTune(ListTunes.PREFIX_COLOMN)+bdColomns.getCode());
            queryTable.set(queryTable.count().add(1), new Varchar2(tmp));

            */
        }

        /*stringBuffer.append("alter table " + tableDBToJavaClass.getDbTable() + "\n");
        stringBuffer.append("add constraint PK#" + tableDBToJavaClass.getDbTable() + "_ID primary key (" + tableDBToJavaClass.getIdColomn().getDbField() + ")\n");
        stringBuffer.append(" using index tablespace \n" + AppConst.getTune(ListTunes.TABLE_SPASE_USER_INDEX) + AppConst.getTune(ListTunes.STORAGE_INDEX));*/
        queryTable.set(queryTable.count().add(1), new Varchar2(stringBuffer.toString()));

        return queryTable;
    }
}
