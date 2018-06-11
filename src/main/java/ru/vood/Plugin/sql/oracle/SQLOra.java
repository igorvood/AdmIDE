package ru.vood.Plugin.sql.oracle;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.intf.CommonFunctionService;
import ru.vood.Plugin.admPlugin.tune.PluginTunes;
import ru.vood.Plugin.applicationConst.SystemObject;
import ru.vood.Plugin.sql.sqlInterfaces.SQLInterface;

import java.math.BigDecimal;

public class SQLOra implements SQLInterface {


    public String getSQLForAddCollectionId(String tableShortName) {
        StringBuffer sb = new StringBuffer();
        PluginTunes pluginTunes = LoadedCTX.getService(PluginTunes.class);
        sb.append("alter table " + pluginTunes.getOwner() + "." + pluginTunes.getPrefixTable() + tableShortName +
                " add " + COLLECTION + " NUMBER");
        return sb.toString();
    }

    public String getSQLNextId() {
        StringBuffer sb = new StringBuffer();
        sb.append("select " + SystemObject.SEQ_ID.getNameInDB() + ".nextval from dual");
        return sb.toString();
    }


    public BigDecimal getNextId() {
        CommonFunctionService commonFunctionService = LoadedCTX.getService(CommonFunctionService.class);
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
