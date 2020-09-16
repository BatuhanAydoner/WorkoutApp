package com.moonturns.workoutapp

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButtonClickEvent()
        llCalculatorClickEvent()
        llHistoryClickEvent()
    }

    // llStart click event from activity_main.xml
    private fun startButtonClickEvent() {
        llStart.setOnClickListener {
            goToExerciseActivity()
        }
    }

    // llCalculator click event listener
    private fun llCalculatorClickEvent() {
        llCalculator.setOnClickListener {
            goToCalculatorActivity()
        }
    }

    // llHistory click event listener
    private fun llHistoryClickEvent() {
        llHistory.setOnClickListener {
            goToHistoryActivity()
        }
    }

    // Uses intent and open ExerciseActivity
    private fun goToExerciseActivity() {
        var intent = Intent(this, ExerciseActivity::class.java)
        startActivity(intent)
    }

    // Uses intent and open CalculatorActivity
    private fun goToCalculatorActivity() {
        var intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }

    // Uses intent and open HistoryActivity
    private fun goToHistoryActivity() {
        var intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }
}