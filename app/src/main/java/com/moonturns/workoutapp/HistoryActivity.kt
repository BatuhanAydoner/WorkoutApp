package com.moonturns.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(tbHistoryActivity)
        var action = supportActionBar
        if (action != null) {
            action.setDisplayHomeAsUpEnabled(true)
        }
        tbHistoryActivity.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}