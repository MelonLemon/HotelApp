package com.example.data.repository

import com.example.data.data_source.HotelApiService
import com.example.domain.model.HotelInfo
import com.example.domain.model.Rooms
import com.example.domain.repository.BookingRepository
import com.example.domain.repository.HotelInfoRepository
import javax.inject.Inject

class HotelInfoRepositoryImpl @Inject constructor(
    private val hotelApiService: HotelApiService
): HotelInfoRepository {
    override suspend fun getHotelInfo(): HotelInfo {
        return hotelApiService.getHotelInfo()
    }

    override suspend fun getRooms(): Rooms {
        return hotelApiService.getRooms()
    }

}
