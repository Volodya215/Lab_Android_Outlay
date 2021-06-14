package com.example.lab_android_outlay

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.lab_android_outlay.data.model.UserData

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

//data class Line(val cat: Category, val amount: Int)

open class HistoryUserData(@DrawableRes val icon: Int, @StringRes val text: Int, val amount: Int){
    companion object {
        fun getUserData(user: UserData): List<HistoryUserData> {
            return listOf(
                HistoryUserData(R.drawable.home, R.string.house, user.houseSum),
                HistoryUserData(R.drawable.car, R.string.car, user.carSum),
                HistoryUserData(R.drawable.destination, R.string.travel, user.travelSum),
                HistoryUserData(R.drawable.groceries, R.string.grocery, user.grocerySum),
                HistoryUserData(R.drawable.sport, R.string.sport, user.sportSum),
                HistoryUserData(R.drawable.ticket, R.string.tickets, user.ticketsSum),
                HistoryUserData(R.drawable.sweets, R.string.sweets, user.sweetsSum),
                HistoryUserData(R.drawable.fashion, R.string.clothes, user.clothesSum),
                HistoryUserData(R.drawable.salon, R.string.beauty, user.beautySum),
                HistoryUserData(R.drawable.tv, R.string.tv, user.TVSum),
            )

        }
    }
}

