package com.example.easycashchallenge.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.easycashchallenge.network.models.AreaTypeConverter
import com.example.easycashchallenge.network.models.Competition
import com.example.easycashchallenge.network.models.CurrentSeasonTypeConverter
import com.example.easycashchallenge.network.models.Team

@Database(entities = [Team::class, Competition::class], version = 1, exportSchema = false)
@TypeConverters(AreaTypeConverter::class, CurrentSeasonTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val appDao: AppDao
}