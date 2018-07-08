package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdObjectEntity;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.except.CoreExeption;
import ru.vood.Plugin.admPlugin.spring.intf.CommonFunctionService;
import ru.vood.Plugin.admPlugin.spring.intf.VBdTableEntityService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdTableEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service//("jpaVBdTableEntityService")
@Repository
@Transactional
public class VBdTableEntityImpl /*extends VBdObjectEntityImpl/*ParentForAllImpl*/ implements VBdTableEntityService {

    @Autowired
    private VBdTableEntityRepository bdTableEntityRepository;

    @Autowired
    private CommonFunctionService commonFunction;

    @Autowired
    protected EntityManager em;

    @Override
    public VBdTableEntity save(VBdTableEntity entity) {
        return bdTableEntityRepository.save(entity);
    }

    @Override
    public void delete(VBdTableEntity entity) {
        bdTableEntityRepository.delete(entity);
    }

    @Override
    public List<VBdTableEntity> findByTypeObjectCodeIn(String... codeS) {
        return bdTableEntityRepository.findByTypeObjectCodeIn(codeS);
    }

    @Override
    public List<VBdTableEntity> findByParent(VBdObjectEntity parent) {
        return bdTableEntityRepository.findByParent(parent);
    }

    @Override
    public VBdTableEntity findByCode(String code) throws CoreExeption {
        Query query = em.createQuery("select a1 from VBdTableEntity a1 " +
                //"  join fetch a1.typeObject a2 " + //" on a1.code in :codeTypeS  " +
                " join fetch a1.parent " +
                " join fetch a1.typeObject " +
//                " join fetch a1.parent " +
                " where a1.code in :codeTypeS " +
                //        " order by a2.id "
                "")
                .setParameter("codeTypeS", code);


        //List list = bdTableEntityRepository.findByCode(code);
        List list1 = (ArrayList<VBdTableEntity>) query.getResultList();
        commonFunction.checkOn(list1);
        return (VBdTableEntity) list1.get(0);
    }

    /*
    public ParentForAll findOne(BigDecimal bigDecimal) {
        return bdTableEntityRepository.findOne(bigDecimal);
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


    public VBdTableEntity save(ParentForAll parentForAll) {
        return bdTableEntityRepository.save((VBdTableEntity) parentForAll);
    }

    public void delete(ParentForAll parentForAll) {
        bdTableEntityRepository.delete((VBdTableEntity) parentForAll);
    }
*/
//------------------------------


}
