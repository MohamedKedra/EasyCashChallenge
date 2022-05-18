package com.example.easycashchallenge.network.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.easycashchallenge.utils.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constants.DB.competitionTable)
data class Competition(
    val area: Area? = Area(),
    val code: String? = "",
    val currentSeason: CurrentSeason? = CurrentSeason(),
    val emblemUrl: String? = "",
    @PrimaryKey
    val id: Int? = 0,
    val lastUpdated: String? = "",
    val name: String? = "",
    val numberOfAvailableSeasons: Int? = 0,
    val plan: String? = ""
) : Parcelable