package ru.vood.Plugin.admPlugin.entityHiber.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectEntity;

import java.math.BigDecimal;
import java.util.List;

public interface VBdObjectEntityRepository extends CrudRepository<VBdObjectEntity, BigDecimal> {

    <S extends VBdObjectEntity> S save(S s);

    <S extends VBdObjectEntity> Iterable<S> save(Iterable<S> iterable);

    VBdObjectEntity findOne(BigDecimal bigDecimal);

    boolean exists(BigDecimal bigDecimal);

    Iterable<VBdObjectEntity> findAll();

    Iterable<VBdObjectEntity> findAll(Iterable<BigDecimal> iterable);

    long count();

    void delete(BigDecimal bigDecimal);

    void delete(VBdObjectEntity vBdObjectEntity);

    void delete(Iterable<? extends VBdObjectEntity> iterable);

    //---------------------------individual
    List<VBdObjectEntity> findByCode(String code);
}
