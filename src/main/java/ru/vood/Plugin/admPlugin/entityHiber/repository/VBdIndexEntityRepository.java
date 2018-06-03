package ru.vood.Plugin.admPlugin.entityHiber.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vood.Plugin.admPlugin.entityHiber.VBdIndexEntity;

import java.math.BigDecimal;
import java.util.List;

public interface VBdIndexEntityRepository extends CrudRepository<VBdIndexEntity, BigDecimal> {
    <S extends VBdIndexEntity> S save(S s);

    <S extends VBdIndexEntity> Iterable<S> save(Iterable<S> iterable);

    VBdIndexEntity findOne(BigDecimal bigDecimal);

    boolean exists(BigDecimal bigDecimal);

    Iterable<VBdIndexEntity> findAll();

    Iterable<VBdIndexEntity> findAll(Iterable<BigDecimal> iterable);

    long count();

    void delete(BigDecimal bigDecimal);

    void delete(VBdIndexEntity vBdIndexEntity);

    void delete(Iterable<? extends VBdIndexEntity> iterable);
    //------------------------------individual-----------------------------

    List<VBdIndexEntity> findByCode(String code);

}
