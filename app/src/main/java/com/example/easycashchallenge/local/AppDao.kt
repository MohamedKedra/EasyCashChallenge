package com.example.easycashchallenge.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.easycashchallenge.network.models.Team
import com.example.easycashchallenge.utils.Constants
import io.reactivex.Single

@Dao
interface AppDao {

    @Query("select * from ${Constants.DB.teamTable}")
    fun getAllTeams(): LiveData<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllTeams(teams: List<Team>)
}