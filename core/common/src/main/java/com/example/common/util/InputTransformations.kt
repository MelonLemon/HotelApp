package com.example.common.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

val phone_mask = "(***) ***-**-**"


fun mobileNumberFilter(text: AnnotatedString): TransformedText {

    val trimmed =
        if (text.text.length >= 10) text.text.substring(0..9) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            val oneDigit = trimmed[i]
            if(i==0){
                append("(${oneDigit}")
            } else {
                append(oneDigit)
            }
            if(i==2){
                append(") ")
            }
            if(i == 5 || i == 7){
                append("-")
            }
        }
        pushStyle(SpanStyle(color = Color.DarkGray))
        append(phone_mask.takeLast(phone_mask.length - length))
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset + 1
            if (offset <= 6) return offset + 2
            if (offset <= 9) return offset + 3
            return 12
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset - 1
            if (offset <= 6) return offset - 2
            if (offset <= 9) return offset - 3
            return 9
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}

val date_mask = "__.__.____"

fun dateFilter(text: AnnotatedString): TransformedText {

    val trimmed =
        if (text.text.length >= 8) text.text.substring(0..7) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            val oneDigit = trimmed[i]
            append(oneDigit)
            if(i == 1 || i == 3){
                append(".")
            }
        }
        pushStyle(SpanStyle(color = Color.DarkGray))
        append(date_mask.takeLast(phone_mask.length - length))
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset + 1
            if (offset <= 6) return offset + 2
            if (offset <= 9) return offset + 3
            return 12
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset - 1
            if (offset <= 6) return offset - 2
            if (offset <= 9) return offset - 3
            return 9
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}