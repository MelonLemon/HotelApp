package com.example.presentation.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.HotelInfoUseCases
import com.example.presentation.hotel.util.HotelState
import com.example.presentation.room.util.RoomsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val useCases: HotelInfoUseCases
): ViewModel() {

    private val _roomsState = MutableStateFlow((RoomsState(hotelName = "Steigenberger Makadi")))
    val roomsState  = _roomsState.asStateFlow()

    init {
        viewModelScope.launch {
            val rooms = useCases.getRooms()
            _roomsState.value = roomsState.value.copy(
                listOfRooms = rooms,
                isLoading = false
            )
        }
    }
}