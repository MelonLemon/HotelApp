package com.example.common.util

import android.icu.text.MessageFormat
import java.util.Locale

fun Int.toWords(language: String, country: String): String {
    val formatter = MessageFormat(
        "{0,spellout,currency}",
        Locale(language, country)
    )
    return formatter.format(arrayOf(this))
}