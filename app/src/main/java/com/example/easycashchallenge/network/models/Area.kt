package com.example.easycashchallenge.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Area(
    val id: Int? = 0,
    val name: String? = "",
    val countryCode: String? = "",
    val ensignUrl: String? = "",
) : Parcelable