package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdColomnsEntityService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdColomnsEntityRepository;

import java.util.List;

@Service("jpaVBdColomnsEntityService")
@Repository
@Transactional

public class VBdColomnsEntityImpl extends VBdObjectEntityImpl /*ParentForAllImpl*/ implements VBdColomnsEntityService {
    @Autowired
    private VBdColomnsEntityRepository bdColomnsEntityRepository;

    @Override
    public List<VBdColomnsEntity> findByParent(VBdObjectEntity parent) {
        return bdColomnsEntityRepository.findByParent(parent);
    }
}

