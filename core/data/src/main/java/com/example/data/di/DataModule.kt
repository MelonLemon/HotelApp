package com.example.data.di

import com.example.data.data_source.HotelApiService
import com.example.data.repository.BookingRepositoryImpl
import com.example.data.repository.HotelInfoRepositoryImpl
import com.example.domain.repository.BookingRepository
import com.example.domain.repository.HotelInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun baseUrl() = "https://run.mocky.io/v3/"

    @Provides
    @Singleton
    fun provideRetrofit(): HotelApiService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl())
            .build()
            .create(HotelApiService::class.java)



    @Provides
    @Singleton
    fun provideBookingRepository(apiService: HotelApiService): BookingRepository {
        return BookingRepositoryImpl(
            hotelApiService = apiService
        )
    }

    @Provides
    @Singleton
    fun provideHotelInfoRepository(apiService: HotelApiService): HotelInfoRepository {
        return HotelInfoRepositoryImpl(
            hotelApiService = apiService
        )
    }

}