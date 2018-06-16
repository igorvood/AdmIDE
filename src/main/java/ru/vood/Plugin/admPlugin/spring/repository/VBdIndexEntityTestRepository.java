package ru.vood.Plugin.admPlugin.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntityTest;

import java.math.BigDecimal;

public interface VBdIndexEntityTestRepository extends CrudRepository<VBdIndexEntityTest, BigDecimal> {

}
