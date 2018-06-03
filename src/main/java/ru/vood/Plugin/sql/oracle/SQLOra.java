package ru.vood.Plugin.sql.oracle;

import ru.vood.Plugin.admPlugin.entityHiber.ParentForAll;
import ru.vood.Plugin.admPlugin.entityHiber.impl.CommonFunction;
import ru.vood.Plugin.admPlugin.entityHiber.intf.CommonFunctionService;
import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.applicationConst.SystemObject;
import ru.vood.Plugin.sql.sqlInterfaces.SQLInterface;

import java.math.BigDecimal;

public class SQLOra implements SQLInterface {

    public String getSQLForAddCollectionId(String tableShortName) {
        StringBuffer sb = new StringBuffer();
        sb.append("alter table " + AppConst.getTune(ListTunes.OWNER) + "." + AppConst.getTune(ListTunes.PREFIX_TABLE) + tableShortName +
                " add collectionid NUMBER");
        return sb.toString();
    }

    public String getSQLNextId() {
        StringBuffer sb = new StringBuffer();
        sb.append("select " + SystemObject.SEQ_ID.getNameInDB() + ".nextval from dual");
        return sb.toString();
    }


    public BigDecimal getNextId() {
        CommonFunctionService commonFunctionService = (CommonFunctionService) ParentForAll.getServise(CommonFunction.class);
        return commonFunctionService.nextId();
    }

    @Override
    public String getIsExistsQuery(Object o) {
  /*      if (o.getClass()== Allclass.class || o.getClass()== ExtAllClass.class) {
            return SQLAllClassFactory.getInstance().getIsExistsQuery((Allclass) o);
        }else if (o.getClass()== ClassProperty.class || o.getClass()== ExtClassProperty.class) {
            return SQLClassPropertyFactory.getInstance().getIsExistsQuery((ClassProperty) o);
        }
        return null;
*/
        return null;
    }

}
