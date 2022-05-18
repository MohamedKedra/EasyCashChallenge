package com.example.easycashchallenge.ui.main.repository

import com.example.easycashchallenge.base.BaseRepository
import com.example.easycashchallenge.local.AppDao
import com.example.easycashchallenge.network.AppService
import com.example.easycashchallenge.network.models.Competition
import com.example.easycashchallenge.network.models.CompetitionsResponse
import io.reactivex.Single

class MainRepository(appService: AppService, private val appDao: AppDao) :
    BaseRepository(appService, appDao) {

    fun getAllCompetitions(): Single<CompetitionsResponse> = appService.getAllCompetitions()

    fun getCachedCompetitions(): Single<List<Competition>> = appDao.getAllCompetitions()

    fun cacheCompetitions(competitions: List<Competition>) =
        appDao.addAllCompetitions(competitions)
}