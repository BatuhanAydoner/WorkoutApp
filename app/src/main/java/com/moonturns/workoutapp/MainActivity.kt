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
    }

    // llStart click event from activity_main.xml
    private fun startButtonClickEvent() {
        llStart.setOnClickListener {
            goToExerciseActivity()
        }
    }

    // Uses intent and open ExerciseActivity
    private fun goToExerciseActivity() {
        var intent = Intent(this, ExerciseActivity::class.java)
        startActivity(intent, ActivityOptions.makeCustomAnimation(this, R.anim.activity_right_to_left_animation, R.anim.activity_right_to_left_animation).toBundle())
    }
}