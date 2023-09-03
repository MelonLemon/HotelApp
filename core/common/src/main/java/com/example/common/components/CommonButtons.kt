package com.example.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.theme.HotelAppTheme

@Composable
fun PrimaryButton(
    text: String,
    onBtnClick: () -> Unit
) {
    Button(
       onClick =  onBtnClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = MaterialTheme.shapes.large
    ){
        Text(
            modifier = Modifier.fillMaxWidth(),
            text=text,
            color=MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun InfoButton(
    text: String,
    onBtnClick: () -> Unit
) {
    Row(
        modifier = Modifier.clip(MaterialTheme.shapes.extraSmall).background(
            MaterialTheme.colorScheme.primary.copy(alpha=0.1f)
        ).clickable { onBtnClick() }.padding(top=5.dp, bottom = 5.dp, start=10.dp, end=2.dp)
    ){
        Text(
            text=text,
            color=MaterialTheme.colorScheme.primary
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint=MaterialTheme.colorScheme.primary
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PrimaryButtonPreview() {
//    HotelAppTheme {
//        PrimaryButton(
//            text="К выбору номера",
//            onBtnClick = { }
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun InfoButtonPreview() {
//    HotelAppTheme {
//        InfoButton(
//            text="Подробнее о номере",
//            onBtnClick = { }
//        )
//    }
//}

