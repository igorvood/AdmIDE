package ru.vood.Plugin.admPlugin.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;

import java.math.BigDecimal;
import java.util.List;

public interface VBdIndexEntityRepository extends CrudRepository<VBdIndexEntity, BigDecimal> {


    //------------------------------individual-----------------------------

    List<VBdIndexEntity> findByCode(String code);

    List<VBdIndexEntity> findByParent(VBdObjectEntity parent);

}
