package ru.vood.Plugin.admPlugin.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;

import java.math.BigDecimal;
import java.util.List;


public interface VBdObjectEntityRepository<T extends VBdObjectEntity> extends CrudRepository<T, BigDecimal> {

    @Override
    T save(T entity);

    @Override
    default <S extends T> Iterable<S> save(Iterable<S> entities) {
        return null;
    }

    @Override
    default void delete(Iterable<? extends T> entities) {

    }

    @Override
    default void deleteAll() {

    }

    T save(T s);

    Iterable<T> save(Iterable<T> iterable);

    VBdObjectEntity findOne(BigDecimal bigDecimal);

    boolean exists(BigDecimal bigDecimal);

    Iterable<VBdObjectEntity> findAll();

    Iterable<VBdObjectEntity> findAll(Iterable<BigDecimal> iterable);

    long count();

    void delete(BigDecimal bigDecimal);

    void delete(VBdObjectEntity vBdObjectEntity);

    void delete(Iterable<? extends VBdObjectEntity> iterable);

    //---------------------------individual
    //List<VBdObjectEntity> findByTypeObject(VBdObjectTypeEntity typeObject);
    List<VBdObjectEntity> findByCode(String code);

    List<VBdObjectEntity> findByParent(VBdObjectEntity parent);

}
