package com.example.easycashchallenge.network.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.easycashchallenge.utils.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constants.DB.teamTable)
data class Team(
    @PrimaryKey
    val id: Int? = 0,
    val address: String? = "",
    val area: Area? = Area(),
    val clubColors: String? = "",
    val crestUrl: String?= "",
    val email: String? = "",
    val founded: Int? = 0,
    val lastUpdated: String? = "",
    val name: String? = "",
    val phone: String? = "",
    val shortName: String? = "",
    val tla: String? = "",
    val venue: String? = "",
    val website: String? = ""
) : Parcelable