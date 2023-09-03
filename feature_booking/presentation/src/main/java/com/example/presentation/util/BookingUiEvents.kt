package com.example.presentation.util

sealed class BookingUiEvents{
    object ShowErrorMsgEmail: BookingUiEvents()
    object ShowErrorMsgPhone: BookingUiEvents()
    object ShowErrorMsgTourist: BookingUiEvents()
    object ToPaymentScreen: BookingUiEvents()
}
