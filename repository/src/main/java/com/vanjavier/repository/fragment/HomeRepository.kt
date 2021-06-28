package com.vanjavier.repository.fragment

import androidx.room.withTransaction
import com.vanjavier.common.payload.PersonsPayload
import com.vanjavier.local.YellowDatabase
import com.vanjavier.network.service.ApiService
import com.vanjavier.network.service.networkBoundResource
import javax.inject.Inject

/**
 * Repository for home screen.
 *
 * @param db The instance of the Room database.
 * @param apiService The service with the API end points from
 * fakeJSON API.
 */
class HomeRepository @Inject constructor(
    private val db: YellowDatabase,
    private val apiService: ApiService
) {

    private val personDao = db.personDao()

    fun getPersons(payload: PersonsPayload) = networkBoundResource(
        query = { personDao.getPersons() },
        fetch = {
            apiService.getPersons(payload)
        }, saveFetchResult = { response ->
            if (response.isNotEmpty()) {
                db.withTransaction {
                    personDao.deletePersons()
                    personDao.insertPersons(response)
                }
            }
        }
    )
}





