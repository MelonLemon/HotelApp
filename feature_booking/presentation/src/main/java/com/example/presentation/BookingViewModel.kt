package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.util.isValidEmail
import com.example.domain.use_cases.BookingUseCases
import com.example.presentation.util.BookingScreenEvents
import com.example.presentation.util.BookingState
import com.example.presentation.util.BookingUiEvents
import com.example.presentation.util.TouristInfo
import com.example.presentation.util.isValidTouristsInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val useCases: BookingUseCases
): ViewModel() {

    private val _bookingState = MutableStateFlow((BookingState()))
    val bookingState  = _bookingState.asStateFlow()

    private val _onBookingUiEvents = MutableSharedFlow<BookingUiEvents>()
    val onBookingUiEvents  = _onBookingUiEvents.asSharedFlow()

    init {
        viewModelScope.launch {
            val bookingInfo = useCases.getBookingInfo()
            _bookingState.value = bookingState.value.copy(
                bookingInfo = bookingInfo,
                isLoading = false
            )
        }
    }

    fun bookingScreenEvents(event: BookingScreenEvents){
        when(event) {
            is BookingScreenEvents.AddNewTourist -> {
                val newList = bookingState.value.listOfTourist.toMutableList()
                newList.add(TouristInfo())
                _bookingState.value = bookingState.value.copy(
                    listOfTourist = newList
                )
            }
            is BookingScreenEvents.OnPhoneChange -> {
                _bookingState.value = bookingState.value.copy(
                    purchaserInfo = bookingState.value.purchaserInfo.copy(
                        phone = event.phone
                    )
                )
            }
            is BookingScreenEvents.OnEmailChange -> {

                _bookingState.value = bookingState.value.copy(
                    purchaserInfo = bookingState.value.purchaserInfo.copy(
                        email = event.email
                    )
                )

            }
            is BookingScreenEvents.OnNameChange -> {
                val newList = bookingState.value.listOfTourist.toMutableList()
                newList[event.index] = newList[event.index].copy(
                    name = event.name
                )
                _bookingState.value = bookingState.value.copy(
                    listOfTourist = newList
                )
            }
            is BookingScreenEvents.OnSurnameChange -> {
                val newList = bookingState.value.listOfTourist.toMutableList()
                newList[event.index] = newList[event.index].copy(
                    surname = event.surname
                )
                _bookingState.value = bookingState.value.copy(
                    listOfTourist = newList
                )
            }
            is BookingScreenEvents.OnBirthdayChange -> {
                val newList = bookingState.value.listOfTourist.toMutableList()
                newList[event.index] = newList[event.index].copy(
                    birthDate = event.birthday
                )
                _bookingState.value = bookingState.value.copy(
                    listOfTourist = newList
                )
            }
            is BookingScreenEvents.OnCitizenshipChange -> {
                val newList = bookingState.value.listOfTourist.toMutableList()
                newList[event.index] = newList[event.index].copy(
                    citizenship = event.citizenship
                )
                _bookingState.value = bookingState.value.copy(
                    listOfTourist = newList
                )
            }
            is BookingScreenEvents.OnIntPassportChange -> {
                val newList = bookingState.value.listOfTourist.toMutableList()
                newList[event.index] = newList[event.index].copy(
                    intPassport = event.intPassport
                )
                _bookingState.value = bookingState.value.copy(
                    listOfTourist = newList
                )
            }
            is BookingScreenEvents.OnPassportDueDateChange -> {
                val newList = bookingState.value.listOfTourist.toMutableList()
                newList[event.index] = newList[event.index].copy(
                    passportDueDate = event.passportDueDate
                )
                _bookingState.value = bookingState.value.copy(
                    listOfTourist = newList
                )
            }
            is BookingScreenEvents.OnPaymentButtonClick -> {
                if(bookingState.value.purchaserInfo.phone.isBlank() ||
                    (bookingState.value.purchaserInfo.phone.length < 10)){
                    viewModelScope.launch {
                        _onBookingUiEvents.emit(BookingUiEvents.ShowErrorMsgPhone)
                    }

                } else if (!isValidEmail(bookingState.value.purchaserInfo.email)) {
                    viewModelScope.launch {
                        _onBookingUiEvents.emit(BookingUiEvents.ShowErrorMsgEmail)
                    }
                } else if (!isValidTouristsInfo(bookingState.value.listOfTourist)) {
                    viewModelScope.launch {
                        _onBookingUiEvents.emit(BookingUiEvents.ShowErrorMsgTourist)
                    }
                } else {
                    viewModelScope.launch {
                        _onBookingUiEvents.emit(BookingUiEvents.ToPaymentScreen)
                    }
                }
            }
        }
    }
}