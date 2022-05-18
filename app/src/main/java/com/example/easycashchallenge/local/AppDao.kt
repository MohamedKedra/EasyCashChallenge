package com.example.easycashchallenge.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.easycashchallenge.network.models.Competition
import com.example.easycashchallenge.network.models.Team
import com.example.easycashchallenge.utils.Constants
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AppDao {

    @Query("select * from ${Constants.DB.competitionTable}")
    fun getAllCompetitions(): Single<List<Competition>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllCompetitions(competitions: List<Competition>): Completable

    @Query("select * from ${Constants.DB.teamTable}")
    fun getAllTeams(): Single<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllTeams(teams: List<Team>) : Completable
}