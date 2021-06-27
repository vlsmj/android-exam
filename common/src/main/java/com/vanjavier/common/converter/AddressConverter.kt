package com.vanjavier.common.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vanjavier.common.entities.Address

class AddressConverter {
    @TypeConverter
    fun fromString(value: String): Address =
        Gson().fromJson(value, object : TypeToken<Address>() {}.type)

    @TypeConverter
    fun fromModel(value: Address): String = Gson().toJson(value)
}