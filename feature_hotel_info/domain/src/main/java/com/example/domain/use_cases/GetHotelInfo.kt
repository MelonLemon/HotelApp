package com.example.domain.use_cases

import com.example.domain.model.HotelInfo
import com.example.domain.repository.HotelInfoRepository
import javax.inject.Inject

class GetHotelInfo  @Inject constructor(
    private val repository: HotelInfoRepository
) {
    suspend operator fun invoke():HotelInfo{
        return repository.getHotelInfo()
    }
}