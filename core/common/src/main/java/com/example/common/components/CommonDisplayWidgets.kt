package com.example.common.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.common.R
import com.example.designsystem.theme.HotelAppTheme
import com.example.designsystem.theme.theme_figma_onBackground
import com.example.designsystem.theme.theme_figma_onSurfaceVariant
import com.example.designsystem.theme.theme_figma_primary
import com.example.designsystem.theme.theme_figma_surface
import com.example.designsystem.theme.theme_figma_surfaceVarient
import com.example.designsystem.theme.theme_figma_tint
import com.example.designsystem.theme.theme_figma_tint_transparent


@Composable
fun BackToNavigationRow(
    modifier: Modifier=Modifier,
    text: String,
    onBtnClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                theme_figma_surface
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        IconButton(
            onClick = onBtnClick,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Transparent
            )
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint= theme_figma_onBackground
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            color = theme_figma_onBackground,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosDisplay(
    modifier: Modifier=Modifier,
    listOfPhotosString: List<String>,
    pagerState: PagerState
) {
    Box(
        modifier=modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState
        ) { photo ->
            if(listOfPhotosString.isNotEmpty()){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(listOfPhotosString[photo])
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(257.dp)
                        .clip(MaterialTheme.shapes.small)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = "Photo File",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.small)
                )
            }

        }
        PageIndicatorBox(
            modifier = Modifier.align(Alignment.BottomCenter),
            pageCount = listOfPhotosString.size,
            pagerState=pagerState
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicatorBox(
    modifier: Modifier=Modifier,
    pageCount: Int,
    pagerState: PagerState
) {
    Row(
        modifier=modifier
            .padding(vertical = 8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(theme_figma_surface)
            .padding(vertical = 5.dp, horizontal = 6.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(7.dp)
            )
        }
    }
}

@Composable
fun BasicHotelInfo(
    modifier: Modifier = Modifier,
    rankingText: String,
    name: String,
    place: String,
    onPlaceClick: () -> Unit = { }
) {
    Column(
        modifier=modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ){
        Row(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .background(theme_figma_tint_transparent)
                .padding(vertical = 5.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ){
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint=theme_figma_tint
            )
            Text(
                text=rankingText,
                color=theme_figma_tint,
                style = MaterialTheme.typography.titleMedium
            )
        }
        TitleText(
            text=name
        )
        Text(
            modifier=Modifier.clickable { onPlaceClick() },
            text=place,
            color=theme_figma_primary,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun BoldTextWithSubtitle(
    modifier: Modifier=Modifier,
    boldText: String,
    subtitle: String
) {
    Row(
        modifier=modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.alignByBaseline(),
            text=boldText,
            color=theme_figma_onBackground,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier.alignByBaseline(),
            text=subtitle,
            color=theme_figma_onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge
        )
    }
    
}

@Composable
fun EmptyContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            theme_figma_surface
        )
    ){
        Box(
            modifier = modifier.padding(16.dp),
        ) {
            content()
        }

    }
}

@Composable
fun MarkedInfoDisplay(
    text: String
) {
    Text(
        modifier = Modifier
            .drawBehind {
                drawRoundRect(
                    color = theme_figma_surfaceVarient,
                    size = this.size,
                    cornerRadius = CornerRadius(25f, 25f)
                )
            }
            .padding(horizontal = 10.dp, vertical = 5.dp),
        text=text,
        maxLines = 1,
        overflow = TextOverflow.Clip,
        color = theme_figma_onSurfaceVariant,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
fun TitleText(
    text: String
) {
    Text(
        text=text,
        color=theme_figma_onBackground,
        style=MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold
        )
    )
}

//@Preview(showBackground = true)
//@Composable
//fun BackToNavigationRowPreview() {
//    HotelAppTheme {
//        BackToNavigationRow(
//            text="Steigenberg Makadi",
//            onBtnClick = { }
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun BasicHotelInfoPreview() {
//    HotelAppTheme {
//        BasicHotelInfo(
//            rankingText = "5 Превосходно",
//            name = "Steigenberger Makadi",
//            place="Madit Makadi, Safaga road, Makadi Bay, Египет"
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun BoldTextWithSubtitlePreview() {
//    HotelAppTheme {
//        BoldTextWithSubtitle(
//            boldText = "от 134 673 Р",
//            subtitle = "за тур с перелетом"
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun EmptyContainerPreview() {
//    HotelAppTheme {
//        EmptyContainer(
//            content = { Text(text="Sample Sample")}
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun MarkedInfoDisplayPreview() {
//    HotelAppTheme {
//        MarkedInfoDisplay(
//            text="3 линия"
//        )
//    }
//}
//
//@OptIn(ExperimentalFoundationApi::class)
//@Preview(showBackground = true)
//@Composable
//fun PageIndicatorBoxPreview() {
//    HotelAppTheme {
//        val pagerState = rememberPagerState(pageCount = {
//            5
//        })
//        PageIndicatorBox(
//            pageCount = 5,
//            pagerState = pagerState
//        )
//    }
//}
//
//@OptIn(ExperimentalFoundationApi::class)
//@Preview(showBackground = true)
//@Composable
//fun PhotosDisplayPreview() {
//    HotelAppTheme {
//        val pagerState = rememberPagerState(pageCount = {
//            5
//        })
//        PhotosDisplay(
//            listOfPhotosString = listOf("","a","ss","a","ss"),
//            pagerState = pagerState
//        )
//    }
//}