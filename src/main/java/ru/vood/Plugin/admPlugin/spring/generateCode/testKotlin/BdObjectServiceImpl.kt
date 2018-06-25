package ru.vood.Plugin.admPlugin.spring.generateCode.testKotlin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*
import javax.persistence.EntityManager

@Service
@Repository
@Transactional
open class BdObjectServiceImpl : BdObjectService {
    @Autowired
    private lateinit var em: EntityManager

    @Autowired
    private lateinit var bdObjectRepository: BdObjectRepository

    /*override fun findOne(id: BigDecimal): BDObject {
        val query = em.createQuery("select a2 from BDObject a2 " +
                //"  join fetch a2.typeObject a1 " +
                " where a2.id = :idd " +
                "")
                .setParameter("idd", id)
        val list1 = query.getResultList() as ArrayList<BDObject>
        return list1[0]

    }*/

    override fun findOne(id: BigDecimal): BDObject {
        val query = em.createQuery("select a2 from BDObject a2 " +
                //"  join fetch a2.typeObject a1 " +
                " where a2.id = :idd " +
                "")
                .setParameter("idd", id)
        val list1 = query.getResultList() as ArrayList<BDObject>
        return list1[0]
    }

    override fun findByCodeAndParenCode(code: String, parentCode: String): BDObject {
        val query = em.createQuery("select a1 from BDObject a1" +
                " left join VBdObjectEntity a2 on a1.parent = a2 " + // and a2.code = :parentCode" +

                " join fetch a1.parent  " +
                " where a1.code = :code " +
                " and (a2.code = :parentCode or (:parentCode is null and  a2 is null ))" +
                //" order by a2.id " +
                " ")
                .setParameter("code", code)
                .setParameter("parentCode", parentCode)
        val list1 = query.getResultList() as ArrayList<BDObject>
        return list1[0]
    }

    override fun save(entity: BDObject): BDObject {
        return bdObjectRepository.save(entity)
    }
}