package com.example.easycashchallenge.network.models

import androidx.room.TypeConverter
import com.example.easycashchallenge.utils.Constants
import org.json.JSONObject

class CurrentSeasonTypeConverter {
    @TypeConverter
    fun fromSource(currentSeason: CurrentSeason): String {
        return JSONObject().apply {
            put(Constants.Converter.id, currentSeason.id ?: 0)
            put(Constants.Converter.currentMatchday, currentSeason.currentMatchday ?: "")
            put(Constants.Converter.startDate, currentSeason.startDate ?: "")
            put(Constants.Converter.endDate, currentSeason.endDate ?: "")
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): CurrentSeason {
        val json = JSONObject(source)
        return CurrentSeason(
            json.getString(Constants.Converter.currentMatchday) ?: "",
            json.getString(Constants.Converter.endDate) ?: "",
            json.getInt(Constants.Converter.id),
            json.getString(Constants.Converter.startDate) ?: ""
        )
    }
}