package com.example.easycashchallenge.ui.main.di

import com.example.easycashchallenge.ui.main.repository.MainRepository
import com.example.easycashchallenge.ui.main.viewmodel.MainViewModel
import org.koin.dsl.module

val mainModule = module {


    single {
        MainRepository(get(), get())
    }


    single {
        MainViewModel(get(), get())
    }
}