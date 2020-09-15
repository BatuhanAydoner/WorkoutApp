package com.moonturns.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity() {

    private var weight: Float = 0F
    private var height: Float = 0F
    private var bmi: Float = 0F

    private var weightMetric = ""
    private var heightMetric = ""

    private var weightUS = ""
    private var heightUS = ""

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
        changeUnit()
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
        height = etMetricUnitHeight.text.toString().toFloat() / 100

        if (rgUnits.checkedRadioButtonId == R.id.rbMetric) {
            bmi = weight / (height * height)
        } else {
            height *= 100
            bmi = (weight / ((height) * (height))) * 703
        }
    }

    // Display a description to user.
    private fun displayBMIResult() {
        calculate()

        llBMIResult.visibility = View.VISIBLE

        txtBMIResult.text = bmi.toString()
        txtBMILabel.text = BMIDescription.label(bmi)
        txtBMIDescription.text = BMIDescription.description(bmi)

    }

    // Listens to Radiogroup changes.
    private fun changeUnit() {
        rgUnits.setOnCheckedChangeListener { group, checkedId ->
            changeEdittextValues(checkedId)
        }
    }

    // According to radiogroup checked button, changes edittext values.
    private fun changeEdittextValues(checkedId: Int) {
        if (checkedId == R.id.rbMetric) {
            tilMetricUnitWeight.hint = "WEIGHT (in kg)"
            tilMetricUnitHeight.hint = "HEIGHT (in cm)"

            weightUS = etMetricUnitWeight.text.toString()
            heightUS = etMetricUnitHeight.text.toString()

            etMetricUnitWeight.text = Editable.Factory.getInstance().newEditable(weightMetric)
            etMetricUnitHeight.text = Editable.Factory.getInstance().newEditable(heightMetric)
        }else {
            tilMetricUnitWeight.hint = "WEIGHT (in lbs)"
            tilMetricUnitHeight.hint = "HEIGHT (in inch)"

            weightMetric = etMetricUnitWeight.text.toString()
            heightMetric = etMetricUnitHeight.text.toString()

            etMetricUnitWeight.text = Editable.Factory.getInstance().newEditable(weightUS)
            etMetricUnitHeight.text = Editable.Factory.getInstance().newEditable(heightUS)
        }
    }
}