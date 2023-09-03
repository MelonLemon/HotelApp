package com.example.presentation.hotel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.common.components.PrimaryButton
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
            PrimaryButton(
                text = stringResource(R.string.forward_to_room),
                onBtnClick = toRoomScreen
            )
        }
        ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ){
            Text(
                modifier=Modifier.fillMaxSize(),
                text = stringResource(R.string.hotel),
                textAlign = TextAlign.Center
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                item{
                    Text(
                        modifier=Modifier.fillMaxSize(),
                        text = stringResource(R.string.hotel),
                        textAlign = TextAlign.Center
                    )
                }
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