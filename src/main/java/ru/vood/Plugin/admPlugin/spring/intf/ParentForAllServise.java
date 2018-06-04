package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.ParentForAll;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@Deprecated
public interface ParentForAllServise<S extends ParentForAll> {

    EntityManager getEntityManager();

    S save(S s);

    S findOne(BigDecimal bigDecimal);

    boolean exists(BigDecimal bigDecimal);

    long count();

    //void delete(BigDecimal bigDecimal);

    void delete(S s);


    //--------------------------------individual------------------------------------

    /**
     * @return возвращает следующий ID из последовательности
     */
    //BigDecimal nextId();

}
