package ru.vood.Plugin.admPlugin

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.jeeconf.hibernate.performancetuning.sqltracker.AssertSqlCount
import com.jeeconf.hibernate.performancetuning.sqltracker.QueryCountInfoHolder.getQueryInfo
import org.junit.Before
import org.springframework.context.support.GenericXmlApplicationContext
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.transaction.AfterTransaction
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import org.springframework.transaction.annotation.Transactional
import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX
import ru.vood.Plugin.admPlugin.spring.referenceBook.RootObjects
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepFirstLoad.TuneChainStepsFirstLoad
import java.util.*
import javax.persistence.EntityManager

@TestExecutionListeners(TransactionalTestExecutionListener::class, DependencyInjectionTestExecutionListener::class, DbUnitTestExecutionListener::class)
@Transactional

abstract class BaseTest {
    private val DB_UNIT_SET_UP = arrayOf("", "****************************************************************", "*************** RUN TEST ***************", "****************************************************************")

    protected lateinit var ctx: GenericXmlApplicationContext

    protected lateinit var em: EntityManager

    //protected lateinit var session: Session

    @Before
    fun dbAllSet() {
        Arrays.stream(DB_UNIT_SET_UP).forEach { println(it) }
        AssertSqlCount.reset()

        ctx = GenericXmlApplicationContext()
        if (!ctx.isRunning) {
            ctx.load("classpath:spring-config.xml")
            ctx.refresh()
            em = ctx.getBean(EntityManager::class.java)
        }

//        val drop = em.createNativeQuery(" call VOOD.DROP_ALL_TABLES()")
//        try {
//            drop.resultList
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
        try {
            RootObjects.getTABLE()
        } catch (e: Exception) {
            LoadedCTX.getService(TuneChainStepsFirstLoad::class.java).run()
        }
    }

    @AfterTransaction
    fun showSqlCount() {
        System.out.printf("\n-------------------------------------Sql count: " + getQueryInfo().countAll())
    }


//    protected fun getSessionFactory(): SessionFactory {
//        return session.sessionFactory
//    }


}