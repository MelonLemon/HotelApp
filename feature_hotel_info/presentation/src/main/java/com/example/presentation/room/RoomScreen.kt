package com.example.presentation.room

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.components.BackToNavigationRow
import com.example.common.components.PrimaryButton
import com.example.designsystem.theme.HotelAppTheme
import com.example.designsystem.theme.theme_figma_background
import com.example.domain.model.Room
import com.example.domain.model.Rooms
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
            modifier = Modifier.padding(innerPadding).background(
                theme_figma_background
            )
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){

                if(roomsState.isLoading){
                    item{
                        Box(modifier = Modifier.fillMaxWidth()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                } else {
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
}

//@Preview(showBackground = true)
//@Composable
//fun RoomScreenPreview() {
//    HotelAppTheme {
//        RoomScreen(
//            roomsState = RoomsState(
//                hotelName = "Steigenberger Makadi",
//                listOfRooms = Rooms(
//                    rooms = arrayListOf(
//                        Room(
//                           id=1,
//                           name="Стандартный номер с видом на бассейн",
//                            price = 186600,
//                            pricePer = "За 7 ночей с перелетом",
//                            peculiarities = arrayListOf("Включен только завтрак",
//                                "Кондиционер"),
//                            imageUrls = arrayListOf(
//                                "https://www.atorus.ru/sites/default/files/upload/image/News/56871/%D1%80%D0%B8%D0%BA%D1%81%D0%BE%D1%81%20%D1%81%D0%B8%D0%B3%D0%B5%D0%B9%D1%82.jpg",
//                                "https://q.bstatic.com/xdata/images/hotel/max1024x768/267647265.jpg?k=c8233ff42c39f9bac99e703900a866dfbad8bcdd6740ba4e594659564e67f191&o=",
//                                "https://worlds-trip.ru/wp-content/uploads/2022/10/white-hills-resort-5.jpeg"
//                            )
//                        ),
//                        Room(
//                            id=1,
//                            name="Стандартный номер с видом на бассейн",
//                            price = 186600,
//                            pricePer = "За 7 ночей с перелетом",
//                            peculiarities = arrayListOf("Включен только завтрак",
//                                "Кондиционер"),
//                            imageUrls = arrayListOf(
//                                "https://www.atorus.ru/sites/default/files/upload/image/News/56871/%D1%80%D0%B8%D0%BA%D1%81%D0%BE%D1%81%20%D1%81%D0%B8%D0%B3%D0%B5%D0%B9%D1%82.jpg",
//                                "https://q.bstatic.com/xdata/images/hotel/max1024x768/267647265.jpg?k=c8233ff42c39f9bac99e703900a866dfbad8bcdd6740ba4e594659564e67f191&o=",
//                                "https://worlds-trip.ru/wp-content/uploads/2022/10/white-hills-resort-5.jpeg"
//                            )
//                        )
//                    )
//                ),
//                isLoading = false
//            ),
//            backToHotelScreen = { },
//            toBookingScreen = { }
//        )
//    }
//}