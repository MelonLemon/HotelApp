package com.example.presentation.util

import com.example.common.util.isValidDate

fun isValidTouristsInfo(tourists: List<TouristInfo>): Boolean {
    tourists.forEach { tourist ->
        if(tourist.name.isBlank() || tourist.surname.isBlank() ||
            tourist.birthDate.isBlank() || tourist.citizenship.isBlank() ||
            tourist.intPassport.isBlank() || tourist.passportDueDate.isBlank()) {
            return false
        }
        if(!isValidDate(tourist.birthDate) || !isValidDate(tourist.passportDueDate)){
            return false
        }
    }
    return true
}