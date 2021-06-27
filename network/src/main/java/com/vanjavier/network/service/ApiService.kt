package com.vanjavier.network.service

import com.vanjavier.common.entities.Person
import com.vanjavier.common.payload.PersonsPayload
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * REST API access points
 */
interface ApiService {

    /**
     * Get the list of persons.
     */
    @POST("q")
    suspend fun getPersons(
        @Body payload: PersonsPayload
    ): List<Person>
}