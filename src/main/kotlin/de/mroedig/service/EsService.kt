package de.mroedig.service

import de.mroedig.domain.entity.Blubbs

interface EsService {
    fun name(): String?
    fun enterBlubbs(blubbs: Blubbs?)
}