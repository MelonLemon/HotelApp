package com.example.common.components

import androidx.compose.foundation.background
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
import com.example.common.util.dateFilter
import com.example.common.util.isValidDate
import com.example.common.util.isValidEmail
import com.example.common.util.mobileNumberFilter
import com.example.designsystem.theme.HotelAppTheme
import kotlinx.coroutines.delay

@Composable
fun RusPhoneNumberInput(
    name: String = "",
    onNameChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(Color(0xFFF6F6F9)),
        value = name,
        onValueChange = onNameChanged,
        label = {Text(text=stringResource(R.string.phone_number))},
        placeholder = { Text(text="", overflow = TextOverflow.Ellipsis, maxLines = 1) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        prefix = { Text(text="+7 ", color = MaterialTheme.colorScheme.onBackground)},
        visualTransformation = { mobileNumberFilter(it) }
    )
}

@Composable
fun MailInput(
    name: String,
    onNameChanged: (String) -> Unit,
    enableShowError: Boolean
) {
    val isValid = isValidEmail(name)
    TextField(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(
                if (!isValid && enableShowError)  Color(0xFFEB5757).copy(alpha = 0.15f) else Color(0xFFF6F6F9)
            ),
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
            disabledIndicatorColor = Color.Transparent
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
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(
                if (name.isBlank() && enableShowError)  Color(0xFFEB5757).copy(alpha = 0.15f) else Color(0xFFF6F6F9)
            ),
        value = name,
        onValueChange = onNameChanged,
        label = {Text(text=label)},
        placeholder = { Text(text="", overflow = TextOverflow.Ellipsis, maxLines = 1) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
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
    val isValid = isValidDate(name)
    TextField(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(
                if (!isValid && enableShowError)  Color(0xFFEB5757).copy(alpha = 0.15f) else Color(0xFFF6F6F9)
            ),
        value = name,
        onValueChange = onNameChanged,
        label = {Text(text=label)},
        placeholder = { Text(text="", overflow = TextOverflow.Ellipsis, maxLines = 1) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        visualTransformation = { dateFilter(it) }
    )
}

@Composable
fun IntPassportInput(
    name: String = "",
    onNameChanged: (String) -> Unit,
    enableShowError: Boolean = false
) {
    TextField(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(
                if ((name.isBlank() || name.length<9) && enableShowError)  Color(0xFFEB5757).copy(alpha = 0.15f) else Color(0xFFF6F6F9)
            ),
        value = name,
        onValueChange = onNameChanged,
        placeholder = { Text(text="", overflow = TextOverflow.Ellipsis, maxLines = 1) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}


@Preview(showBackground = true)
@Composable
fun MailInputPreview() {
    HotelAppTheme {
        MailInput(
            name="fghfh@gmail.com",
            onNameChanged = { },
            enableShowError = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RusPhoneNumberInputPreview() {
    HotelAppTheme {
        RusPhoneNumberInput(
            name="",
            onNameChanged = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DateInputPreview() {
    HotelAppTheme {
        DateInput(
            name="",
            onNameChanged = { },
            label ="Дата Рождения"
        )
    }
}