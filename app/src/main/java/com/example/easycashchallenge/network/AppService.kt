package com.example.easycashchallenge.network

import com.example.easycashchallenge.network.models.AllTeamsResponse
import com.example.easycashchallenge.network.models.CompetitionInfoResponse
import com.example.easycashchallenge.network.models.CompetitionsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface AppService {

    @GET("competitions")
    fun getAllCompetitions(): Single<CompetitionsResponse>

    @GET("competitions/{id}")
    fun getCompetitionInfo(
        @Header("X-Auth-Token") token: String,
        @Path("id") id: Int
    ): Single<CompetitionInfoResponse>

    @GET("competitions/{id}/teams")
    fun getAllTeams(
        @Header("X-Auth-Token") token: String,
        @Path("id") id: Int
    ): Single<AllTeamsResponse>
}