package com.example.easycashchallenge.ui.compitition.di

import com.example.easycashchallenge.ui.compitition.repository.CompetitionRepository
import com.example.easycashchallenge.ui.compitition.viewmodel.CompititionViewModel
import org.koin.dsl.module

val competitionModule = module {

    single {
        CompetitionRepository(get(), get())
    }

    single {
        CompititionViewModel(get(), get())
    }
}