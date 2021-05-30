package com.example.lab_android_outlay

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

open class Category(@DrawableRes val icon: Int, @StringRes val text: Int){
    companion object {
        val categories = listOf(
            Category(R.drawable.home, R.string.house),
            Category(R.drawable.car, R.string.car),
            Category(R.drawable.destination, R.string.travel),
            Category(R.drawable.groceries, R.string.grocery),
            Category(R.drawable.sport, R.string.sport),
            Category(R.drawable.ticket, R.string.tickets),
            Category(R.drawable.sweets, R.string.sweets),
            Category(R.drawable.fashion, R.string.clothes),
            Category(R.drawable.salon, R.string.beauty),
            Category(R.drawable.tv, R.string.tv),
        )
    }
}


