package com.example.domain.use_cases

import com.example.domain.model.Rooms
import com.example.domain.repository.HotelInfoRepository
import javax.inject.Inject

class GetRooms @Inject constructor(
    private val repository: HotelInfoRepository
) {
    suspend operator fun invoke(): Rooms{
        return repository.getRooms()
    }
}