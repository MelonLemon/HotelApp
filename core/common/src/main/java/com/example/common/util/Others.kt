package com.example.common.util


import android.icu.text.MessageFormat
import java.util.Locale

fun Int.toWords(language: String, country: String): String {
    val listOrdinal = listOf("Первый", "Второй", "Третий", "Четвертый",
        "Пятый", "Шестой", "Седьмой", "Восьмой", "Девятый", "Десятый")
    if(this<11){
        return listOrdinal[this-1]
    } else {
        val formatter = MessageFormat(
            "{0, ordinal}",
            Locale(language, country)
        )
        return formatter.format(arrayOf(this))
    }

}



