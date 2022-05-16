package com.example.easycashchallenge.di

import android.app.Application
import com.example.easycashchallenge.ui.compitition.di.competitionModule
import com.example.easycashchallenge.ui.main.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(
                listOf(
                    appModule,
                    mainModule,
                    competitionModule
                )
            )
        }
    }
}