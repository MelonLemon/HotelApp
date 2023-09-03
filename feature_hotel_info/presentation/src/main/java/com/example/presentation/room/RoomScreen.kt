package com.example.presentation.room

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.common.components.BackToNavigationRow
import com.example.common.components.PrimaryButton
import com.example.presentation.R
import com.example.presentation.components.RoomInfoBlock
import com.example.presentation.room.util.RoomsState
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoomScreen(
    roomsState: RoomsState,
    backToHotelScreen: () -> Unit,
    toBookingScreen: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackToNavigationRow(
                text = roomsState.hotelName,
                onBtnClick = backToHotelScreen
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){

                items(roomsState.listOfRooms.rooms){ room ->
                    val pagerState = rememberPagerState(pageCount = {
                        room.imageUrls.size
                    })
                    RoomInfoBlock(
                        listInfo = room.peculiarities,
                        listOfPhotosString = room.imageUrls,
                        pagerState = pagerState,
                        name =room.name?: "Без имени",
                        amountText = "${String.format(Locale.FRANCE, "%,d", room.price)} ₽",
                        additionalInfo = room.pricePer?:"",
                        onRoomBtnClick = toBookingScreen,
                        onRoomDetailsClick = { }
                    )
                }
            }
        }
    }
}