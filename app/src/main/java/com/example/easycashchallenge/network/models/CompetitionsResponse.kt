package com.example.easycashchallenge.network.models

import android.os.Parcelable

data class CompetitionsResponse(
    val competitions: List<Competition>?,
    val count: Int?,
    val filters: Filters?
)

data class Competition(
    val area: Area?,
    val code: String?,
    val currentSeason: CurrentSeason?,
    val emblemUrl: Any?,
    val id: Int?,
    val lastUpdated: String?,
    val name: String?,
    val numberOfAvailableSeasons: Int?,
    val plan: String?
)

data class CurrentSeason(
    val currentMatchday: Int?,
    val endDate: String?,
    val id: Int?,
    val startDate: String?,
    val winner: Any?
)

data class Area(
    val countryCode: String?,
    val ensignUrl: Any?,
    val id: Int?,
    val name: String?
)

class Filters