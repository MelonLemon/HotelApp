package com.example.domain.di

import com.example.domain.repository.HotelInfoRepository
import com.example.domain.use_cases.GetHotelInfo
import com.example.domain.use_cases.GetRooms
import com.example.domain.use_cases.HotelInfoUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HotelInfoModule {

    @Provides
    @Singleton
    fun provideHotelInfoUseCases(repository: HotelInfoRepository): HotelInfoUseCases {
        return HotelInfoUseCases(
            getHotelInfo = GetHotelInfo(repository),
            getRooms = GetRooms(repository)
        )
    }

}