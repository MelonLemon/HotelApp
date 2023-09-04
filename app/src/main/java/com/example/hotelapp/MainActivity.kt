package com.example.hotelapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.designsystem.theme.HotelAppTheme
import com.example.presentation.navigation.HotelInfoPattern
import com.example.presentation.navigation.bookingGraph
import com.example.presentation.navigation.hotelGraph
import com.example.presentation.navigation.navigateToBooking
import com.example.presentation.navigation.navigateToHotelGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotelAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = HotelInfoPattern,
                    ){
                        hotelGraph(
                            navController=navController,
                            toBookingScreen = {  navController.navigateToBooking()  }
                        )
                        bookingGraph(
                            navController=navController,
                            toHotelScreen = {
                                navController.navigateToHotelGraph()
                            },
                            backToRoomsScreen = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}



