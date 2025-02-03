package com.artinc.presentation.utils

public fun getPluralForm(count: Int, forms: List<String>): String {
    val mod10 = count % 10
    val mod100 = count % 100

    return when {
        mod100 in 11..19 -> forms[2] //"человек"
        mod10 == 1 -> forms[0] //"человек"
        mod10 in 2..4 -> forms[1] //"человека"
        else -> forms[2] //"человек"
    }
}
