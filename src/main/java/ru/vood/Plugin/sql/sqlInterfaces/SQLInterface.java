package ru.vood.Plugin.sql.sqlInterfaces;

import java.math.BigDecimal;

public interface SQLInterface {

    String getSQLForAddCollectionId(String tableShortName);

    String getSQLNextId();

    BigDecimal getNextId();

    String getIsExistsQuery(Object o);

}