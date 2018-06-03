package ru.vood.Plugin.dinamicCall_2.sqlInterface;

@Deprecated
public interface SQLClassPropertyInterface {
    //public String getSQLForTree(Allclass ac);

    //String getGreateSQL(ClassProperty cp);

    /**
     * @param cp         - изменный объект
     * @param baseEntity - изменяемый объект
     * @return для объекта выполняется метод getEditSQL, класс этого объкта должен иметь этот метод.
     * Класс должен являться сам EJB классом или быть его наследником
     * Метод не должен иметь параметров и возвращать строку в которой через разделитель '|' перечисленны запросы
     * на редактирование объекта в БД и обновление соответствующих таблиц
     */
    //String getEditSQL(ClassProperty cp, Object baseEntity);


    /**
     * при переименовании таблицы ее необходимо переименовать также в таблица связанной с Allclass, но это мешают сделать реквизиты из таблицы
     * связанной с ClassProperty(там есть внешний ключ). Поэтому нужно сначала перенаправить все реквизиты таблицы на какой то тип, переименовать, а потом вернуть обратно.
     * Но в уже переименованную таблицу
     *
     * @param acSource    первоначальная таблица
     * @param acResiver   переименованная таблица
     * @param queryRename набор запросов по переименованию
     * @return
     */
    // QueryTable getSQLForRenameTable(Allclass acSource, Allclass acResiver, QueryTable queryRename);

    /**
     * Возвращает строчку запроса для поиска объекта classProperty в БД
     *
     * @param classProperty
     * @return
     */

    //   String getIsExistsQuery(ClassProperty classProperty);


}
