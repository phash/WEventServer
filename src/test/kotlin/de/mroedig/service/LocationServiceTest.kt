package de.mroedig.service

import org.junit.jupiter.api.BeforeEach
import javax.inject.Inject

@Inject
var eventsLocationService: EventsLocationService? = null

@BeforeEach
fun init() {

}

fun `test persist and retrieve eventsLocations`() {
    //   val loc1 = EventsLocation("Munich")
    //   eventsLocationService.persist(loc1)

}