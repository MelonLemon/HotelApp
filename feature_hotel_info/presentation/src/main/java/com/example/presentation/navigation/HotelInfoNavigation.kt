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
import com.example.presentation.hotel.HotelScreen
import com.example.presentation.hotel.HotelViewModel
import com.example.presentation.room.RoomScreen
import com.example.presentation.room.RoomViewModel


const val HotelInfoPattern = "hotelInfo"
const val hotelRoute = "hotel_route"
const val roomRoute = "room_route"

fun NavController.navigateToHotel() {
    this.navigate(hotelRoute)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.hotelScreen(
    toRoomScreen: () -> Unit
) {

    composable(route = hotelRoute) {
        val viewModel = hiltViewModel<HotelViewModel>()
        val hotelState by viewModel.hotelState.collectAsStateWithLifecycle()
        HotelScreen(
            hotelState = hotelState,
            toRoomScreen = toRoomScreen
        )
    }
}

fun NavController.navigateToRooms() {
    this.navigate(hotelRoute)
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.roomScreen(
    backToHotelScreen: () -> Unit,
    toBookingScreen: () -> Unit
) {

    composable(route = roomRoute) {
        val viewModel = hiltViewModel<RoomViewModel>()
        val hotelState by viewModel.roomsState.collectAsStateWithLifecycle()
        RoomScreen(
            roomsState = hotelState,
            backToHotelScreen = backToHotelScreen,
            toBookingScreen = toBookingScreen
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.hotelGraph(
    navController: NavController,
    toBookingScreen: () -> Unit
) {
    navigation(
        startDestination = hotelRoute,
        route = HotelInfoPattern
    ) {
        hotelScreen(
            toRoomScreen = { navController.navigateToRooms() }
        )
        roomScreen(
            backToHotelScreen = { navController.popBackStack() },
            toBookingScreen = toBookingScreen
        )
    }
}

fun NavController.navigateToHotelGraph() {
    this.navigate(HotelInfoPattern)
}