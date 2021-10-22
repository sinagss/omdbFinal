package com.example.movierecycler.di

import androidx.room.Room
import com.example.movierecycler.model.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "movie"
        ).allowMainThreadQueries().build()

    }
}

//val retroInterfaceModule = module {
//    single {
//
//    }
//}