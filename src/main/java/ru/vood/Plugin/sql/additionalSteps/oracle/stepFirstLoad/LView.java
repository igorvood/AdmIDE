package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.db.QueryTable;
import ru.vood.core.runtime.type.Varchar2;

public class LView extends StepsFirstLoad {
    @Override
    QueryTable additionOne(QueryTable queryTable) {
        if (queryTable == null) {
            queryTable = new QueryTable();
        }

        String s = ("CREATE OR REPLACE VIEW VW_CLASS_FOR_TREE AS \n" +
                " select level as c_level, a1.*\n" + ", a3.table_space, a3.storage, a3.to_type, a3.length, a3.precision \n" +
                "                from " + AppConst.getTune(ListTunes.OWNER) + ".V_BD_OBJECT a1\n" +
                "                   , " + AppConst.getTune(ListTunes.OWNER) + ".V_BD_OBJECT_TYPE a2\n" +
                "                   , " + AppConst.getTune(ListTunes.OWNER) + ".V_BD_TABLE a3\n" +
                "               where a1.TYPE_OBJECT = a2.id\n" +
                "                       and a3.id(+) = a1.id\n" +
                "                   and a2.code in ('DATE' , 'REFERENCE' , 'ARRAY' , 'STRING' , 'NUMBER' , 'TABLE')\n" +
                "                CONNECT BY PRIOR a1.ID=a1.PARENT\n" +
                "                start with a1.PARENT is null ORDER SIBLINGS by a1.id desc\n" +
                "                ");

        queryTable.set(queryTable.count().add(1), new Varchar2(s));


        s = ("CREATE OR REPLACE VIEW VW_COLOMN_FOR_TABLE AS\n" +
                "select col.id, " +
                "col.not_null, " +
                "col.type_colomn, " +
                "col.type_value, " +
                "obj.code, " +
                "obj.name,obj.parent, " +
                "obj.type_object, " +
                "obj.java_class " +
                "           from " + AppConst.getTune(ListTunes.OWNER) + ".v_BD_COLOMNS col\n" +
                "              ," + AppConst.getTune(ListTunes.OWNER) + ".V_BD_OBJECT obj\n" +
                "           where col.id=   obj.id");
        queryTable.set(queryTable.count().add(1), new Varchar2(s));

        return queryTable;
    }
}