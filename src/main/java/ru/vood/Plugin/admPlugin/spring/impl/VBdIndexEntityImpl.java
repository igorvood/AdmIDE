package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdIndexEntityService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdIndexEntityRepository;

import java.util.List;

@Service("jpaVBdIndexEntityService")
@Repository
@Transactional
public class VBdIndexEntityImpl implements VBdIndexEntityService {

    @Autowired
    private VBdIndexEntityRepository vBdIndexEntityRepository;

    @Override
    public VBdIndexEntity save(VBdIndexEntity entity) {
        return vBdIndexEntityRepository.save(entity);
    }

    @Override
    public void delete(VBdIndexEntity entity) {
        vBdIndexEntityRepository.delete(entity);
    }

    @Override
    public List<VBdIndexEntity> findByTypeObjectCodeIn(String... codeS) {
        return null;
    }

    @Override
    public List<VBdIndexEntity> findByParent(VBdObjectEntity parent) {
        return vBdIndexEntityRepository.findByParent(parent);
    }
}

