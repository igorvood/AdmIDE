package ru.vood.Plugin.admPlugin.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;

import java.math.BigDecimal;

public interface VBdIndexEntityRepository extends CrudRepository<VBdIndexEntity, BigDecimal> {

}
