package com.example.easycashchallenge.network.models

data class CompetitionsResponse(
    val competitions: List<Competition>?,
    val count: Int?,
    val filters: Filters?
)
