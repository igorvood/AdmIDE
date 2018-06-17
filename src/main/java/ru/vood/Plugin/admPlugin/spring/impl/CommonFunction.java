package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.intf.CommonFunctionService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

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


}
