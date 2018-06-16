package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.sql.QueryTableNew;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class TuneChainStepsCreate {

    private final static Logger lOG = LoggerFactory.getLogger(TuneChainStepsCreate.class);


    @Autowired
    @Qualifier("addTableImpl")
    private StepsCreateServise table;

    @Autowired
    private DriverManagerDataSource dataSource;

    public void runChain(Object bdobj) {
        // Вызов первого, остальное пойдет по цепочке
        QueryTableNew queryTable = null;
        try {
            queryTable = table.runSteps((VBdObjectEntity) bdobj);
            runChain(queryTable);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

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
                    }
                } catch (SQLException e) {
                    int i = queryTable.indexOf(q);
                    lOG.error("Попытка выполнить запрос №" + i + " " + q + " не удалась. ", e);
                    e.printStackTrace();
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
