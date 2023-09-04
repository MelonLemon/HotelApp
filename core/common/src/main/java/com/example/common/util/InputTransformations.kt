package com.example.common.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation



class MaskTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return mobileNumberFilter(text)
    }
}

class DateTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return dateFilter(text)
    }
}

fun mobileNumberFilter(text: AnnotatedString): TransformedText {

    val trimmed =
        if (text.text.length >= 10) text.text.substring(0..9) else text.text

    var out = ""
    for (i in trimmed.indices) {

        if(i==0){
            out += "("
            out += trimmed[i]
        } else {
            out += trimmed[i]
        }
        if(i==2){
            out += ") "
        }
        if(i == 5 || i == 7){
            out += "-"
        }
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 0) return offset
            if (offset <= 2) return offset + 1
            if (offset <= 5) return offset + 3
            if (offset <= 7) return offset + 4
            if (offset <= 10) return offset + 5
            return 15
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 5) return offset - 1
            if (offset <= 9) return offset - 3
            if (offset <= 12) return offset - 4
            if (offset <= 15) return offset - 5
            return 10
        }
    }

    return TransformedText(AnnotatedString(out), phoneNumberOffsetTranslator)
}



fun dateFilter(text: AnnotatedString): TransformedText {

    val trimmed =
        if (text.text.length >= 8) text.text.substring(0..7) else text.text

    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 2 == 1 && i < 4) out += "."
    }

    val dateNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 3) return offset + 1
            if (offset <= 8) return offset + 2
            return 10
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 5) return offset - 1
            if (offset <= 10) return offset - 2
            return 8
        }
    }

    return TransformedText(AnnotatedString(out), dateNumberOffsetTranslator)
}


