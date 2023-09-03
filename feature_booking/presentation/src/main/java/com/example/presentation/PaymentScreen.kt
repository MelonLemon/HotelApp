package com.example.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.common.components.BackToNavigationRow
import com.example.common.components.PrimaryButton
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
            PrimaryButton(
                text = stringResource(R.string.awesome),
                onBtnClick = toHotelScreen
            )
        },
        topBar = {
            BackToNavigationRow(
                text = stringResource(R.string.order_paid),
                onBtnClick = backToBooking
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ){
            SuccessfulTransactionDisplay(
                orderNum = Random.nextInt(100000,199999)
            )
        }
    }
}