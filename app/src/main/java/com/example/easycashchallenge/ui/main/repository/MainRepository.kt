package com.example.easycashchallenge.ui.main.repository

import com.example.easycashchallenge.base.BaseRepository
import com.example.easycashchallenge.network.AppService
import com.example.easycashchallenge.network.models.CompetitionsResponse
import io.reactivex.Single

class MainRepository(appService: AppService) : BaseRepository(appService) {

    fun getAllCompetitions(): Single<CompetitionsResponse> = appService.getAllCompetitions()
}