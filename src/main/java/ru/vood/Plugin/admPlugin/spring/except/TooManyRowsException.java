package ru.vood.Plugin.admPlugin.spring.except;

public class TooManyRowsException extends CoreExeption {

    public TooManyRowsException() {
        super("Найдено несколько запесей, ожидалась одна");
    }
}
