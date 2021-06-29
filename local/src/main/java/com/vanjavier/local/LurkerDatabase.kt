package com.vanjavier.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vanjavier.common.converter.AddressConverter
import com.vanjavier.common.converter.ContactPersonConverter
import com.vanjavier.common.entities.Person
import com.vanjavier.local.dao.PersonDao

@Database(entities = [Person::class], version = 1, exportSchema = false)
@TypeConverters(AddressConverter::class, ContactPersonConverter::class)
abstract class YellowDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao
}