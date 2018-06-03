package ru.vood.Plugin.Refactoring;

import ru.vood.Plugin.db.QueryTable;
import ru.vood.Plugin.logging.Log;

import java.sql.ResultSet;


public class Query {

    private static Log log = Log.getLogger(Query.class);

    public static boolean executeCreate(QueryTable query) {
        boolean retVal = true;
        ru.vood.core.runtime.type.Number key = query.first();
        ResultSet r = null;
        while (!key.isNull_booleanValue()) {
            r = DbQuery.executeSelectQuery(query.get(key).getValue());
            retVal = retVal && (r != null);
            DbQuery.closeCursor(r);
            key = query.next(key);
        }
        return retVal;
    }
}
