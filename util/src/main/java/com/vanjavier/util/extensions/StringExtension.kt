package com.vanjavier.util.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Convert String to Date and get the age from birthdate.
 */
fun String.getAge(): Int {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = formatter.parse(this)

    date?.let {
        val calendar = Calendar.getInstance()
        calendar.time = Date(it.time - Date().time)
        return 1970 - (calendar.get(Calendar.YEAR) + 1)
    }

    return 0
}