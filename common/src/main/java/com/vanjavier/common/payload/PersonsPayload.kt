package com.vanjavier.common.payload

import com.vanjavier.common.BuildConfig
import com.vanjavier.common.entities.Person

data class PersonsPayload(
    val token: String? = BuildConfig.TOKEN,
    val data: Person? = Person()
)