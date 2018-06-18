package ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepFirstLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vood.Plugin.admPlugin.spring.except.CoreExeption;
import ru.vood.Plugin.admPlugin.sql.QueryTableNew;
import ru.vood.Plugin.admPlugin.sql.additionalSteps.oracle.stepToCreate.abstr.TuneChainStepsCreateServise;


@Component
public class TuneChainStepsFirstLoad {

    @Autowired
    @Qualifier("tuneChainStepsCreate")
    private TuneChainStepsCreateServise stepsCreate;

    @Autowired
    private LObjType lObjType;

    @Autowired
    private LObject lObj;

    @Autowired
    private LColomns lColomns;

    @Autowired
    private LIndexedColumns indexedColumns;

    @Autowired
    private LTable lTable;

    @Autowired
    private LIndex lIndex;

    @Autowired
    private LView lView;

    @Autowired
    private LObjTypeInsert lObjTypeInsert;

    @Autowired
    private LObjectInsert lObjectInsert;

    @Autowired
    private LTableInsert lTableInsert;

    public void run() throws CoreExeption {
        QueryTableNew queryTable = new QueryTableNew();
        // 1 создание таблицы типов объектов
        queryTable.addAll(lObjType.additionOne());

        // 2 создание таблицы объектов
        queryTable.addAll(lObj.additionOne());

        // 3 создание таблицы колонок
        queryTable.addAll(lColomns.additionOne());

        // 4 создание таблицы Таблиц
        queryTable.addAll(lTable.additionOne());

        // 5 создание Таблицы индексов
        queryTable.addAll(lIndex.additionOne());

        // 6 Создоание таблицы с индексированными колонками
        queryTable.addAll(indexedColumns.additionOne());

        // 7 создание Представлений
        queryTable.addAll(lView.additionOne());

        // Запуск созданных DDL Комманд
        stepsCreate.runChain(queryTable);


        // --------------------- пеовоначальная залива созданных таблиц-------------------------
        // 1.1 заполнение типов объектов, первоначальная заливка таблицы
        lObjTypeInsert.additionOne();

        // 2.1 заполнение объектов, первоначальная заливка таблицы
        lObjectInsert.additionOne();

        // 4.1 создание Таблиц, первоначальная заливка таблицы
        lTableInsert.additionOne();
    }
}
