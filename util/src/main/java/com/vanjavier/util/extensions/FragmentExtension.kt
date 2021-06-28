package com.vanjavier.util.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Replace the action bar title.
 *
 * @param title The title to be used in the action bar.
 */

fun Fragment.setActionBarTitle(title: String) {
    activity?.let {
        if (it is AppCompatActivity) it.supportActionBar?.title = title
    }
}