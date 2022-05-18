package com.example.easycashchallenge.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentSeason(
    val currentMatchday: String? = "",
    val endDate: String? = "",
    val id: Int? = 0,
    val startDate: String? = ""
) : Parcelable