package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdIndexEntityService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdIndexEntityRepository;
import ru.vood.Plugin.admPlugin.spring.repository.VBdIndexedColomnsEntityRepository;

import javax.persistence.EntityManager;

@Service("jpaVBdIndexEntityTestImpService")
@Repository
@Transactional

public class VBdIndexEntityImp implements VBdIndexEntityService {

    @Autowired
    protected VBdIndexEntityRepository entityTestRepository;
    @Autowired
    protected VBdIndexedColomnsEntityRepository colomnsEntityRepository;

    @Autowired
    protected EntityManager em;


    @Override
    public VBdIndexEntity save(VBdIndexEntity entity) {
        if (entity.getColomnsEntities() != null) {
            entity.getColomnsEntities().stream().forEach(col -> colomnsEntityRepository.save(col));
        }
        return entityTestRepository.save(entity);
    }

    @Override
    public void delete(VBdIndexEntity entity) {
        if (entity.getColomnsEntities() != null) {
            entity.getColomnsEntities().stream().forEach(col -> colomnsEntityRepository.delete(col));
        }
        entityTestRepository.delete(entity);
    }
}
