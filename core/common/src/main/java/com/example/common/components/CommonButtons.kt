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
import com.example.designsystem.theme.theme_figma_primary

@Composable
fun PrimaryButton(
    text: String,
    onBtnClick: () -> Unit
) {
    Button(
        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
        onClick =  onBtnClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = theme_figma_primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = MaterialTheme.shapes.medium
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
            theme_figma_primary.copy(alpha=0.1f)
        ).clickable { onBtnClick() }.padding(top=5.dp, bottom = 5.dp, start=10.dp, end=2.dp)
    ){
        Text(
            text=text,
            color=theme_figma_primary
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint=theme_figma_primary
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

