package ru.vood.Plugin.admPlugin.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;

import java.math.BigDecimal;
import java.util.List;


public interface VBdObjectEntityRepository extends CrudRepository<VBdObjectEntity, BigDecimal> {

    //---------------------------individual
    //List<VBdObjectEntity> findByTypeObject(VBdObjectTypeEntity typeObject);
    List<VBdObjectEntity> findByCodeAndTypeObject(String code, VBdObjectTypeEntity typeObject);

    List<VBdObjectEntity> findByParent(VBdObjectEntity parent);

}
