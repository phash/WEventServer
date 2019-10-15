package de.mroedig.service

import de.mroedig.EsConfig
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(EsConfig::class)
class TestConfig
