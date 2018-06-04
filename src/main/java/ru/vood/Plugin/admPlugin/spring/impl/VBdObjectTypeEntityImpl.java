package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectTypeEntityService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdObjectTypeEntityRepository;

@Service("jpaVBdObjectTypeEntityService")
@Repository
@Transactional
public class VBdObjectTypeEntityImpl /*extends ParentForAllImpl*/ implements VBdObjectTypeEntityService {

    @Autowired
    private VBdObjectTypeEntityRepository bdObjectTypeEntityRepository;

    @Override
    public VBdObjectTypeEntity save(VBdObjectTypeEntity bdObjectTypeEntity) {
        return bdObjectTypeEntityRepository.save(bdObjectTypeEntity);
    }

    @Override
    public void delete(VBdObjectTypeEntity bdObjectTypeEntity) {
        bdObjectTypeEntityRepository.delete(bdObjectTypeEntity);
    }

    @Override
    public VBdObjectTypeEntity findByCode(String code) {
        return bdObjectTypeEntityRepository.findByCode(code).get(0);
    }
}
