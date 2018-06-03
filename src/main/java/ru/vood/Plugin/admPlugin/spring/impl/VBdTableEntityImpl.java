package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.ParentForAll;
import ru.vood.Plugin.admPlugin.spring.entity.VBdTableEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdTableEntityService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdTableEntityRepository;

import java.math.BigDecimal;

@Service("jpaVBdTableEntityService")
@Repository
@Transactional
public class VBdTableEntityImpl extends VBdObjectEntityImpl/*ParentForAllImpl*/ implements VBdTableEntityService {

    @Autowired
    private VBdTableEntityRepository bdTableEntityRepository;

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

//------------------------------



}
