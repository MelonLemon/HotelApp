package com.example.domain.di

import com.example.domain.repository.BookingRepository
import com.example.domain.use_cases.BookingUseCases
import com.example.domain.use_cases.GetBookingInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookingModule {

    @Provides
    @Singleton
    fun provideBookingUseCases(repository: BookingRepository): BookingUseCases {
        return BookingUseCases(
            getBookingInfo = GetBookingInfo(repository)
        )
    }

}