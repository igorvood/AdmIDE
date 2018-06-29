package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.vood.Plugin.admPlugin.spring.except.ApplicationException;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class TuneChainStepsCreateServise {

    protected final static Logger lOG = LoggerFactory.getLogger(TuneChainStepsCreateServise.class);
    @Autowired
    protected DriverManagerDataSource dataSource;

    public abstract void runChain(Object bdobj);

    public void runChain(QueryTableNew queryTable) {
        Connection conn;
        Statement stmt = null;
        ResultSet r = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            conn = null;
        }
        if (conn != null) {
            for (String q : queryTable) {
                try {
                    if (!conn.isClosed()) {
                        stmt = conn.createStatement();
                        if (lOG.isDebugEnabled()) {
                            lOG.debug("Попытка выполнить запрос '" + q + "'");
                        }
                        r = stmt.executeQuery(q);
                        int i = queryTable.indexOf(q);
                        if (i == 25) {
                            System.out.println("икуфл");
                        }
                    }
                } catch (SQLException e) {
                    int i = queryTable.indexOf(q);
                    lOG.error("Попытка выполнить запрос №" + i + " " + q + " не удалась. ", e);
                    throw new ApplicationException("Попытка выполнить запрос №" + i + " " + q + " не удалась. ", e);
                } finally {
                    try {
                        r.close();
                        stmt.close();
                    } catch (Exception e) {

                    }
                }
            }
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
