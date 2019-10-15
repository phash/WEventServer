package de.mroedig.service.impl

import de.mroedig.domain.dao.BlubbsDao
import de.mroedig.domain.entity.Blubbs
import de.mroedig.service.EsService
import org.springframework.transaction.annotation.Transactional
import javax.inject.Inject
import javax.inject.Named

@Named
open class EsServiceImpl : EsService {
    @Inject
    private var blubbsDao: BlubbsDao? = null

    override fun name(): String? {
        return "Hello World from Spring"
    }

    @Transactional
    override fun enterBlubbs(blubbs: Blubbs?) {
        blubbsDao!!.persist(blubbs)
    }
}