package com.example.easycashchallenge.utils

class Constants {

    object API {
        const val baseURL = "https://api.football-data.org/v2/"
        const val token = "16ca663d5dc24ef4947f173e6aaf9182"
    }

    object Const {
        const val Competition = "competition"
        const val Team = "team"
    }

    object DB {
        const val appDB = "teams_database"
        const val teamTable = "teams_table"
    }

    object APICaching{
        const val headerCacheControl = "Cache-Control"
        const val headerPargma = "Pragma"
        const val offlineCache = "offlineCache"
    }

    object Converter{
        const val id = "id"
        const val name = "name"
        const val countryCode = "countryCode"
        const val ensignUrl = "ensignUrl"
    }
}