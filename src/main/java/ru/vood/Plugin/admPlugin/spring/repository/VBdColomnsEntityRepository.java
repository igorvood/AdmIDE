package ru.vood.Plugin.admPlugin.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;

import java.math.BigDecimal;
import java.util.List;

public interface VBdColomnsEntityRepository extends CrudRepository<VBdColomnsEntity, BigDecimal> {

    <S extends VBdColomnsEntity> S save(S s);

    <S extends VBdColomnsEntity> Iterable<S> save(Iterable<S> iterable);

    VBdColomnsEntity findOne(BigDecimal bigDecimal);

    boolean exists(BigDecimal bigDecimal);

    Iterable<VBdColomnsEntity> findAll();

    Iterable<VBdColomnsEntity> findAll(Iterable<BigDecimal> iterable);

    long count();

    void delete(BigDecimal bigDecimal);

    void delete(VBdColomnsEntity vBdColomnsEntity);

    void delete(Iterable<? extends VBdColomnsEntity> iterable);

    //------------------------------individual-----------------------------

    List<VBdColomnsEntity> findByCode(String code);

    //List<VBdColomnsEntity> findByParent(VBdObjectEntity parent);

}
