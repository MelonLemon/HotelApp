package com.example.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.presentation.BookingScreen
import com.example.presentation.BookingViewModel
import com.example.presentation.PaymentScreen


const val BookingBlockPattern = "hotelInfo"
const val bookingRoute = "hotel_route"
const val paymentRoute = "room_route"

fun NavController.navigateToBooking() {
    this.navigate(bookingRoute)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.hotelScreen(
    backToRoomsScreen: () -> Unit,
    toPaymentScreen: () -> Unit
) {

    composable(route = bookingRoute) {
        val viewModel = hiltViewModel<BookingViewModel>()
        val bookingState by viewModel.bookingState.collectAsStateWithLifecycle()
        val bookingEvents = viewModel::bookingScreenEvents
        val bookingUiEvents by viewModel::onBookingUiEvents
        BookingScreen(
            bookingState = bookingState,
            backToRoomsScreen = backToRoomsScreen,
            toPaymentScreen = toPaymentScreen,
            bookingEvents=bookingEvents,
            bookingUiEvents = bookingUiEvents
        )
    }
}

fun NavController.navigateToPayment() {
    this.navigate(paymentRoute)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.roomScreen(
    backToBooking: () -> Unit,
    toHotelScreen: () -> Unit
) {

    composable(route = paymentRoute) {
        PaymentScreen(
            backToBooking=backToBooking,
            toHotelScreen=toHotelScreen)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.bookingGraph(
    navController: NavController,
    toHotelScreen: () -> Unit,
    backToRoomsScreen: () -> Unit
) {
    navigation(
        startDestination = bookingRoute,
        route = BookingBlockPattern
    ) {
        hotelScreen(
            backToRoomsScreen = backToRoomsScreen,
            toPaymentScreen = {
                navController.navigateToPayment()
            }
        )
        roomScreen(
            backToBooking = {
                navController.popBackStack()
            },
            toHotelScreen = toHotelScreen
        )
    }
}

fun NavController.navigateToBookingGraph() {
    this.navigate(BookingBlockPattern)
}