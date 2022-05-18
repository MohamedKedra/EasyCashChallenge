package com.example.easycashchallenge.network.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.easycashchallenge.utils.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constants.DB.competitionIdsTable)
data class ID(
    @PrimaryKey
    val id: Int
) : Parcelable
