package com.example.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.common.components.DateInput
import com.example.common.components.EmptyContainer
import com.example.common.components.IntPassportInput
import com.example.common.components.MailInput
import com.example.common.components.NameInput
import com.example.common.components.RusPhoneNumberInput
import com.example.common.components.TitleText
import com.example.designsystem.theme.theme_figma_container
import com.example.designsystem.theme.theme_figma_onBackground
import com.example.designsystem.theme.theme_figma_primary
import com.example.presentation.R
import java.util.Locale

@Composable
fun BookingInfoRow(
    modifier: Modifier=Modifier,
    title: String,
    value: String
) {
    Row(
        modifier=modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            modifier=Modifier.weight(4f),
            text=title,
            style= MaterialTheme.typography.bodyLarge,
            color=theme_figma_container
        )
        Text(
            modifier=Modifier.weight(6f),
            text=value,
            style= MaterialTheme.typography.bodyLarge,
            color=theme_figma_onBackground
        )
    }
}

@Composable
fun BookingPurchaseRow(
    modifier: Modifier=Modifier,
    title: String,
    value: String,
    colorValue: Color = theme_figma_onBackground
) {
    Row(
        modifier=modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text=title,
            style= MaterialTheme.typography.bodyLarge,
            color=theme_figma_container
        )
        Text(
            text=value,
            style= MaterialTheme.typography.bodyLarge,
            color=colorValue,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun BookingHotelInfo(
    modifier: Modifier =Modifier,
    flight_from: String,
    country_city: String,
    dates: String,
    number_of_nights: String,
    hotel: String,
    room_type: String,
    meal: String
    ) {
    EmptyContainer(
        modifier=modifier
    ){
        Column(
            modifier=Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BookingInfoRow(
                title = stringResource(R.string.flight_from),
                value = flight_from
            )
            BookingInfoRow(
                title = stringResource(R.string.country_city),
                value = country_city
            )
            BookingInfoRow(
                title = stringResource(R.string.dates),
                value = dates
            )
            BookingInfoRow(
                title = stringResource(R.string.number_of_nights),
                value = number_of_nights
            )
            BookingInfoRow(
                title = stringResource(R.string.hotel),
                value = hotel
            )
            BookingInfoRow(
                title = stringResource(R.string.room_type),
                value = room_type
            )
            BookingInfoRow(
                title = stringResource(R.string.meal),
                value = meal
            )
        }
    }
}


@Composable
fun PurchaserInfo(
    modifier: Modifier =Modifier,
    phoneString: String,
    mail: String,
    onPhoneChange: (String) -> Unit,
    onMailChange: (String) -> Unit,
    enableCheckMail: Boolean
) {
    EmptyContainer(
        modifier=modifier
    ){
        Column(
            modifier=Modifier.fillMaxWidth()
        ){
            TitleText(text = stringResource(R.string.info_purchaser))
            Spacer(modifier = Modifier.height(20.dp))
            RusPhoneNumberInput(
                name = phoneString,
                onNameChanged = onPhoneChange
            )
            Spacer(modifier = Modifier.height(8.dp))
            MailInput(
                name = mail,
                onNameChanged = onMailChange,
                enableShowError=enableCheckMail
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text= stringResource(R.string.purchaser_disclaimer),
                style = MaterialTheme.typography.bodyMedium,
                color = theme_figma_container
            )
        }
    }
}

@Composable
fun TouristInfoBlock(
    modifier: Modifier =Modifier,
    title: String,
    nameString: String,
    onNameChange: (String) -> Unit,
    surnameString: String,
    onSurnameChange: (String) -> Unit,
    dateString: String,
    onDateChange: (String) -> Unit,
    citizenshipString: String,
    onCitizenshipChange: (String) -> Unit,
    intPassportString: String,
    onIntPassportChange: (String) -> Unit,
    passportDueString: String,
    onPassportDueChange: (String) -> Unit,
    enableShowError: Boolean = false
) {
    var expandBlock by remember{ mutableStateOf(true)}

    EmptyContainer(
        modifier=modifier
    ){
        Column(
            modifier=Modifier.fillMaxWidth()
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TitleText(text = title)
                IconButton(
                    onClick = {
                    expandBlock = !expandBlock },
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.extraSmall)
                        .background(
                            theme_figma_primary.copy(
                                alpha = 0.1f
                            )
                        )
                ) {
                    Icon(
                        imageVector = if(expandBlock) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = theme_figma_primary
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            if(expandBlock){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    NameInput(
                        name = nameString,
                        onNameChanged = onNameChange,
                        label = stringResource(R.string.name_customer),
                        enableShowError=enableShowError
                    )
                    NameInput(
                        name = surnameString,
                        onNameChanged = onSurnameChange,
                        label = stringResource(R.string.surname),
                        enableShowError=enableShowError
                    )
                    DateInput(
                        name = dateString,
                        onNameChanged = onDateChange,
                        label = stringResource(R.string.date_birthday),
                        enableShowError=enableShowError
                    )
                    NameInput(
                        name = citizenshipString,
                        onNameChanged = onCitizenshipChange,
                        label = stringResource(R.string.citizenship),
                        enableShowError=enableShowError
                    )
                    IntPassportInput(
                        name = intPassportString,
                        onNameChanged = onIntPassportChange,
                        label = stringResource(R.string.intpassport_num),
                        enableShowError=enableShowError
                    )
                    DateInput(
                        name = passportDueString,
                        onNameChanged = onPassportDueChange,
                        label = stringResource(R.string.passport_due),
                        enableShowError=enableShowError
                    )
                }
            }
        }
    }
}



@Composable
fun AddCustomRow(
    modifier: Modifier =Modifier,
    onAddBtnClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TitleText(text = stringResource(R.string.add_tourist))
        IconButton(
            onClick = onAddBtnClick,
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraSmall)
                .background(
                    theme_figma_primary
                )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun PurchaseInfoBlock(
    modifier: Modifier =Modifier,
    tour: Int,
    fuel: Int,
    service: Int
) {
    EmptyContainer(
        modifier=modifier
    ){
        Column(
            modifier=Modifier.fillMaxWidth()
        ){
            BookingPurchaseRow(
                title = stringResource(R.string.tour),
                value = "${String.format(Locale.FRANCE, "%,d",tour)} ₽"
            )
            BookingPurchaseRow(
                title = stringResource(R.string.fuel_surcharge),
                value = "${String.format(Locale.FRANCE, "%,d",fuel)} ₽"
            )
            BookingPurchaseRow(
                title = stringResource(R.string.service_charge),
                value = "${String.format(Locale.FRANCE, "%,d",service)} ₽"
            )
            BookingPurchaseRow(
                title = stringResource(R.string.to_payment),
                value = "${String.format(Locale.FRANCE, "%,d",(tour + fuel + service))} ₽",
                colorValue = theme_figma_primary
            )
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun BookingInfoRowPreview() {
//    HotelAppTheme {
//        BookingInfoRow(
//            title="Вылет из",
//            value = "Санкт-Петербург"
//        )
//    }
//}
