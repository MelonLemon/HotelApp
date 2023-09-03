package com.example.presentation.room.util

import com.example.domain.model.Room
import com.example.domain.model.Rooms


data class RoomsState(
    val hotelName: String = "",
    val listOfRooms: Rooms = Rooms(),
    val isLoading: Boolean = true
)
