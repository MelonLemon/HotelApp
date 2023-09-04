package com.example.domain.use_cases

import com.example.domain.model.BookingInfo
import com.example.domain.repository.BookingRepository
import javax.inject.Inject

class GetBookingInfo @Inject constructor(
    private val repository: BookingRepository
) {
    suspend operator fun invoke(): BookingInfo{

        return repository.getBookingInfo()
    }
}