package com.example.easycashchallenge.ui.compitition.repository

import com.example.easycashchallenge.base.BaseRepository
import com.example.easycashchallenge.local.AppDao
import com.example.easycashchallenge.network.AppService
import com.example.easycashchallenge.network.models.AllTeamsResponse
import com.example.easycashchallenge.network.models.ID
import com.example.easycashchallenge.network.models.Team
import com.example.easycashchallenge.utils.Constants
import io.reactivex.Single

class CompetitionRepository(appService: AppService, private val appDao: AppDao) :
    BaseRepository(appService, appDao) {

    fun getAllTeams(id: Int): Single<AllTeamsResponse> =
        appService.getAllTeams(Constants.API.token, id = id)

    fun getAllCachedTeams(): Single<List<Team>> = appDao.getAllTeams()

    fun cacheTeams(teams: List<Team>) = appDao.addAllTeams(teams)

    fun cacheIds(id: ID) = appDao.addCompetitionId(id)

    fun getAllCachedIds(): Single<List<ID>> = appDao.getAllCompetitionIds()
}