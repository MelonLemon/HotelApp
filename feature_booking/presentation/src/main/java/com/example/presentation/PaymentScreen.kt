package com.example.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.components.BackToNavigationRow
import com.example.common.components.EmptyContainer
import com.example.common.components.PrimaryButton
import com.example.designsystem.theme.HotelAppTheme
import com.example.presentation.components.SuccessfulTransactionDisplay
import kotlin.random.Random

@Composable
fun PaymentScreen(
    backToBooking: () -> Unit,
    toHotelScreen: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            EmptyContainer(){
                PrimaryButton(
                    text = stringResource(R.string.awesome),
                    onBtnClick = toHotelScreen
                )
            }

        },
        topBar = {
            BackToNavigationRow(
                text = stringResource(R.string.order_paid),
                onBtnClick = backToBooking
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(
                    Color.White
                )
        ){
            Spacer(modifier=Modifier.height(100.dp))
            SuccessfulTransactionDisplay(
                modifier = Modifier.fillMaxSize(),
                orderNum = Random.nextInt(100000,199999)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PaymentScreenPreview() {
//    HotelAppTheme {
//        PaymentScreen(
//            backToBooking = { },
//            toHotelScreen = { }
//        )
//    }
//}