package com.vanjavier.common.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vanjavier.common.entities.ContactPerson

class ContactPersonConverter {
    @TypeConverter
    fun fromString(value: String): ContactPerson =
        Gson().fromJson(value, object : TypeToken<ContactPerson>() {}.type)

    @TypeConverter
    fun fromModel(value: ContactPerson): String = Gson().toJson(value)
}