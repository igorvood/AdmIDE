package ru.vood.Plugin.admPlugin.spring.except;

public class TooManyRowsException extends ApplicationException {

    public TooManyRowsException() {
        super("Найдено несколько запесей, ожидалась одна");
    }

}
