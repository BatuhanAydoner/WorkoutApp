package com.moonturns.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finish.*

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
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    private fun btnFinishClickEvent() {
        btnFinish.setOnClickListener {
            onBackPressed()
        }
    }
}