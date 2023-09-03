package com.example.domain.repository

import com.example.domain.model.BookingInfo

interface BookingRepository {
    suspend fun getBookingInfo(): BookingInfo
}