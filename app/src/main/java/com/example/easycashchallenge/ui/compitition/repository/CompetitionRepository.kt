package com.example.easycashchallenge.ui.compitition.repository

import androidx.lifecycle.LiveData
import com.example.easycashchallenge.base.BaseRepository
import com.example.easycashchallenge.local.AppDao
import com.example.easycashchallenge.network.AppService
import com.example.easycashchallenge.network.models.AllTeamsResponse
import com.example.easycashchallenge.network.models.Team
import com.example.easycashchallenge.utils.Constants
import io.reactivex.Single

class CompetitionRepository(appService: AppService, private val appDao: AppDao) :
    BaseRepository(appService, appDao) {

    fun getAllTeams(id: Int): Single<AllTeamsResponse> =
        appService.getAllTeams(Constants.API.token, id = id)

    fun getAllCachedTeams(): LiveData<List<Team>> = appDao.getAllTeams()

    fun cacheTeams(teams: List<Team>) = appDao.addAllTeams(teams)
}