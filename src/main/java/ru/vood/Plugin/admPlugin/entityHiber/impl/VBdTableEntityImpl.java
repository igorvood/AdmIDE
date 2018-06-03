package ru.vood.Plugin.admPlugin.entityHiber.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.entityHiber.ParentForAll;
import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.entityHiber.VBdTableEntity;
import ru.vood.Plugin.admPlugin.entityHiber.intf.VBdObjectEntityService;
import ru.vood.Plugin.admPlugin.entityHiber.intf.VBdTableEntityService;
import ru.vood.Plugin.admPlugin.entityHiber.repository.VBdTableEntityRepository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("jpaVBdTableEntityService")
@Repository
@Transactional
public class VBdTableEntityImpl extends ParentForAllImpl implements VBdTableEntityService {

    @Autowired
    private VBdTableEntityRepository bdTableEntityRepository;

    @Autowired
    private EntityManager em;

    public ParentForAll findOne(BigDecimal bigDecimal) {
        return bdTableEntityRepository.findOne(bigDecimal);
    }

    public List<VBdObjectEntity> findByTypeObjectCodeIn(String... codeS) {
        VBdObjectEntityService vBdObjectEntityService = (VBdObjectEntityService) ParentForAll.getServise(VBdObjectEntity.class);
        return vBdObjectEntityService.findByTypeObjectCodeIn(codeS);
    }

    public boolean exists(BigDecimal bigDecimal) {
        return bdTableEntityRepository.exists(bigDecimal);
    }

    public long count() {
        return bdTableEntityRepository.count();
    }

    public void delete(Iterable<? extends ParentForAll> iterable) {
        bdTableEntityRepository.delete((Iterable<? extends VBdTableEntity>) iterable);
    }

    public List<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent(String codeS, String typeObjectCode, VBdObjectEntity parent) {
        return ((VBdObjectEntityService) ParentForAll.getServise(VBdObjectEntity.class)).findByCodeAndTypeObjectCodeAndParent(codeS, typeObjectCode, parent);
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public <S extends ParentForAll> S save(S s) {
        return (S) bdTableEntityRepository.save((VBdTableEntity) s);
    }

    public List<VBdTableEntity> findByCode(String code) {
        return bdTableEntityRepository.findByCode(code);
    }

    public ArrayList<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent_TEST(String code, String typeObjectCode, VBdObjectEntity parent) {
        List list = null;
        return (ArrayList<VBdObjectEntity>) list;
    }

}
