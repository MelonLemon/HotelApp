package com.example.common.util

import java.text.ParseException
import java.text.SimpleDateFormat

fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}

fun isValidDate(dateString: String): Boolean {
    if(dateString.isBlank() || dateString.length<8){
        return false
    }
    val formatter = SimpleDateFormat("ddMMyyyy")
    try {
        formatter.parse(dateString)
    } catch (e: ParseException){
        return false
    }
    return true
}

