package com.example.movierecycler

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

//        startKoin {
//            androidContext(this@BaseApplication)
//            modules(roomModule)
//        }
    }
}