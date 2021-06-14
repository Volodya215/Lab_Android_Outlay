package com.example.lab_android_outlay.data.model

data class UserData (
    val uid: String = "",
    val username: String = "",
    var houseSum: Int = 0,
    var carSum: Int = 0,
    var travelSum: Int = 0,
    var grocerySum: Int = 0,
    var sportSum: Int = 0,
    var ticketsSum: Int = 0,
    var sweetsSum: Int = 0,
    var clothesSum: Int = 0,
    var beautySum: Int = 0,
    var TVSum: Int = 0,
    var houseLimit: Int = Int.MAX_VALUE,
    var carLimit: Int = Int.MAX_VALUE,
    var travelLimit: Int = Int.MAX_VALUE,
    var groceryLimit: Int = Int.MAX_VALUE,
    var sportLimit: Int = Int.MAX_VALUE,
    var ticketsLimit: Int = Int.MAX_VALUE,
    var sweetsLimit: Int = Int.MAX_VALUE,
    var clothesLimit: Int = Int.MAX_VALUE,
    var beautyLimit: Int = Int.MAX_VALUE,
    var TVLimit: Int = Int.MAX_VALUE
)