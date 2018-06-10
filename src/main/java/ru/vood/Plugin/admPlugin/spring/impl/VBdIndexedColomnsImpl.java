package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.entity.VBdIndexedColomnsEntity;
import ru.vood.Plugin.admPlugin.spring.intf.VBdIndexedColomnsService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdIndexedColomnsRepository;

import java.math.BigDecimal;
import java.util.List;

@Service("jpaVBdIndexedColomnsService")
@Repository
@Transactional
public class VBdIndexedColomnsImpl implements VBdIndexedColomnsService {

    @Autowired
    private VBdIndexedColomnsRepository bdColomnsEntityRepository;

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
}