package com.example.presentation.hotel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.components.EmptyContainer
import com.example.common.components.PrimaryButton
import com.example.designsystem.theme.HotelAppTheme
import com.example.designsystem.theme.theme_figma_background
import com.example.domain.model.AboutTheHotel
import com.example.domain.model.HotelInfo
import com.example.presentation.R
import com.example.presentation.components.HotelDetails
import com.example.presentation.components.MainHotelInfo
import com.example.presentation.hotel.util.HotelState

import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@androidx.compose.runtime.Composable
fun HotelScreen(
    hotelState: HotelState,
    toRoomScreen: () -> Unit
    ) {

    val pagerState = rememberPagerState(pageCount = {hotelState.hotelInfo.imageUrls.size})
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            EmptyContainer(){
                PrimaryButton(
                    text = stringResource(R.string.forward_to_room),
                    onBtnClick = toRoomScreen
                )
            }

        }
        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(
                    theme_figma_background
                )
        ){
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                if(hotelState.isLoading){
                    item{
                        Box(modifier = Modifier.fillMaxWidth()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                } else {
                    item {
                        MainHotelInfo(
                            rankingText = "${hotelState.hotelInfo.rating} ${hotelState.hotelInfo.ratingName}",
                            name = hotelState.hotelInfo.name?:"",
                            place= hotelState.hotelInfo.address?:"",
                            amountText = "от ${String.format(Locale.FRANCE, "%,d", hotelState.hotelInfo.minimalPrice)} ₽",
                            additionalInfo = hotelState.hotelInfo.priceForIt?:"",
                            listOfPhotosString = hotelState.hotelInfo.imageUrls,
                            pagerState = pagerState
                        )
                    }

                    item {
                        HotelDetails(
                            listInfo = hotelState.hotelInfo.aboutTheHotel?.peculiarities ?: emptyList(),
                            description = hotelState.hotelInfo.aboutTheHotel?.description ?:""
                        )
                    }
                }

            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HotelScreenPreview() {
//    HotelAppTheme {
//        HotelScreen(
//            hotelState = HotelState(
//                hotelInfo = HotelInfo(
//                    id=1,
//                    name="Стандартный номер с видом на бассейн",
//                    address = "Madinat Makadi, Safaga Road, Makadi Bay, Египет",
//                    minimalPrice = 134268,
//                    priceForIt = "За тур с перелётом",
//                    rating = 5,
//                    ratingName = "Превосходно",
//                    imageUrls = arrayListOf("https://www.atorus.ru/sites/default/files/upload/image/News/56149/Club_Priv%C3%A9_by_Belek_Club_House.jpg",
//                        "https://deluxe.voyage/useruploads/articles/The_Makadi_Spa_Hotel_02.jpg",
//                        "https://deluxe.voyage/useruploads/articles/article_1eb0a64d00.jpg"),
//                    aboutTheHotel = AboutTheHotel(
//                        description = "Отель VIP-класса с собственными гольф полями. Высокий уровнь сервиса. Рекомендуем для респектабельного отдыха. Отель принимает гостей от 18 лет!",
//                        peculiarities = arrayListOf(
//                            "Бесплатный Wifi на всей территории отеля", "1 км до пляжа",
//                            "Бесплатный фитнес-клуб", "20 км до аэропорта"
//                        )
//                    )
//                ),
//                isLoading = false
//            ),
//            toRoomScreen = { }
//        )
//    }
//}