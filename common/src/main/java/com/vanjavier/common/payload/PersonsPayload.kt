package com.vanjavier.common.payload

import com.vanjavier.common.entities.Person

data class PersonsPayload(
    val token: String? = "Dz7W4GZlWKsJe0hGfxvVOQ",
    val data: Person? = Person()
)