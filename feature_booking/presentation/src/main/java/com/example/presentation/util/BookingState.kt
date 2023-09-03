package com.example.presentation.util

import com.example.domain.model.BookingInfo

data class BookingState(
    val bookingInfo: BookingInfo = BookingInfo(),
    val isLoading: Boolean = true,
    val purchaserInfo: PurchaserInfo = PurchaserInfo(),
    val listOfTourist: List<TouristInfo> = listOf(TouristInfo())
)
