package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexedColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.except.CoreExeption;
import ru.vood.Plugin.admPlugin.spring.intf.CommonFunctionService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdIndexEntityService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdIndexEntityRepository;
import ru.vood.Plugin.admPlugin.spring.repository.VBdIndexedColomnsEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Repository
@Transactional
public class VBdIndexEntityImp implements VBdIndexEntityService {

    @Autowired
    protected VBdIndexEntityRepository entityTestRepository;
    @Autowired
    protected VBdIndexedColomnsEntityRepository colomnsEntityRepository;
    @Autowired
    protected EntityManager em;
    @Autowired
    private CommonFunctionService commonFunctionService;

    @Override
    public VBdIndexEntity save(VBdIndexEntity entity) {
        if (entity.getColomnsEntities() != null) {
            entity.getColomnsEntities().stream().forEach(col -> colomnsEntityRepository.save(col));
        }
        return entityTestRepository.save(entity);
    }

    @Override
    public void delete(VBdIndexEntity entity) {
        if (entity.getColomnsEntities() != null) {
            entity.getColomnsEntities().stream().forEach(col -> colomnsEntityRepository.delete(col));
        }
        entityTestRepository.delete(entity);
    }

    @Override
    public List<VBdIndexEntity> findByParent(VBdObjectEntity parent) {
        Query query = em.createQuery("select a1 from VBdIndexEntity a1 " +
                "  join fetch a1.typeObject a2 " +
                "  join fetch a1.parent a3  " +
//                "  join fetch a1.typeValue a5 " +
                "  join fetch a1.typeObject a6 " +
                // "  left join VBdIndexedColomnsEntity a7 on  a1.columns= a7.collectionId " +
                //"  join fetch a1.colomnsEntities a7 " +
                " where a1.parent = :parent " +
                //" order by a2.id " +
                "")
                .setParameter("parent", parent);
        List<VBdIndexEntity> listVBdIndexEntity = query.getResultList();
        List<BigDecimal> bigDecimals = listVBdIndexEntity.stream().map(q -> q.getColumns()).collect(Collectors.toList());
        List<VBdIndexedColomnsEntity> indexedColomnsEntities = colomnsEntityRepository.findByCollectionIdIn(bigDecimals);
        for (VBdIndexEntity li : listVBdIndexEntity) {
            for (VBdIndexedColomnsEntity col : indexedColomnsEntities) {
                if (li.getColumns().equals(col.getCollectionId())) {
                    li.addColomn(col);
                }
            }
        }
        return listVBdIndexEntity;
    }

    @Override
    public VBdIndexEntity findByCode(String code) throws CoreExeption {
        List<VBdIndexEntity> indexEntities = entityTestRepository.findByCode(code);
        return (VBdIndexEntity) commonFunctionService.checkOn(indexEntities);
    }
}
