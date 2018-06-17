package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.except.CoreExeption;
import ru.vood.Plugin.admPlugin.spring.except.NoDataFoundException;
import ru.vood.Plugin.admPlugin.spring.except.TooManyRowsException;

import java.math.BigDecimal;
import java.util.List;

public interface CommonFunctionService {

    BigDecimal nextId();

    default Object checkOnNoDataFound(List list) throws NoDataFoundException {
        if (list == null || list.isEmpty()) {
            throw new NoDataFoundException();
        }
        return list;
    }

    default Object checkOnTooManyRows(List list) throws TooManyRowsException {
        if (list == null || list.size() > 1) {
            throw new TooManyRowsException();
        }
        return list;
    }

    default Object checkOn(List list) throws CoreExeption {
        checkOnNoDataFound(list);
        checkOnTooManyRows(list);
        return list.get(0);
    }

}
