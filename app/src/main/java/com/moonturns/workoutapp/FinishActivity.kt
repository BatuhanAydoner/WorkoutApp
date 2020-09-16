package com.moonturns.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.moonturns.workoutapp.database.WorkoutAppDatabase
import com.moonturns.workoutapp.database.WorkoutAppEntity
import kotlinx.android.synthetic.main.activity_finish.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        setSupportActionBar(tb_FinishActivity)
        var action = supportActionBar
        if (action != null) {
            action.setDisplayHomeAsUpEnabled(true)
        }

        tb_FinishActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnFinishClickEvent()

        saveCompletedExerciseToRoomDatabase()
    }

    private fun btnFinishClickEvent() {
        btnFinish.setOnClickListener {
            onBackPressed()
        }
    }

    // When user comes to end of exercises, completed exercise will be saved.
    private fun saveCompletedExerciseToRoomDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            var added = -1L
            withContext(Dispatchers.IO) {
                var calendar = Calendar.getInstance()
                var dateTime = calendar.time

                var formatter = SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())
                var workoutAppDatabaseModel = WorkoutAppEntity(0, formatter.format(dateTime))

                var workoutAppDao = WorkoutAppDatabase.invoke(this@FinishActivity).workoutAppDao()
                added = workoutAppDao.add(workoutAppDatabaseModel)
            }
        }
    }
}