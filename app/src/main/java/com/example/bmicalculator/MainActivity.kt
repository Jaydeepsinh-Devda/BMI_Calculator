package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val btnResult = findViewById<Button>(R.id.calculate)

        btnResult.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(validateInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                // To get 2 Digit Decimal Number
                val bmi2Digit = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digit)
            }
        }
    }

    private fun validateInput(weight:String?, height:String?):Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight Field id Empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height Field id Empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDes = findViewById<TextView>(R.id.tvResult)
        val resultInfo = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        resultInfo.text = "Normal range is 18.5 - 24.9"

        var resultText = ""
        var color = 0


        when{
            bmi<18.5 ->{
                resultText = "Undeweight"
                color = R.color.under_weight
            }
            bmi in 18.5..24.9 ->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.0..29.9 -> {
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmi>30.0 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }

        resultDes.setTextColor(ContextCompat.getColor(this,color))
        resultDes.text = resultText
    }
}
