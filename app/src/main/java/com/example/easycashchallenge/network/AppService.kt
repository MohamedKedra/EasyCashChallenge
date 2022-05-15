package com.example.easycashchallenge.network

import com.example.easycashchallenge.network.models.CompetitionsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AppService {

    @GET("competitions")
    fun getAllCompetitions(): Single<CompetitionsResponse>
}