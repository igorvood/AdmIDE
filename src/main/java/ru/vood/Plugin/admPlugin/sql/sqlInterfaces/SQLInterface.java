package ru.vood.Plugin.admPlugin.sql.sqlInterfaces;

import java.math.BigDecimal;

public interface SQLInterface {

    public final static String COLLECTION = "COLLECTIONID";

    String getSQLForAddCollectionId(String tableShortName);

    String getSQLNextId();

    BigDecimal getNextId();

    String getIsExistsQuery(Object o);

}
