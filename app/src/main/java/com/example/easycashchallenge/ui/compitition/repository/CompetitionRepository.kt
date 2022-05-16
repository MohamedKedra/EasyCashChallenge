package com.example.easycashchallenge.ui.compitition.repository

import com.example.easycashchallenge.base.BaseRepository
import com.example.easycashchallenge.network.AppService
import com.example.easycashchallenge.network.models.AllTeamsResponse
import com.example.easycashchallenge.utils.Constants
import io.reactivex.Single

class CompetitionRepository(appService: AppService) : BaseRepository(appService) {

    fun getAllTeams(id: Int): Single<AllTeamsResponse> =
        appService.getAllTeams(Constants.API.token, id = id)
}