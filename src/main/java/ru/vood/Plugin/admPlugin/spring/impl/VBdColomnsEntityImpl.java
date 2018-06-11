package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdColomnsEntityService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdColomnsEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service("jpaVBdColomnsEntityService")
@Repository
@Transactional

public class VBdColomnsEntityImpl /*extends VBdObjectEntityImpl /*ParentForAllImpl*/ implements VBdColomnsEntityService {
    @Autowired
    protected EntityManager em;
    @Autowired
    private VBdColomnsEntityRepository bdColomnsEntityRepository;

    @Override
    public List<VBdColomnsEntity> findByParent(VBdTableEntity parent) {
        Query query = em.createQuery("select a2 from VBdObjectEntity a2 " +
                "  join fetch a2.typeObject a1 " + //" on a1.code in :codeTypeS  " +
                "  join fetch a2.parent a3  " +
                " where a2.parent = :parent " +
                " order by a2.id ")
                .setParameter("parent", parent);
        List list = query.getResultList();
        return list;

        // return bdColomnsEntityRepository.findByParent(parent);
    }

    @Override
    public VBdColomnsEntity save(VBdColomnsEntity entity) {
        return bdColomnsEntityRepository.save(entity);
    }

    @Override
    public void delete(VBdColomnsEntity entity) {
        bdColomnsEntityRepository.delete(entity);
    }

}

