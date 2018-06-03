package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate;

import java.util.ArrayList;

public class TuneChainStepsCreate {
    public static void runChain(Object bdobj, String javaClass) {
        ArrayList<StepsCreate> steps = new ArrayList<>();

        // добавление таблицы
        steps.add(new AddTable(bdobj, javaClass));
        // добавление Колонки таблицы, в том числе и сложной
        steps.add(new AddArrayType(bdobj));
        // добавление первичного ключа для таблицы
        steps.add(new AddPrimaryKey(bdobj, javaClass));
        // добавление Вторичного ключа для таблицы, в случае если есть родительская таблица
        steps.add(new AddForeignKeyForParent(bdobj));
        // добавление Колонки таблицы, в том числе и сложной
        steps.add(new AddColomn(bdobj, javaClass));

        for (int i = steps.size() - 2; i >= 0; i--) {
            steps.get(i).setNextStep(steps.get(i + 1));
        }

        // Вызов первого, остальное пойдет по цепочке
        steps.get(0).addition();
    }
}
