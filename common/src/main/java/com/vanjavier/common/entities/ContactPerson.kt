package com.vanjavier.common.entities

import com.google.gson.annotations.SerializedName

data class ContactPerson(
    @SerializedName("first_name") val firstName: String? = "nameFirst",
    @SerializedName("last_name") val lastName: String? = "nameLast",
    @SerializedName("mobile") val mobile: String? = "phoneMobile"
)