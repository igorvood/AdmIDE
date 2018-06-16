package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.spring.intf.CommonFunctionService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdObjectEntityService;
import ru.vood.Plugin.admPlugin.spring.referenceBook.ObjectTypes;
import ru.vood.Plugin.admPlugin.spring.repository.VBdObjectEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service//("jpaVBdObjectEntityService")
@Repository
@Transactional
public class VBdObjectEntityImpl /*extends ParentForAllImpl*/ implements VBdObjectEntityService {

    @Autowired
    protected VBdObjectEntityRepository vBdObjectEntityRepository;
    @Autowired
    protected EntityManager em;
    @Autowired
    private CommonFunctionService commonFunctionService;

    @Cacheable("сacheFromDBTree")
    public ArrayList<VBdObjectEntity> findByTypeObjectCodeIn(String... codeS) {

        List<String> stringList = new ArrayList<>(codeS.length);
        for (int i = 0; i < codeS.length; i++) {
            stringList.add(codeS[i]);
        }
        Query query = em.createQuery("select a2 from VBdObjectEntity a2 " +
                "  join fetch a2.typeObject a1 " + //" on a1.code in :codeTypeS  " +
                "  left join a2.parent a3  " +
                " where a1.code in :codeTypeS " +
                " order by a2.id ")
                .setParameter("codeTypeS", stringList);

        List list1 = (ArrayList<VBdObjectEntity>) query.getResultList();
        return (ArrayList<VBdObjectEntity>) list1;
    }
/*
    public ArrayList<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent(String code, String typeObjectCode, VBdObjectEntity parent) {
        Query query = em.createQuery("select a2 from VBdObjectTypeEntity a1, VBdObjectEntity a2 where a1.code = :codeType " +
                "and a2.typeObject = a1.id and a2.code = :code and a2.parent = :parentId", VBdObjectEntity.class)
                .setParameter("codeType", typeObjectCode)
                .setParameter("code", code)
                .setParameter("parentId", parent);

        List list = query.getResultList();
        return (ArrayList<VBdObjectEntity>) list;
    }
    */

    @Override
    public List<VBdObjectEntity> findByParent(VBdObjectEntity parent) {
        return vBdObjectEntityRepository.findByParent(parent);
    }

    @Override
    public VBdObjectEntity save(VBdObjectEntity entity) {
        if (entity.getDateCreate() == null) {
            entity.setDateCreate(new Date());
        }
        return vBdObjectEntityRepository.save(entity);
    }

    @Override
    public void delete(VBdObjectEntity entity) {
        vBdObjectEntityRepository.delete(entity);
    }

    @Override
    public VBdObjectEntity findOne(BigDecimal id) {
        Query query = em.createQuery("select a2 from VBdObjectEntity a2 " +
                "  join fetch a2.typeObject a1 " + //" on a1.code in :codeTypeS  " +
//                "  left join a2.parent a3  " +
                " where a2.id = :idd " +
                //" order by a2.id " +
                "")
                .setParameter("idd", id);
        List list1 = (ArrayList<VBdObjectEntity>) query.getResultList();
        return (VBdObjectEntity) list1.get(0);
//        return vBdObjectEntityRepository.findOne(id);
    }

    @Override
    public List<VBdObjectEntity> findByParentAndTypeObject(VBdObjectEntity parent, VBdObjectTypeEntity objectTypeEntity) {
        Query query = em.createQuery("select a1 from VBdObjectEntity a1 " +
                //"  join VBdObjectEntity a2 on a2 = :parent " +
                //"  join VBdObjectTypeEntity a3 on a3 = :objectTypeEntity " +
                " where a1.parent = :parent " +
                " and a1.typeObject = :objectType" +
                //" order by a2.id " +
                " ")
                .setParameter("parent", parent)
                .setParameter("objectType", objectTypeEntity);
        List list1 = (ArrayList<VBdObjectEntity>) query.getResultList();
        return list1;
    }

    @Override
    public VBdObjectEntity findByCode(String code) {
        List list = vBdObjectEntityRepository.findByCodeAndTypeObject(code, ObjectTypes.getOBJECT());
        commonFunctionService.checkOn(list);
        return (VBdObjectEntity) commonFunctionService.checkOn(list);
    }
}
