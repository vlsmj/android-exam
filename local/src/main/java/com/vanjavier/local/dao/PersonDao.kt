package com.vanjavier.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vanjavier.common.entities.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    /**
     * Get all the persons currently saved in the database.
     */
    @Query("SELECT * FROM person")
    fun getPersons(): Flow<List<Person>>

    /**
     * Insert new set of persons. Replace if conflicts occur.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersons(persons: List<Person>)

    /**
     * Delete all the persons in the database.
     */
    @Query("DELETE FROM person")
    suspend fun deletePersons()
}