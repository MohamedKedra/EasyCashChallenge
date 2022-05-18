package com.example.easycashchallenge.network.models

import androidx.room.TypeConverter
import com.example.easycashchallenge.utils.Constants
import org.json.JSONObject

class AreaTypeConverter {
    @TypeConverter
    fun fromSource(area: Area): String {
        return JSONObject().apply {
            put(Constants.Converter.id, area.id ?: 0)
            put(Constants.Converter.name, area.name ?: "")
            put(Constants.Converter.countryCode, area.countryCode ?: "")
            put(Constants.Converter.ensignUrl, area.ensignUrl ?: "")
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): Area {
        val json = JSONObject(source)
        return Area(
            json.getInt(Constants.Converter.id),
            json.getString(Constants.Converter.name) ?: "",
            json.getString(Constants.Converter.countryCode) ?: "",
            json.getString(Constants.Converter.ensignUrl) ?: ""
        )
    }
}