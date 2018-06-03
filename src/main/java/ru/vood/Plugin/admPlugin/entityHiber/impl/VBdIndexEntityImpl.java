package ru.vood.Plugin.admPlugin.entityHiber.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.entityHiber.ParentForAll;
import ru.vood.Plugin.admPlugin.entityHiber.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.entityHiber.intf.VBdIndexEntityService;
import ru.vood.Plugin.admPlugin.entityHiber.intf.VBdObjectEntityService;
import ru.vood.Plugin.admPlugin.entityHiber.repository.VBdIndexEntityRepository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("jpaVBdIndexEntityService")
@Repository
@Transactional
public class VBdIndexEntityImpl extends ParentForAllImpl implements VBdIndexEntityService {

    @Autowired
    private VBdIndexEntityRepository vBdIndexEntityRepository;

    @Autowired
    private EntityManager em;

    public List<VBdObjectEntity> findByTypeObjectCodeIn(String... codeS) {
        VBdObjectEntityService vBdObjectEntityService = (VBdObjectEntityService) ParentForAll.getServise(VBdObjectEntity.class);
        return vBdObjectEntityService.findByTypeObjectCodeIn(codeS);
    }

    public <S extends ParentForAll> S findOne(BigDecimal bigDecimal) {
        return (S) vBdIndexEntityRepository.findOne(bigDecimal);
    }

    public boolean exists(BigDecimal bigDecimal) {
        return vBdIndexEntityRepository.exists(bigDecimal);
    }

    public long count() {
        return vBdIndexEntityRepository.count();
    }

    public void delete(Iterable<? extends ParentForAll> iterable) {
        vBdIndexEntityRepository.delete((Iterable<? extends VBdIndexEntity>) iterable);
    }

    public List<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent(String codeS, String typeObjectCode, VBdObjectEntity parent) {
        return ((VBdObjectEntityService) ParentForAll.getServise(VBdObjectEntity.class)).findByCodeAndTypeObjectCodeAndParent(codeS, typeObjectCode, parent);
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public <S extends ParentForAll> S save(S s) {
        return (S) vBdIndexEntityRepository.save((VBdIndexEntity) s);
    }

    public List<VBdIndexEntity> findByCode(String code) {
        return vBdIndexEntityRepository.findByCode(code);
    }

    public ArrayList<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent_TEST(String code, String typeObjectCode, VBdObjectEntity parent) {
        List list = null;
        return (ArrayList<VBdObjectEntity>) list;
    }

}

