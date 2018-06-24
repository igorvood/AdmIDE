package ru.vood.Plugin.admPlugin.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;

import java.math.BigDecimal;
import java.util.List;

public interface VBdObjectTypeEntityRepository extends CrudRepository<VBdObjectTypeEntity, BigDecimal> {


    //------------------------------individual-----------------------------

    List<VBdObjectTypeEntity> findByCodeIn(String... code);

    List<VBdObjectTypeEntity> findByCode(String code);

}
