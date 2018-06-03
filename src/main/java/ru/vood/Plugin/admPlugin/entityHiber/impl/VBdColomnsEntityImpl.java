package ru.vood.Plugin.admPlugin.entityHiber.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.entityHiber.ParentForAll;
import ru.vood.Plugin.admPlugin.entityHiber.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.entityHiber.intf.VBdColomnsEntityService;
import ru.vood.Plugin.admPlugin.entityHiber.intf.VBdObjectEntityService;
import ru.vood.Plugin.admPlugin.entityHiber.repository.VBdColomnsEntityRepository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("jpaVBdColomnsEntityService")
@Repository
@Transactional

public class VBdColomnsEntityImpl extends ParentForAllImpl implements VBdColomnsEntityService {
    @Autowired
    private VBdColomnsEntityRepository bdColomnsEntityRepository;

    @Autowired
    private EntityManager em;

    public List<VBdObjectEntity> findByTypeObjectCodeIn(String... codeS) {
        VBdObjectEntityService vBdObjectEntityService = (VBdObjectEntityService) ParentForAll.getServise(VBdObjectEntity.class);
        return vBdObjectEntityService.findByTypeObjectCodeIn(codeS);
    }

    public <S extends ParentForAll> S findOne(BigDecimal bigDecimal) {
        return (S) bdColomnsEntityRepository.findOne(bigDecimal);
    }

    public boolean exists(BigDecimal bigDecimal) {
        return bdColomnsEntityRepository.exists(bigDecimal);
    }

    public long count() {
        return bdColomnsEntityRepository.count();
    }

    public void delete(Iterable<? extends ParentForAll> iterable) {
        bdColomnsEntityRepository.delete((Iterable<? extends VBdColomnsEntity>) iterable);
    }

    public List<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent(String codeS, String typeObjectCode, VBdObjectEntity parent) {
        return ((VBdObjectEntityService) ParentForAll.getServise(VBdObjectEntity.class)).findByCodeAndTypeObjectCodeAndParent(codeS, typeObjectCode, parent);
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public <S extends ParentForAll> S save(S s) {
        return (S) bdColomnsEntityRepository.save((VBdColomnsEntity) s);
    }

    public List<VBdColomnsEntity> findByCode(String code) {
        return bdColomnsEntityRepository.findByCode(code);
    }

    public ArrayList<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent_TEST(String code, String typeObjectCode, VBdObjectEntity parent) {
        List list = null;
        return (ArrayList<VBdObjectEntity>) list;
    }

    public List<VBdColomnsEntity> findByParent(VBdObjectEntity parent) {
        return bdColomnsEntityRepository.findByParent(parent);
    }
}
