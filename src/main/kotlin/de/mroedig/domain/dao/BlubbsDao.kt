package de.mroedig.domain.dao

import de.mroedig.domain.entity.Blubbs
import org.springframework.data.repository.Repository

interface BlubbsDao : Repository<Blubbs?, Long?> {
    fun persist(b: Blubbs?)
}