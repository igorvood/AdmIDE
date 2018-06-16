package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.intf.CommonFunctionService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdColomnsEntityService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdColomnsEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service//("jpaVBdColomnsEntityService")
@Repository
@Transactional

public class VBdColomnsEntityImpl /*extends VBdObjectEntityImpl /*ParentForAllImpl*/ implements VBdColomnsEntityService {
    @Autowired
    protected EntityManager em;
    @Autowired
    private VBdColomnsEntityRepository bdColomnsEntityRepository;

    @Autowired
    private CommonFunctionService commonFunctionService;

    @Override
    public List<VBdColomnsEntity> findByParent(VBdTableEntity parent) {
  /*      Query query = em.createQuery("select a2 from VBdObjectEntity a2 " +
                "  join fetch a2.typeObject a1 " +
                "  join fetch a2.parent a3  " +
//                "  inner join VBdColomnsEntity a4 on a2.id = a4.id " +
//                "  join fetch a4.typeValue a5  " +

                //"  join fetch a3.typeObject a4 " +
                " where a2.parent = :parent " +
                " order by a2.id ")
                .setParameter("parent", parent);*/

        Query query = em.createQuery("select a2 from VBdColomnsEntity a2 " +
                "  join fetch a2.typeObject a1 " +
                "  join fetch a2.parent a3  " +
                "  join fetch a2.typeValue a5 " +
                "  join fetch a5.typeObject a6 " +
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

    @Override
    public VBdColomnsEntity findColomn(VBdTableEntity parent, String code) {
        Query query = em.createQuery("select a1 from VBdColomnsEntity a1 " +
                //"  join fetch a2.typeObject a1 " +
                "  join fetch a1.parent a3  " +
                //"  join fetch a2.typeValue a5 " +
                //"  join fetch a5.typeObject a6 " +
                " where a1.parent = :parent " +
                " and a1.code = :code" +
                " ")
                .setParameter("parent", parent)
                .setParameter("code", code);

        List list = query.getResultList();
        commonFunctionService.checkOn(list);
        return (VBdColomnsEntity) list.get(0);
    }
}

