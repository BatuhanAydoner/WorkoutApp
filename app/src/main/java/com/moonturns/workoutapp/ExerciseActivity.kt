package com.moonturns.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.toolbar.*

class ExerciseActivity : AppCompatActivity() {

    private var readyTimer: CountDownTimer? = null
    private var exerciseTimer: CountDownTimer? = null
    private val TIME_GET_READY = 10000L // Before exercise starts
    private val TIME_EXERCISE = 30000L // Exercise duration
    private val COUNTDOWN_INTERVAL = 1000L

    private var restProgress = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setupToolbar()
        flTimerClickEventListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (readyTimer != null) {
            readyTimer?.cancel()
        }

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
        }
    }

    // Setup toolbar for this activity
    private fun setupToolbar() {
        setSupportActionBar(toolbarExerciseActivity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        toolbarExerciseActivity.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    // Click event listener for flTimer at activity_exercise
    private fun flTimerClickEventListener() {
        flTimer.setOnClickListener {
            txtTimer.visibility = View.VISIBLE
            if (readyTimer == null) {
                initReadyTimer()
            }
        }
    }

    // Start first timer
    // 10 seconds
    private fun initReadyTimer() {
        readyTimer = object : CountDownTimer(TIME_GET_READY, COUNTDOWN_INTERVAL) {
            override fun onFinish() {
                restProgress = 30;
                pbTimer.max = 30
                initExerciseTimer()
            }

            override fun onTick(millisUntilFinished: Long) {
                txtTimer.text = "" + millisUntilFinished.toInt() / 1000
                setRestProgressBar()
            }
        }.start()
    }

    // Start exercise timer
    // 30 seconds
    private fun initExerciseTimer() {
        exerciseTimer = object : CountDownTimer(TIME_EXERCISE, COUNTDOWN_INTERVAL) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
                txtTimer.text = "" + millisUntilFinished.toInt() / 1000
                setRestProgressBar()
            }
        }.start()
    }

    // Set progressbar
    private fun setRestProgressBar() {
        restProgress--
        pbTimer.progress = restProgress
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}