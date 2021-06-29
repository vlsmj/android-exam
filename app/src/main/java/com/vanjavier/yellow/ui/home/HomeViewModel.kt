package com.vanjavier.yellow.ui.home

import androidx.lifecycle.*
import com.vanjavier.common.payload.PersonsPayload
import com.vanjavier.repository.fragment.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for home screen.
 *
 * @param repository The repository for this ViewModel that handles fetching of data.
 * @param state This will retain the data if the application decided to end the process
 * to save memory.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    state: SavedStateHandle
) : ViewModel() {

    /**
     * Instantiate a LiveData variable to be passed on to the repository.
     */
    private val personsPayload = state.getLiveData(PERSONS_PAYLOAD, PersonsPayload())

    val persons = personsPayload.switchMap {
        repository.getPersons(it).asLiveData()
    }

    /**
     * Set the default payload values.
     */
    fun getPersons() {
        this.personsPayload.value = PersonsPayload()
    }

    companion object {
        private const val PERSONS_PAYLOAD = "PERSONS_PAYLOAD"
    }
}