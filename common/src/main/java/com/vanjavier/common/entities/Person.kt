package com.vanjavier.common.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "person")
data class Person(

    @PrimaryKey
    @SerializedName("id") val id: String = "personNickname",
    @SerializedName("first_name") val firstName: String? = "nameFirst",
    @SerializedName("last_name") val lastName: String? = "nameLast",
    @SerializedName("email") val email: String? = "internetEmail",
    @SerializedName("gender") val gender: String? = "personGender",
    @SerializedName("mobile") val mobile: String? = "phoneMobile",
    @SerializedName("dateDOB") val birthDay: String? = "dateDOB",
    @SerializedName("_repeat") val repeat: Int? = 10,
    @SerializedName("address") val address: Address? = Address(),
    @SerializedName("contact_person") val contactPerson: ContactPerson? = ContactPerson()
) : Serializable