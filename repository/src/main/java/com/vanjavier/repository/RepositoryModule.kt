package com.vanjavier.repository

import com.vanjavier.local.YellowDatabase
import com.vanjavier.network.service.ApiService
import com.vanjavier.repository.fragment.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Prepare the single instance repository module components to be used
 * across the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provide the Home Fragment Repository
     *
     * @param db The instance of the Room database.
     * @param apiService The service with the API end points from
     * fakeJSON API.
     */
    @Provides
    fun providesHomeFragmentRepository(
        db: YellowDatabase,
        apiService: ApiService
    ) =
        HomeRepository(
            db,
            apiService
        )
}