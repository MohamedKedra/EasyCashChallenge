package com.example.easycashchallenge.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable