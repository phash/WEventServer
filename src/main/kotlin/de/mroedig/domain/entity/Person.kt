package de.mroedig.domain.entity

import javax.persistence.*

@Entity
internal data class Person @JvmOverloads constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        @Column(nullable = false)
        val name: String,
        @Column(nullable = true)
        val email: String? = null

)