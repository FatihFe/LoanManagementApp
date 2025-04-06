package com.loanmanagementapp.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.formatAmount(): String {
    // Format value and round to approximate value
    val numberFormat = NumberFormat.getNumberInstance(Locale("tr", "TR"))
    val formatted = numberFormat.format(this)

    // If there is a ",00" at the end, remove it
    return if (formatted.endsWith(",00")) {
        formatted.substring(0, formatted.length - 3)
    } else {
        formatted
    }
}