package com.example.data.repository

import com.example.data.data_source.HotelApiService
import com.example.domain.model.BookingInfo
import com.example.domain.repository.BookingRepository
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor(
    private val hotelApiService: HotelApiService
): BookingRepository {
    override suspend fun getBookingInfo(): BookingInfo {
        return hotelApiService.getBookingInfo()
    }

}