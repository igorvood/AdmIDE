package ru.vood.Plugin.dinamicCall_2.sqlInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

@Deprecated
public interface SQLAllClassInteface {

    /**
     * собирает запросы на добавление новой таблицы на основе объекта ac
     *
     * @param ac
     * @return
     */
    //String getGreateSQL(Allclass ac);

    //  public String getSQLForTree();

    ResultSet getResulSetForTree() throws SQLException;

    //int execInsertSQL(Allclass ac);

    //String getEditSQL(Allclass ac, Object baseEntity);


    /**
     * Возвращает строчку запроса для поиска объекта allclass в БД
     *
     * @param allclass
     * @return
     */
    //String getIsExistsQuery(Allclass allclass);

}
