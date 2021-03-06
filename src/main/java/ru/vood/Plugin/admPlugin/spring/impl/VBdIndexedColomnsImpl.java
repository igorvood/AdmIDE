package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexedColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdIndexedColomnsEntityService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdIndexedColomnsEntityRepository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@Service//("jpaVBdIndexedColomnsService")
@Repository
@Transactional
public class VBdIndexedColomnsImpl implements VBdIndexedColomnsEntityService {

    @Autowired
    protected EntityManager em;
    @Autowired
    private VBdIndexedColomnsEntityRepository bdColomnsEntityRepository;

    @Override
    public VBdIndexedColomnsEntity save(VBdIndexedColomnsEntity entity) {
        return bdColomnsEntityRepository.save(entity);
    }

    @Override
    public void delete(VBdIndexedColomnsEntity entity) {
        bdColomnsEntityRepository.delete(entity);
    }

    @Override
    public List<VBdIndexedColomnsEntity> findByCollectionId(BigDecimal collectionId) {
        return bdColomnsEntityRepository.findByCollectionId(collectionId);
    }

    @Override
    public List<VBdIndexedColomnsEntity> findByCollectionIdIn(List<BigDecimal> collectionId) {
        /*Query query = em.createQuery("select a1 from VBdIndexedColomnsEntity a1 " +

                " where a1.collectionId in :collectionId " +
                //" order by a2.id " +
                "")
                .setParameter("collectionId", collectionId);
        List list = query.getResultList();*/
        List list = bdColomnsEntityRepository.findByCollectionIdIn(collectionId);
        return list;
    }
}
