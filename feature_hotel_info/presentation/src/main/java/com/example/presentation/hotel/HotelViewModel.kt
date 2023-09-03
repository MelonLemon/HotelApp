package com.example.presentation.hotel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.HotelInfoUseCases
import com.example.presentation.hotel.util.HotelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val useCases: HotelInfoUseCases
): ViewModel() {
    private val _hotelState = MutableStateFlow((HotelState()))
    val hotelState  = _hotelState.asStateFlow()

    init {
        viewModelScope.launch {
            val hotelInfo = useCases.getHotelInfo()
            _hotelState.value = hotelState.value.copy(
                hotelInfo = hotelInfo,
                isLoading = false
            )
        }
    }
}