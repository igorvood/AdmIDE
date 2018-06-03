package ru.vood.Plugin.admPlugin.entityHiber.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectTypeEntity;

import java.math.BigDecimal;
import java.util.List;

public interface VBdObjectTypeEntityRepository extends CrudRepository<VBdObjectTypeEntity, BigDecimal> {

    <S extends VBdObjectTypeEntity> S save(S s);

    <S extends VBdObjectTypeEntity> Iterable<S> save(Iterable<S> iterable);

    VBdObjectTypeEntity findOne(BigDecimal bigDecimal);

    boolean exists(BigDecimal bigDecimal);

    Iterable<VBdObjectTypeEntity> findAll();

    Iterable<VBdObjectTypeEntity> findAll(Iterable<BigDecimal> iterable);

    long count();

    void delete(BigDecimal bigDecimal);

    void delete(VBdObjectTypeEntity vBdObjectTypeEntity);

    void delete(Iterable<? extends VBdObjectTypeEntity> iterable);


    //------------------------------individual-----------------------------
    List<VBdObjectTypeEntity> findByCodeIn(String... code);

    List<VBdObjectTypeEntity> findByCode(String code);


}
