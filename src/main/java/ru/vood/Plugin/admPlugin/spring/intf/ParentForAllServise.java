package ru.vood.Plugin.admPlugin.spring.intf;

import ru.vood.Plugin.admPlugin.spring.entity.ParentForAll;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public interface ParentForAllServise {

    EntityManager getEntityManager();

    <S extends ParentForAll> S save(S s);

    <S extends ParentForAll> S findOne(BigDecimal bigDecimal);

    boolean exists(BigDecimal bigDecimal);

    long count();

    //void delete(BigDecimal bigDecimal);

    <S extends ParentForAll> void delete(S s);

    void delete(Iterable<? extends ParentForAll> iterable);

    //--------------------------------individual------------------------------------

    /**
     * @return возвращает следующий ID из последовательности
     */
    //BigDecimal nextId();

}
