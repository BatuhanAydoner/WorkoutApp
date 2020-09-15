package com.moonturns.workoutapp

// This is class is for BMI result.
class BMIDescription {
    companion object {
        fun label(bmi: Float): String {
            val bmiLabel: String

            if (bmi.compareTo(15f) <= 0) {
                bmiLabel = "Very severely underweight"
            } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
            ) {
                bmiLabel = "Severely underweight"
            } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
            ) {
                bmiLabel = "Underweight"
            } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
            ) {
                bmiLabel = "Normal"
            } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                    bmi,
                    30f
                ) <= 0
            ) {
                bmiLabel = "Overweight"
            } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
            ) {
                bmiLabel = "Obese Class | (Moderately obese)"
            } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
            ) {
                bmiLabel = "Obese Class || (Severely obese)"
            } else {
                bmiLabel = "Obese Class ||| (Very Severely obese)"
            }

            return bmiLabel
        }
        fun description(bmi: Float): String {
            val bmiDescription: String

            if (bmi.compareTo(15f) <= 0) {
                bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
            } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
            ) {
                bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
            } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
            ) {
                bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
            } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
            ) {
                bmiDescription = "Congratulations! You are in a good shape!"
            } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                    bmi,
                    30f
                ) <= 0
            ) {
                bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
            } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
            ) {
                bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
            } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
            ) {
                bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
            } else {
                bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
            }

            return bmiDescription
        }
    }
}