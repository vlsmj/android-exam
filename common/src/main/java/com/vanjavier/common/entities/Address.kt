package com.vanjavier.common.entities

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("building") val building: String? = "addressBuilding",
    @SerializedName("street") val street: String? = "addressFullStreet",
    @SerializedName("city") val city: String? = "addressCity",
    @SerializedName("state") val state: String? = "addressState",
    @SerializedName("country") val country: String? = "addressCountry",
    @SerializedName("zip") val zip: String? = "addressZipCode"
)