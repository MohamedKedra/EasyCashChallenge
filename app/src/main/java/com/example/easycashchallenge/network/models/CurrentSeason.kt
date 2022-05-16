package com.example.easycashchallenge.network.models

data class CurrentSeason(
    val currentMatchday: Int?,
    val endDate: String?,
    val id: Int?,
    val startDate: String?,
    val winner: Any?
)