package com.moonturns.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity() {

    private var weight: Float = 0F
    private var height: Float = 0F
    private var bmi: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        setSupportActionBar(tb_calculator_activity)
        var actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "CALCULATE BMI"
        }
        tb_calculator_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnBMICalculateClickEvent()
    }

    // btnMBICalculate click event listener.
    private fun btnBMICalculateClickEvent() {
        btnCalculate.setOnClickListener {
            if  (isValid()) {
                displayBMIResult()
            }else {
                Toast.makeText(this, "Fill all values, please", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Edittext areas are filled, method returns true or returns false.
    private fun isValid(): Boolean {
        return !(etMetricUnitWeight.text!!.isEmpty() || etMetricUnitHeight.text!!.isEmpty())
    }

    // Calculate BMI.
    private fun calculate() {
        weight = etMetricUnitWeight.text.toString().toFloat()
        height = etMetricUnitHeight.text.toString().toFloat()

        bmi = weight / (height / 100 * height / 100)
    }

    // Display a description to user.
    private fun displayBMIResult() {
        calculate()

        llBMIResult.visibility = View.VISIBLE

        txtBMIResult.text = bmi.toString()
        txtBMILabel.text = BMIDescription.label(bmi)
        txtBMIDescription.text = BMIDescription.description(bmi)

    }
}