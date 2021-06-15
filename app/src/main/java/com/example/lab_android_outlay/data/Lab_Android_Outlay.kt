package com.example.lab_android_outlay.data

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class Lab_Android_Outlay : Application(){

    override fun onCreate() {
        super.onCreate()

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}