package ru.vood.Plugin.Refactoring;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.db.QueryTable;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;


public class Query {


    private static Logger logger = Logger.getLogger(Query.class.toString());

    public static boolean executeCreate(QueryTable query) {
        EntityManager manager = LoadedCTX.getManager();
        DriverManagerDataSource dataSource = LoadedCTX.getService(DriverManagerDataSource.class);
        Connection connection;
        Statement statement;


        boolean retVal = true;
        ru.vood.core.runtime.type.Number key = query.first();
        ResultSet r = null;
        while (!key.isNull_booleanValue()) {
            try {
                connection = dataSource.getConnection();
                if (!connection.isClosed()) {
                    statement = connection.createStatement();
                    r = statement.executeQuery(query.get(key).getValue());
                    System.out.println(" Выполнен запрос. " + query + " Результат " + r);
                } else {
                    System.out.println(" Нет соединения. ");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    r.getStatement().close();
                    r.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            key = query.next(key);
        }
        return retVal;
    }
}
