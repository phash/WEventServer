package de.mroedig.service

import org.junit.jupiter.api.BeforeEach
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertNotNull

@SpringJUnitConfig(TestConfig::class)
@RunWith(JUnitPlatform::class)
class TestLocationService {
    @Inject
    var eventsLocationService: EventsLocationService? = null

    @BeforeEach
    fun init() {

    }

    /*fun `test persist and retrieve eventsLocations`(){
        val loc1 = EventsLocation("Munich")
        eventsLocationService.persist(loc1)

    }*/

    @Test
    fun `test if spring is available`() {
        assertNotNull(eventsLocationService)
    }
}