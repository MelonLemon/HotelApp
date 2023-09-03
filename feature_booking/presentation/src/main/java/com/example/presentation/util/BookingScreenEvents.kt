package com.example.presentation.util

sealed class BookingScreenEvents{
    object AddNewTourist: BookingScreenEvents()
    data class OnPhoneChange(val phone:String): BookingScreenEvents()
    data class OnEmailChange(val email:String): BookingScreenEvents()
    data class OnNameChange(val index: Int, val name:String): BookingScreenEvents()
    data class OnSurnameChange(val index: Int, val surname:String): BookingScreenEvents()
    data class OnBirthdayChange(val index: Int, val birthday:String): BookingScreenEvents()
    data class OnCitizenshipChange(val index: Int, val citizenship:String): BookingScreenEvents()
    data class OnIntPassportChange(val index: Int, val intPassport:String): BookingScreenEvents()
    data class OnPassportDueDateChange(val index: Int, val passportDueDate:String): BookingScreenEvents()
    object OnPaymentButtonClick: BookingScreenEvents()
}
