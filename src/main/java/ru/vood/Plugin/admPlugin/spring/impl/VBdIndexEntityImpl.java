package ru.vood.Plugin.admPlugin.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vood.Plugin.admPlugin.spring.intf.VBdIndexEntityService;
import ru.vood.Plugin.admPlugin.spring.repository.VBdIndexEntityRepository;

@Service("jpaVBdIndexEntityService")
@Repository
@Transactional
public class VBdIndexEntityImpl extends /*ParentForAllImpl*/VBdObjectEntityImpl implements VBdIndexEntityService {

    @Autowired
    private VBdIndexEntityRepository vBdIndexEntityRepository;


}

