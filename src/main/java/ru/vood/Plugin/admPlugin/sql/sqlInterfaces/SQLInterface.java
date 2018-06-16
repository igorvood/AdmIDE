package ru.vood.Plugin.admPlugin.sql.sqlInterfaces;

import java.math.BigDecimal;

public interface SQLInterface {

    public final static String COLLECTION = "COLLECTIONID";
    public final static String INDEX_PREFIX = "IDX_Z#";


    String getSQLForAddCollectionId(String tableShortName);

    String getSQLNextId();

    BigDecimal getNextId();

    String getIsExistsQuery(Object o);

}
