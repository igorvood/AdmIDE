package ru.vood.Plugin.admPlugin.entityHiber.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.entityHiber.ParentForAll;
import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.entityHiber.intf.VBdObjectEntityService;
import ru.vood.Plugin.admPlugin.entityHiber.repository.VBdObjectEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("jpaVBdObjectEntityService")
@Repository
@Transactional

public class VBdObjectEntityImpl extends ParentForAllImpl implements VBdObjectEntityService {

    @Autowired
    private VBdObjectEntityRepository vBdObjectEntityRepository;

    @Autowired
    private EntityManager em;

    public ParentForAll findOne(BigDecimal bigDecimal) {
        return vBdObjectEntityRepository.findOne(bigDecimal);
    }

    public boolean exists(BigDecimal bigDecimal) {
        return vBdObjectEntityRepository.exists(bigDecimal);
    }

    public long count() {
        return vBdObjectEntityRepository.count();
    }

    public void delete(Iterable<? extends ParentForAll> iterable) {
        vBdObjectEntityRepository.delete((Iterable<? extends VBdObjectEntity>) iterable);
    }

    @Cacheable("сacheFromDB")
    public ArrayList<VBdObjectEntity> findByTypeObjectCodeIn(String... codeS) {

        List<String> stringList = new ArrayList<>(codeS.length);
        for (int i = 0; i < codeS.length; i++) {
            stringList.add(codeS[i]);
        }
        Query query = em.createQuery("select a2 from VBdObjectTypeEntity a1, VBdObjectEntity a2 where a1.code in :codeTypeS " +
                " and a2.typeObject = a1.id " +
                " order by a2.id ")
                .setParameter("codeTypeS", stringList);
        List list1 = (ArrayList<VBdObjectEntity>) query.getResultList();
        return (ArrayList<VBdObjectEntity>) list1;
    }

    public ArrayList<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent(String code, String typeObjectCode, VBdObjectEntity parent) {
        Query query = em.createQuery("select a2 from VBdObjectTypeEntity a1, VBdObjectEntity a2 where a1.code = :codeType " +
                "and a2.typeObject = a1.id and a2.code = :code and a2.parent = :parentId", VBdObjectEntity.class)
                .setParameter("codeType", typeObjectCode)
                .setParameter("code", code)
                .setParameter("parentId", parent);

/*        String s = "select a2.* from " + ParentForAll.SCHEMA + ".V_BD_OBJECT_TYPE a1, " + ParentForAll.SCHEMA + ".V_BD_OBJECT a2 " + " where " +
                "a1.code = '" + typeObjectCode + "' and a2.TYPE_OBJECT = a1.id and a2.CODE = '" + code + "' and a2.PARENT = " + parent.getId();
        Query query = em.createNativeQuery(s, VBdObjectEntity.class);*/
        List list = query.getResultList();
        return (ArrayList<VBdObjectEntity>) list;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public <S extends ParentForAll> S save(S s) {
        return (S) vBdObjectEntityRepository.save((VBdObjectEntity) s);
    }

    public List<VBdObjectEntity> findByCode(String code) {
        return vBdObjectEntityRepository.findByCode(code);
    }

    public ArrayList<VBdObjectEntity> findByCodeAndTypeObjectCodeAndParent_TEST(String code, String typeObjectCode, VBdObjectEntity parent) {
        Query query;
/*      Работает такой запрос
        query = em.createQuery("select a2 from VBdObjectEntity a2 where a2.code = :code1 and a2.parent = :parentId1")
                .setParameter("code1", code)
                .setParameter("parentId1", parent);
*/

/*      Работает такой запрос
        query = em.createQuery("select a2 from VBdObjectEntity a2 where a2.code = :code1 and a2.parent = :parentId1");
        List list = query.getResultList();
*/
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("DATE");
        arrayList.add("REFERENCE");
        arrayList.add("TABLE");
        query = em.createQuery("select a2 from VBdObjectEntity a2 where a2.code in :code1").setParameter("code1", arrayList);
        List list = query.getResultList();

        return (ArrayList<VBdObjectEntity>) list;
    }
}
