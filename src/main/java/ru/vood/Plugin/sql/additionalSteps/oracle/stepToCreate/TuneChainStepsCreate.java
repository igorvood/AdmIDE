package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate.abstr.StepsCreateServise;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class TuneChainStepsCreate {


    @Autowired
    @Qualifier("addTableImpl")
    private StepsCreateServise table;

    @Autowired
    private DriverManagerDataSource dataSource;

    public void runChain(Object bdobj) {
        // Вызов первого, остальное пойдет по цепочке
        QueryTableNew queryTable = table.runSteps((VBdObjectEntity) bdobj);
        runChain(queryTable);
    }

    public void runChain(QueryTableNew queryTable) {
        Connection conn;
        Statement stmt = null;
        ResultSet r = null;

        for (String q : queryTable) {
            try {
                conn = dataSource.getConnection();
                if (!conn.isClosed()) {
                    stmt = conn.createStatement();
                    r = stmt.executeQuery(q);
                }
            } catch (SQLException e) {
                int i = queryTable.indexOf(q);
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
}
