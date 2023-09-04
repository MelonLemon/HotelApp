package com.example.presentation.hotel.util


import com.example.domain.model.HotelInfo

data class HotelState(
    val hotelInfo: HotelInfo = HotelInfo(),
    val isLoading: Boolean = true
)
