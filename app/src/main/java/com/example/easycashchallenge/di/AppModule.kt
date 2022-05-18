package com.example.easycashchallenge.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.easycashchallenge.local.AppDao
import com.example.easycashchallenge.local.AppDatabase
import com.example.easycashchallenge.network.AppService
import com.example.easycashchallenge.utils.Constants
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val appModule = module {

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {

        val instance = HttpLoggingInterceptor()
        instance.level = HttpLoggingInterceptor.Level.BODY
        return instance
    }

    fun provideNetworkInterceptor(): Interceptor {
        return Interceptor {

            val response = it.proceed(it.request())

            val cacheControl: CacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()

            response.newBuilder()
                .removeHeader(Constants.APICaching.headerPargma)
                .removeHeader(Constants.APICaching.headerCacheControl)
                .header(Constants.APICaching.headerCacheControl, cacheControl.toString())
                .build()
        }
    }

    fun provideOfflineInterceptor(): Interceptor {
        return Interceptor {

            var request = it.request()

            val cacheControl: CacheControl = CacheControl.Builder()
                .maxAge(7, TimeUnit.DAYS)
                .build()

            request = request.newBuilder()
                .removeHeader(Constants.APICaching.headerPargma)
                .removeHeader(Constants.APICaching.headerCacheControl)
                .cacheControl(cacheControl)
                .build()

            it.proceed(request)
        }
    }


    fun getHttpClient(context: Context): OkHttpClient {

        val cache =
            Cache(File(context.cacheDir, Constants.APICaching.offlineCache), 10 * 1024 * 1024)
        return OkHttpClient().newBuilder()
            .cache(cache)
            .addInterceptor(provideHttpLoggingInterceptor())
            .addNetworkInterceptor(provideNetworkInterceptor())
            .addInterceptor(provideOfflineInterceptor())
            .build()
    }

    single {
        Retrofit.Builder().baseUrl(Constants.API.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getHttpClient(context = androidContext()))
            .build()
    }

    single {
        get<Retrofit>().create(AppService::class.java)
    }

    single {
        get<Context>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun provideDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, Constants.DB.appDB)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(appDatabase: AppDatabase): AppDao {
        return appDatabase.appDao
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}