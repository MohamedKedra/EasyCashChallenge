package com.example.easycashchallenge.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.easycashchallenge.network.AppService
import com.example.easycashchallenge.utils.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder().baseUrl(Constants.API.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(AppService::class.java)
    }

    single {
        get<Context>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}