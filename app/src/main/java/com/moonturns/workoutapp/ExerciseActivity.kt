package com.moonturns.workoutapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.moonturns.workoutapp.adapter.ItemExerciseAdapter
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlin.time.measureTime

class ExerciseActivity : AppCompatActivity() {

    private var readyTimer: CountDownTimer? = null
    private var exerciseTimer: CountDownTimer? = null
    private val TIME_GET_READY = 1000L // Before exercise starts
    private val TIME_EXERCISE = 30000L // Exercise duration
    private val COUNTDOWN_INTERVAL = 1000L

    private var restProgress = 10

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1 // Current image position

    private var tts: TextToSpeech? = null // Text to speech for next exercise name
    private var mediaPlayer: MediaPlayer? = null

    private var itemExerciseAdapter: ItemExerciseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setupToolbar()
        flTimerClickEventListener()

        exerciseList = Constants.defaultLExerciseList()

        txtNextExerciseName.text = exerciseList!![currentExercisePosition + 1].name

        itemExerciseAdapter = ItemExerciseAdapter(exerciseList!!)
        initExerciseRecyclerview()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (readyTimer != null) {
            readyTimer?.cancel()
        }

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
        }

        if (tts!!.isSpeaking) {
            tts?.stop()
            tts?.shutdown()
        }

        if (mediaPlayer != null) {
            mediaPlayer?.stop()
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
                exerciseSound()
                initReadyTimer()
            }
        }
    }

    // Start first timer
    // 10 seconds
    private fun initReadyTimer() {
        exerciseNameToSpeech()
        readyTimer = object : CountDownTimer(TIME_GET_READY, COUNTDOWN_INTERVAL) {
            override fun onFinish() {
                if (currentExercisePosition < exerciseList!!.size - 1) {
                    setProgressBarTimer(30, 30)
                    visibleExerciseImage()
                    initExerciseTimer()
                    stateNextExerciseName(false)
                    changeCurrentItemBackground()
                }
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
                if (currentExercisePosition < exerciseList!!.size - 1) {
                    invisibleExerciseImage()
                    setProgressBarTimer(10, 10)
                    readyTimer?.start()
                    stateNextExerciseName(true)
                    exerciseNameToSpeech()
                    exerciseSound()
                }else {
                    goToFinishActivity()
                }
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

    // When ready timer is finished, imgExercise becomes VISIBLE ans set an image
    private fun visibleExerciseImage() {
        currentExercisePosition++
        imgExercise.visibility = View.VISIBLE
        imgExercise.setImageDrawable(ContextCompat.getDrawable(this, exerciseList!![currentExercisePosition].image))
        txtExerciseName.text = exerciseList!![currentExercisePosition].name
    }

    // When exercise timer is finished, imgExercise becomes GONE
    private fun invisibleExerciseImage() {
        imgExercise.visibility = View.GONE
        txtExerciseName.text = "GET READY FOR"
        txtNextExerciseName.text = exerciseList!![currentExercisePosition].name
    }

    private fun setProgressBarTimer(rest: Int, max: Int) {
        restProgress = rest
        pbTimer.max = max
    }

    // If Exercise image is visible, exercise name and description is invisible
    private fun stateNextExerciseName(visible: Boolean) {
        if (visible) {
            txtUpcoming.visibility = View.VISIBLE
            txtNextExerciseName.visibility = View.VISIBLE
        }else {
            txtUpcoming.visibility = View.GONE
            txtNextExerciseName.visibility = View.GONE
        }
    }

    // Text to speech for next exercise name.
    private fun exerciseNameToSpeech() {
        tts = TextToSpeech(this, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {
                var result = tts?.setLanguage(Locale.US)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("myApp", "Language is not supported")
                }

            }else {
                Log.e("myApp", "Initialization failed")
            }
        })
        var exerciseName = txtNextExerciseName.text
        var speakText = "Upcoming exercise " + exerciseName
        tts?.speak(speakText, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    // This function uses mediaplayer before exercise
    private fun exerciseSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.press_start)
        mediaPlayer?.start()
    }

    // Setup recyclerview for activity_exercise
    private fun initExerciseRecyclerview() {
        rvExerciseStatus.apply {
            adapter = itemExerciseAdapter
            layoutManager = LinearLayoutManager(this@ExerciseActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    // When a exercise is finished, marks an item as completed
    private fun changeCurrentItemBackground() {
        var view = rvExerciseStatus.layoutManager!!.getChildAt(currentExercisePosition)
        view?.background = ContextCompat.getDrawable(this, R.drawable.item_exercise_border)

    }

    // Open FinishActivity.
    private fun goToFinishActivity() {
        finish()
        var intent = Intent(this, FinishActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}