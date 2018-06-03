package ru.vood.Plugin.admPlugin.entityHiber.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vood.Plugin.admPlugin.entityHiber.VBdTableEntity;

import java.math.BigDecimal;
import java.util.List;

public interface VBdTableEntityRepository extends CrudRepository<VBdTableEntity, BigDecimal> {


    <S extends VBdTableEntity> S save(S s);

    <S extends VBdTableEntity> Iterable<S> save(Iterable<S> iterable);

    VBdTableEntity findOne(BigDecimal bigDecimal);

    boolean exists(BigDecimal bigDecimal);

    Iterable<VBdTableEntity> findAll();

    Iterable<VBdTableEntity> findAll(Iterable<BigDecimal> iterable);

    long count();

    void delete(BigDecimal bigDecimal);

    void delete(VBdTableEntity vBdTableEntity);

    void delete(Iterable<? extends VBdTableEntity> iterable);

    //------------------------------individual-----------------------------

    List<VBdTableEntity> findByCode(String code);
}
