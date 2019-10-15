package de.mroedig.domain.dao.impl

import de.mroedig.domain.dao.BlubbsDao
import de.mroedig.domain.entity.Blubbs
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class BlubbsDaoImpl : BlubbsDao {
    @PersistenceContext
    private val em: EntityManager? = null

    override fun persist(b: Blubbs?) {
        em!!.persist(b)
    }
}