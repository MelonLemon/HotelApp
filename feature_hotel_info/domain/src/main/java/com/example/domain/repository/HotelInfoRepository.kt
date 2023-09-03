package com.example.domain.repository

import com.example.domain.model.HotelInfo
import com.example.domain.model.Rooms

interface HotelInfoRepository {
    suspend fun getHotelInfo(): HotelInfo
    suspend fun getRooms(): Rooms
}