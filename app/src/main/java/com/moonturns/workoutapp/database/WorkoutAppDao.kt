package com.moonturns.workoutapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutAppDao {
    @Query("SELECT * FROM WorkoutAppEntity")
    suspend fun getAll(): List<WorkoutAppEntity>

    @Insert
    suspend fun add(workoutAppEntity: WorkoutAppEntity): Long
}