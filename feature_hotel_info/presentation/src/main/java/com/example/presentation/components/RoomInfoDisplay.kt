package com.example.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.components.BoldTextWithSubtitle
import com.example.common.components.EmptyContainer
import com.example.common.components.InfoButton
import com.example.common.components.MarkedInfoDisplay
import com.example.common.components.PhotosDisplay
import com.example.common.components.PrimaryButton
import com.example.common.components.TitleText
import com.example.designsystem.theme.HotelAppTheme
import com.example.presentation.R

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun RoomInfoBlock(
    modifier: Modifier=Modifier,
    listOfPhotosString: List<String>,
    pagerState: PagerState,
    listInfo: List<String>,
    name: String,
    onRoomDetailsClick: () -> Unit,
    amountText: String,
    additionalInfo: String,
    onRoomBtnClick: () -> Unit
) {
    EmptyContainer(
        modifier=modifier
    ){
        Column(
            modifier=Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ){
            PhotosDisplay(
                listOfPhotosString = listOfPhotosString,
                pagerState = pagerState
            )
            TitleText(text = name)
            FlowRow {
                listInfo.forEach { info ->
                    MarkedInfoDisplay(text=info)
                }
            }
            InfoButton(
                text = stringResource(R.string.room_details_more),
                onBtnClick = onRoomDetailsClick
            )
            BoldTextWithSubtitle(
                boldText = amountText,
                subtitle = additionalInfo
            )
            PrimaryButton(
                text = stringResource(R.string.choose_room),
                onBtnClick = onRoomBtnClick
            )
        }
    }

}

//@OptIn(ExperimentalFoundationApi::class)
//@Preview(showBackground = true)
//@Composable
//fun RoomInfoBlockPreview() {
//    HotelAppTheme {
//        val pagerState = rememberPagerState(pageCount = {
//            5
//        })
//        RoomInfoBlock(
//            listInfo = listOf(
//                "Всё включено",
//                "Кондиционер"),
//            listOfPhotosString = listOf("","a","ss","a","ss"),
//            pagerState = pagerState,
//            name ="Станданртный с видом на бассейн",
//            amountText = "186 600 Р",
//            additionalInfo = "за 7 ночей с перелётом",
//            onRoomBtnClick = { },
//            onRoomDetailsClick = { }
//        )
//    }
//}