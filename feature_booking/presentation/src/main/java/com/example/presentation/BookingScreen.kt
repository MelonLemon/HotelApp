package com.example.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.common.components.BackToNavigationRow
import com.example.common.components.BasicHotelInfo
import com.example.common.components.PrimaryButton
import com.example.common.util.toWords
import com.example.presentation.components.AddCustomRow
import com.example.presentation.components.BookingHotelInfo
import com.example.presentation.components.TouristInfoBlock
import com.example.presentation.components.PurchaseInfoBlock
import com.example.presentation.components.PurchaserInfo
import com.example.presentation.util.BookingScreenEvents
import com.example.presentation.util.BookingState
import com.example.presentation.util.BookingUiEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import java.util.Locale

@Composable
fun BookingScreen(
    bookingState: BookingState,
    backToRoomsScreen: () -> Unit,
    toPaymentScreen: () -> Unit,
    bookingEvents: (BookingScreenEvents) -> Unit,
    bookingUiEvents: SharedFlow<BookingUiEvents>
) {

    //FOR LAUNCHED EFFECT
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    var fillTouristListCheck by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true){
        bookingUiEvents.collectLatest { event ->
            when(event) {
                is BookingUiEvents.ShowErrorMsgPhone -> {
                    snackbarHostState.showSnackbar(
                        message = context.resources.getString(R.string.fill_num_phone),
                        actionLabel = null
                    )
                }
                is BookingUiEvents.ShowErrorMsgEmail -> {
                    snackbarHostState.showSnackbar(
                        message = context.resources.getString(R.string.fill_email),
                        actionLabel = null
                    )
                }
                is BookingUiEvents.ShowErrorMsgTourist -> {
                    snackbarHostState.showSnackbar(
                        message = context.resources.getString(R.string.fill_tourist),
                        actionLabel = null
                    )
                    fillTouristListCheck = true
                }
                is BookingUiEvents.ToPaymentScreen -> {
                    toPaymentScreen()
                }
            }
        }
    }

    //FOR LAUNCHED EFFECT
    var enableCheckMail by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = bookingState.purchaserInfo.email) {
        if(enableCheckMail)  enableCheckMail = false
        delay(500)
        enableCheckMail = true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        bottomBar = {
            PrimaryButton(
                text = stringResource(R.string.booking),
                onBtnClick = backToRoomsScreen
            )
        },
        topBar = {
            BackToNavigationRow(
                text = stringResource(R.string.pay) + " " +
                        "${String.format(Locale.FRANCE, "%,d", (bookingState.bookingInfo.tourPrice?:0) + (bookingState.bookingInfo.fuelCharge?:0) +(bookingState.bookingInfo.serviceCharge?:0))} ₽",
                onBtnClick = {
                    bookingEvents(BookingScreenEvents.OnPaymentButtonClick)
                }
            )
        }

    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            item {
                BasicHotelInfo(
                    rankingText = "${bookingState.bookingInfo.horating} ${bookingState.bookingInfo.ratingName}",
                    name = bookingState.bookingInfo.hotelName?:"",
                    place= bookingState.bookingInfo.hotelAdress?:"",
                )
            }
            item {
                BookingHotelInfo(
                    flight_from=bookingState.bookingInfo.departure?:"",
                    country_city=bookingState.bookingInfo.arrivalCountry?:"",
                    dates="${bookingState.bookingInfo.tourDateStart} - ${bookingState.bookingInfo.tourDateStop}",
                    number_of_nights="${bookingState.bookingInfo.numberOfNights} " + stringResource(R.string.nights),
                    hotel=bookingState.bookingInfo.hotelName?:"",
                    room_type=bookingState.bookingInfo.room?:"",
                    meal=bookingState.bookingInfo.nutrition?:""
                )
            }
            item {
                PurchaserInfo(
                    phoneString=bookingState.purchaserInfo.phone,
                    mail=bookingState.purchaserInfo.email,
                    onPhoneChange={phone ->
                        bookingEvents(BookingScreenEvents.OnPhoneChange(phone))
                    },
                    onMailChange={email ->
                        bookingEvents(BookingScreenEvents.OnEmailChange(email))
                    },
                    enableCheckMail = enableCheckMail
                )
            }

            itemsIndexed(bookingState.listOfTourist){ index, item ->
                TouristInfoBlock(
                    title= (index+1).toWords("ru","RU") + " " + stringResource(R.string.tourist),
                    nameString=item.name,
                    onNameChange={ name ->
                        bookingEvents(BookingScreenEvents.OnNameChange(
                            index=index, name=name
                        ))
                    },
                    surnameString=item.citizenship,
                    onSurnameChange={ surname ->
                        bookingEvents(BookingScreenEvents.OnSurnameChange(
                            index=index, surname=surname
                        ))

                    },
                    dateString=item.birthDate,
                    onDateChange={ birthday ->
                        bookingEvents(BookingScreenEvents.OnBirthdayChange(
                            index=index, birthday=birthday
                        ))
                    },
                    citizenshipString=item.citizenship,
                    onCitizenshipChange={ citizenship ->
                        bookingEvents(BookingScreenEvents.OnCitizenshipChange(
                            index=index, citizenship=citizenship
                        ))
                    },
                    intPassportString=item.intPassport,
                    onIntPassportChange={ intPassport ->
                        bookingEvents(BookingScreenEvents.OnIntPassportChange(
                            index=index, intPassport=intPassport
                        ))

                    },
                    passportDueString=item.passportDueDate,
                    onPassportDueChange={ passportDueDate ->
                        bookingEvents(BookingScreenEvents.OnPassportDueDateChange(
                            index=index, passportDueDate=passportDueDate
                        ))
                    },
                    enableShowError = fillTouristListCheck
                )
            }
            item {
                AddCustomRow(
                   onAddBtnClick = {
                       bookingEvents(BookingScreenEvents.AddNewTourist)
                   }
                )
            }
            item{
                PurchaseInfoBlock(
                    tour=bookingState.bookingInfo.tourPrice?:0,
                    fuel=bookingState.bookingInfo.fuelCharge?:0,
                    service=bookingState.bookingInfo.serviceCharge?:0
                )
            }
        }
    }
}