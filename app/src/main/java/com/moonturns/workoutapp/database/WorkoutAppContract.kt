package com.moonturns.workoutapp.database

class WorkoutAppContract {
    val DATABASE_NAME = "WorkoutApp.db"
    val DATABASE_VERSION = 1

    class History {
        val TABLE_NAME = "history"
        val COLUMN_ID = "_id"
        val COLUMN_COMPLETED_DATE = "completed_date"
    }
}