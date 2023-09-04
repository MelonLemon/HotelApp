package com.example.presentation.navigation


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


const val BookingBlockPattern = "bookingInfo"
const val bookingRoute = "booking_route"
const val paymentRoute = "payment_route"

fun NavController.navigateToBooking() {
    this.navigate(bookingRoute)
}


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