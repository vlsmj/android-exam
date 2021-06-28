package com.vanjavier.network.service

import com.vanjavier.common.entities.Person
import kotlinx.coroutines.flow.*

/**
 * The service to fetch data from the API and Room.
 *
 * Sequence as follows:
 * 1. Get the data saved in Room database.
 * 2. Fetch the data from the API.
 * 3. Replace the data in the database with the new data
 *    fetched from the API.
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    // Force prevent remote fetching if local persistence already has set of persons.
    val isPreventRemoteFetch = data is List<*> && data.isNotEmpty()

    val flow = if (shouldFetch(data) && !isPreventRemoteFetch) {
        emit(Resource.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}