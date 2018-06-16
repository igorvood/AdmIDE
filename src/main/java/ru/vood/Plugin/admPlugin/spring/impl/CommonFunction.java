package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.except.NoDataFoundException;
import ru.vood.Plugin.admPlugin.spring.except.TooManyRowsException;
import ru.vood.Plugin.admPlugin.spring.intf.CommonFunctionService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

//@Service("jpaCommonFunctionService")
@Service
@Repository
@Transactional
public class CommonFunction implements CommonFunctionService {

    @Autowired
    private EntityManager entityManager;

    public BigDecimal nextId() {
        return (BigDecimal) entityManager.createNativeQuery("SELECT SEQ_ID.nextval from dual").getResultList().get(0);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void checkOnNoDataFound(List list) {
        if (list == null || list.isEmpty()) {
            throw new NoDataFoundException();
        }
    }

    public void checkOnTooManyRows(List list) {
        if (list == null || list.size() > 1) {
            throw new TooManyRowsException();
        }
    }

    public Object checkOn(List list) {
        checkOnNoDataFound(list);
        checkOnTooManyRows(list);
        return list.get(0);
    }


}
