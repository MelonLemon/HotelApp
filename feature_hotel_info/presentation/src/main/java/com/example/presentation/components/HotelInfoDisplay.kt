package com.example.presentation.components

import android.util.Size
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.components.BasicHotelInfo
import com.example.common.components.BoldTextWithSubtitle
import com.example.common.components.EmptyContainer
import com.example.common.components.MarkedInfoDisplay
import com.example.common.components.PhotosDisplay
import com.example.common.components.TitleText
import com.example.designsystem.theme.HotelAppTheme
import com.example.designsystem.theme.theme_figma_container
import com.example.designsystem.theme.theme_figma_onSurface
import com.example.designsystem.theme.theme_figma_onSurfaceVariant
import com.example.presentation.R

@Composable
fun ForwardToNavigationRow(
    modifier: Modifier=Modifier,
    icon: ImageVector,
    title: String,
    subtitle: String,
    onBtnClick: () -> Unit
) {
    Row(
        modifier=modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = theme_figma_onSurface
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text=title,
                    style=MaterialTheme.typography.titleMedium,
                    color=theme_figma_onSurface
                )
                Text(
                    text=subtitle,
                    style=MaterialTheme.typography.titleSmall,
                    color=theme_figma_onSurfaceVariant
                )
            }
        }
        IconButton(
            onClick = onBtnClick,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Transparent
            )
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = theme_figma_onSurface
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainHotelInfo(
    modifier: Modifier=Modifier,
    rankingText: String,
    name: String,
    place: String,
    amountText: String,
    additionalInfo: String,
    onPlaceClick: () -> Unit = { },
    listOfPhotosString: List<String>,
    pagerState: PagerState
) {
    EmptyContainer(
        modifier=modifier
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                modifier=Modifier.fillMaxSize(),
                text = stringResource(R.string.hotel),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            PhotosDisplay(
                listOfPhotosString = listOfPhotosString,
                pagerState = pagerState
            )
            BasicHotelInfo(
                rankingText = rankingText,
                name=name,
                place=place,
                onPlaceClick=onPlaceClick
            )
            BoldTextWithSubtitle(
                boldText = amountText,
                subtitle = additionalInfo
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HotelDetails(
    modifier: Modifier=Modifier,
    listInfo: List<String>,
    description: String
) {
    EmptyContainer(
        modifier=modifier
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ){
            TitleText(text= stringResource(R.string.about_hotel))
            FlowRow {
                listInfo.forEach { info ->
                    MarkedInfoDisplay(text=info)
                }

            }

            Text(
                text=description,
                style=MaterialTheme.typography.bodyLarge
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                ForwardToNavigationRow(
                    icon = ImageVector.vectorResource(id = R.drawable.emojihappy),
                    title = stringResource(R.string.facilities),
                    subtitle = stringResource(R.string.essentials),
                    onBtnClick = { }
                )
                Divider(thickness = 1.dp, color = theme_figma_container)
                ForwardToNavigationRow(
                    icon = ImageVector.vectorResource(id = R.drawable.ticksquare),
                    title = stringResource(R.string.included),
                    subtitle = stringResource(R.string.essentials),
                    onBtnClick = { }
                )
                Divider(thickness = 1.dp, color = theme_figma_container)
                ForwardToNavigationRow(
                    icon = ImageVector.vectorResource(id = R.drawable.closesquare),
                    title = stringResource(R.string.not_included),
                    subtitle = stringResource(R.string.essentials),
                    onBtnClick = { }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ForwardToNavigationRowPreview() {
//    HotelAppTheme {
//        ForwardToNavigationRow(
//            icon = Icons.Default.Home,
//            title = "Удобства",
//            subtitle = "Самое необходимое",
//            onBtnClick = { }
//        )
//    }
//}
//
//@OptIn(ExperimentalFoundationApi::class)
//@Preview(showBackground = true)
//@Composable
//fun MainHotelInfoPreview() {
//    HotelAppTheme {
//        val pagerState = rememberPagerState(pageCount = {
//            5
//        })
//        MainHotelInfo(
//            rankingText = "5 Превосходно",
//            name = "Steigenberger Makadi",
//            place="Madit Makadi, Safaga road, Makadi Bay, Египет",
//            amountText = "от 134 673 Р",
//            additionalInfo = "за тур с перелетом",
//            listOfPhotosString = listOf("","a","ss","a","ss"),
//            pagerState = pagerState
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun HotelDetailsPreview() {
//    HotelAppTheme {
//        HotelDetails(
//            listInfo = listOf(
//                "3-я линия",
//                "Платный wifi в фоей",
//                "30 км до Аэропорта",
//                "1 км до пляжа"
//            ),
//            description = "Отель VIP-класса с собственными гольф полями. " +
//                    "Высокий уровнь сервиса. " +
//                    "Рекомендуем для респектабельного отдыха. " +
//                    "Отель принимает гостей от 18 лет!"
//        )
//    }
//}