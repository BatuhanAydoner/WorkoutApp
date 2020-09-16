package com.moonturns.workoutapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WorkoutAppEntity::class), version = 1)
abstract class WorkoutAppDatabase: RoomDatabase() {
    abstract fun workoutAppDao(): WorkoutAppDao

    companion object {
        @Volatile
        private var instance: WorkoutAppDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(context, WorkoutAppDatabase::class.java, "dogdatabase").build()
    }
}