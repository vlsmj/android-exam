package com.vanjavier.common.payload

import com.vanjavier.common.entities.Person

data class PersonsPayload(
    val token: String? = "tTCg5emLIqSIh5BzxKl6ag",
    val data: Person? = Person()
)