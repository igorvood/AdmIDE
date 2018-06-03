package ru.vood.Plugin.admPlugin.entityHiber.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.entityHiber.ParentForAll;
import ru.vood.Plugin.admPlugin.entityHiber.VBdObjectTypeEntity;
import ru.vood.Plugin.admPlugin.entityHiber.except.NoDataFoundException;
import ru.vood.Plugin.admPlugin.entityHiber.except.TooManyRowsException;
import ru.vood.Plugin.admPlugin.entityHiber.intf.VBdObjectTypeEntityService;
import ru.vood.Plugin.admPlugin.entityHiber.repository.VBdObjectTypeEntityRepository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static ru.vood.Plugin.admPlugin.entityHiber.ParentForAll.SCHEMA;

@Service("jpaVBdObjectTypeEntityService")
@Repository
@Transactional
public class VBdObjectTypeEntityImpl extends ParentForAllImpl implements VBdObjectTypeEntityService {

    @Autowired
    private VBdObjectTypeEntityRepository bdObjectTypeEntityRepository;

    @Autowired
    private EntityManager em;

   /* public <S extends ParentForAll> S save(S s) {
        return (S) s.save();
    }*/

    public ParentForAll findOne(BigDecimal bigDecimal) {
        return bdObjectTypeEntityRepository.findOne(bigDecimal);
    }

    public boolean exists(BigDecimal bigDecimal) {
        return bdObjectTypeEntityRepository.exists(bigDecimal);
    }

    public long count() {
        return bdObjectTypeEntityRepository.count();
    }

    public void delete(Iterable<? extends ParentForAll> iterable) {
        bdObjectTypeEntityRepository.delete((Iterable<? extends VBdObjectTypeEntity>) iterable);
    }

    //----------------------------------------induvidual---------------------------------------
    public List<VBdObjectTypeEntity> findByCodeS(String... codeS) {
        String s = "select a2.* " +
                "from " + SCHEMA + ".V_BD_OBJECT_TYPE a2 where a2.code in (";
        for (int i = 0; i < codeS.length; i++) {
            if (i != 0) {
                s = s + ",";
            }
            s = s + "'" + codeS[i] + "'";
        }
        s = s + ") CONNECT BY PRIOR a2.ID=a2.PARENT start with a2.PARENT is null ORDER SIBLINGS by a2.id desc";

        List<VBdObjectTypeEntity> list = em.createNativeQuery(s, VBdObjectTypeEntity.class).getResultList();

        return list;
    }

    public List<VBdObjectTypeEntity> findByCodeIn(String... code) {
        return bdObjectTypeEntityRepository.findByCodeIn(code);
    }

    @Transactional
    public VBdObjectTypeEntity findByCode(String code) {
        List<VBdObjectTypeEntity> typeEntities = bdObjectTypeEntityRepository.findByCode(code);
        if (typeEntities.size() == 0) {
            throw new NoDataFoundException();
        } else if (typeEntities.size() > 1) {
            throw new TooManyRowsException();
        }
        return typeEntities.get(0);
    }

    public EntityManager getEntityManager() {
        return em;
    }


    public <S extends ParentForAll> S save(S s) {
        return (S) bdObjectTypeEntityRepository.save((VBdObjectTypeEntity) s);
    }
}
