package com.moonturns.workoutapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutAppEntity(
    @PrimaryKey(autoGenerate = true)
    var _id: Int,
    @ColumnInfo(name = "completed_date")
    var completedDate: String
) {

}