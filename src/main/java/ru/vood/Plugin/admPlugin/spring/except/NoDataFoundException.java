package ru.vood.Plugin.admPlugin.spring.except;

public class NoDataFoundException extends ApplicationException {

    public NoDataFoundException() {
        super("Данные не найдены");
    }
}
