package com.example.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.common.R
import com.example.common.util.DateTransformation
import com.example.common.util.MaskTransformation
import com.example.common.util.dateFilter
import com.example.common.util.isValidDate
import com.example.common.util.isValidEmail
import com.example.common.util.mobileNumberFilter
import com.example.designsystem.theme.HotelAppTheme
import com.example.designsystem.theme.theme_figma_background
import com.example.designsystem.theme.theme_figma_error
import kotlinx.coroutines.delay

@Composable
fun RusPhoneNumberInput(
    name: String = "",
    onNameChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        value = name,
        onValueChange = onNameChanged,
        label = {Text(text=stringResource(R.string.phone_number))},
        placeholder = { Text(text="", overflow = TextOverflow.Ellipsis, maxLines = 1) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor =  theme_figma_background,
            unfocusedContainerColor =  theme_figma_background
        ),
        prefix = { Text(text="+7 ", color = MaterialTheme.colorScheme.onBackground)},
        visualTransformation = MaskTransformation()
    )
}

@Composable
fun MailInput(
    name: String,
    onNameChanged: (String) -> Unit,
    enableShowError: Boolean
) {
    val isValid = if(enableShowError) isValidEmail(name) else false
    TextField(
        modifier = Modifier.fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        value = name,
        onValueChange = { newName ->
            onNameChanged(newName)
        },
        label = {Text(text= stringResource(R.string.mail))},
        placeholder = { Text(text="", overflow = TextOverflow.Ellipsis, maxLines = 1) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = if (enableShowError && !isValid) theme_figma_error else theme_figma_background,
            unfocusedContainerColor = if (enableShowError && !isValid) theme_figma_error else theme_figma_background
        )
    )

}

@Composable
fun NameInput(
    name: String = "",
    onNameChanged: (String) -> Unit,
    label: String,
    enableShowError: Boolean = false
) {
    TextField(
        modifier = Modifier.fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        value = name,
        onValueChange = onNameChanged,
        label = {Text(text=label)},
        placeholder = { Text(text="", overflow = TextOverflow.Ellipsis, maxLines = 1) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = if (enableShowError && name.isBlank()) theme_figma_error else theme_figma_background,
            unfocusedContainerColor = if (enableShowError && name.isBlank()) theme_figma_error else theme_figma_background,
        )
    )
}

@Composable
fun DateInput(
    name: String = "",
    onNameChanged: (String) -> Unit,
    label: String,
    enableShowError: Boolean = false
) {
    val isValid =  if(enableShowError) isValidDate(name) else false
    TextField(
        modifier = Modifier.fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        value = name,
        onValueChange = onNameChanged,
        label = {Text(text=label)},
        placeholder = { Text(text="", overflow = TextOverflow.Ellipsis, maxLines = 1) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = if (enableShowError && !isValid) theme_figma_error else theme_figma_background,
            unfocusedContainerColor = if (enableShowError && !isValid) theme_figma_error else theme_figma_background,
        ),
        visualTransformation = DateTransformation()
    )
}

@Composable
fun IntPassportInput(
    name: String = "",
    onNameChanged: (String) -> Unit,
    label: String,
    enableShowError: Boolean = false
) {
    TextField(
        modifier = Modifier.fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        value = name,
        label = {Text(text=label)},
        onValueChange = onNameChanged,
        placeholder = { Text(text="", overflow = TextOverflow.Ellipsis, maxLines = 1) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = if (enableShowError && name.isBlank()) theme_figma_error else theme_figma_background,
            unfocusedContainerColor = if (enableShowError && name.isBlank()) theme_figma_error else theme_figma_background
        )
    )
}


//@Preview(showBackground = true)
//@Composable
//fun MailInputPreview() {
//    HotelAppTheme {
//        MailInput(
//            name="fghfh@gmail.com",
//            onNameChanged = { },
//            enableShowError = true
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun RusPhoneNumberInputPreview() {
//    HotelAppTheme {
//        RusPhoneNumberInput(
//            name="",
//            onNameChanged = { }
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DateInputPreview() {
//    HotelAppTheme {
//        DateInput(
//            name="12",
//            onNameChanged = { },
//            label ="Дата Рождения",
//            enableShowError = true
//        )
//    }
//}