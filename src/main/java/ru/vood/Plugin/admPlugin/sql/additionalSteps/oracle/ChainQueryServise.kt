package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.datasource.DriverManagerDataSource
import ru.vood.Plugin.admPlugin.spring.except.CoreExeption
import ru.vood.Plugin.admPlugin.sql.QueryTableNew
import java.sql.Connection

abstract class ChainQueryServise {
    protected val lOG = LoggerFactory.getLogger(ChainQueryServise::class.java)
    @Autowired
    protected lateinit var dataSource: DriverManagerDataSource

    fun runQueryes(queryTable: QueryTableNew) {
        var conn: Connection = dataSource.connection
        for (q in queryTable) {
            if (!conn.isClosed) {
                var stmt = conn.createStatement()
                if (lOG.isDebugEnabled) {
                    lOG.debug("Попытка выполнить запрос '$q'")
                }
                try {
                    var r = stmt.executeQuery(q)
                    r?.close()
                } catch (e: Exception) {
                    lOG.error("Попытка выполнить запрос '$q'", e)
                    throw CoreExeption("Не удалось выполнить запрос \n ${q}")
                }

//                val i = queryTable.indexOf(q)
//                if (i == 25) {
//                    println("икуфл")
//                }

                stmt?.close()
            }
        }
        conn?.close()
    }

}