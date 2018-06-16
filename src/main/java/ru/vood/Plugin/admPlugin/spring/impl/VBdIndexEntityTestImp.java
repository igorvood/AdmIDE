package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntityTest;
import ru.vood.Plugin.admPlugin.spring.intf.VBdIndexEntityTestService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdIndexEntityTestRepository;

import javax.persistence.EntityManager;

@Service("jpaVBdIndexEntityTestImpService")
@Repository
@Transactional

public class VBdIndexEntityTestImp implements VBdIndexEntityTestService {

    @Autowired
    protected VBdIndexEntityTestRepository entityTestRepository;

    @Autowired
    protected EntityManager em;


    @Override
    public VBdIndexEntityTest save(VBdIndexEntityTest entity) {
        return entityTestRepository.save(entity);
    }

    @Override
    public void delete(VBdIndexEntityTest entity) {
        entityTestRepository.delete(entity);

    }
}
