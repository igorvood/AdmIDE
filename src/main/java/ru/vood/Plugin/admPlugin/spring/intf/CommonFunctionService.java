package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.except.NoDataFoundException;
import ru.vood.Plugin.admPlugin.spring.except.TooManyRowsException;

import java.math.BigDecimal;
import java.util.List;

public interface CommonFunctionService {

    BigDecimal nextId();

    default void checkOnNoDataFound(List list) {
        if (list == null || list.isEmpty()) {
            throw new NoDataFoundException();
        }
    }

    default void checkOnTooManyRows(List list) {
        if (list == null || list.size() > 1) {
            throw new TooManyRowsException();
        }
    }

    default Object checkOn(List list) {
        checkOnNoDataFound(list);
        checkOnTooManyRows(list);
        return list.get(0);
    }

}
