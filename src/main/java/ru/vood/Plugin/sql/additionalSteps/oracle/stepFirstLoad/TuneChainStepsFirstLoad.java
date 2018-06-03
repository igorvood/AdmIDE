package ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad;

import java.util.ArrayList;


public class TuneChainStepsFirstLoad {

    public static void runChain() {
        ArrayList<StepsFirstLoad> steps = new ArrayList<>();
        // 1 заполнение типов объектов
        steps.add(new LObjType());

        // 2 заполнение объектов
        steps.add(new LObject());

        // 3 создание таблицы колонок
        steps.add(new LColomns());

        // 4 создание Таблиц
        steps.add(new LTable());

        // 5 создание Таблицы индексов
        steps.add(new LIndex());

        // 6 создание Представлений
        steps.add(new LView());

        // 1.1 заполнение типов объектов, первоначальная заливка таблицы
        steps.add(new LObjTypeInsert());

        // 2.1 заполнение объектов, первоначальная заливка таблицы
        steps.add(new LObjectInsert());

        // 4.1 создание Таблиц, первоначальная заливка таблицы
        steps.add(new LTableInsert());


        for (int i = steps.size() - 2; i >= 0; i--) {
            steps.get(i).setNextStep(steps.get(i + 1));
        }

        // Вызов первого, остальное пойдет по цепочке
        steps.get(0).addition();
    }

}
