package ru.vood.Plugin.admPlugin.spring.except;

public class NoDataFoundException extends CoreExeption {

    public NoDataFoundException() {
        super("Данные не найдены");
    }
}
