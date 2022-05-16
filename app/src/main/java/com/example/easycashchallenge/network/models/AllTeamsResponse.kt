package com.example.easycashchallenge.network.models

data class AllTeamsResponse(
    val competition: Competition?,
    val count: Int?,
    val filters: Filters?,
    val season: Season?,
    val teams: List<Team>?
)