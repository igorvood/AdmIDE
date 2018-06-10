package ru.vood.Plugin.sql.additionalSteps.oracle.stepToCreate;

import ru.vood.Plugin.Refactoring.Query;
import ru.vood.Plugin.db.QueryTable;

/**
 * Абстрактый класс для Выполнения индивидуальных шагов в зависимости от класса, например для таблицы - будет, создание первичного ключа, для
 */
@Deprecated
abstract public class StepsCreate {
    Object bdObject;
    // используется не всегда, только когда в создании учавствует 2 объекта. например внешний ключ к таблице завязан также на другую таблицу
    Object bdObjectIndirect;
    //String javaClass;
    StepsCreate nextStep;
    //Любые бополнительные аршументы, тут надо смотреть описание конкретного типа
    Object[] args;

    /**
     * @param bdObject - объкет по которому генерируется запрос
     */
    public StepsCreate(Object bdObject) {
        this.bdObject = bdObject;

    }

    /**
     * @param bdObject         - объкет по которому генерируется запрос
     * @param bdObjectIndirect - объкект необходимый для создания запроса, например для созданий внешнего ключа нужна вторая таблица к которой устанавливается связь
     */
    public StepsCreate(Object bdObject, Object bdObjectIndirect) {
        this.bdObject = bdObject;
        this.bdObjectIndirect = bdObjectIndirect;
        //this.javaClass = "class " + javaClass;
    }

    /**
     * @param bdObject         - объкет по которому генерируется запрос
     * @param bdObjectIndirect - объкект необходимый для создания запроса, например для созданий внешнего ключа нужна вторая таблица к которой устанавливается связь
     * @param adds             - любые аргументы. о правилах использования будет описано в каждом понкретном классе
     */
    public StepsCreate(Object bdObject, Object bdObjectIndirect, Object... adds) {
        this.bdObject = bdObject;
        this.bdObjectIndirect = bdObjectIndirect;
        this.args = adds;
        //this.javaClass = "class " + javaClass;
    }

    public void setNextStep(StepsCreate nextStep) {
        this.nextStep = nextStep;
    }

    public void addition() {

        QueryTable table = new QueryTable();
        table = additionOne(table);
        Query.executeCreate(table);

        if (this.nextStep != null) {
            this.nextStep.addition();
        }
    }

    abstract QueryTable additionOne(QueryTable queryTable);
}
