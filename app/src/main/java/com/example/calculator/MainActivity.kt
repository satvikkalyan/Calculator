package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private var currentInput: StringBuilder = StringBuilder()
    private var pendingOperation: String? = null
    private var operand1: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView2)

        val buttonIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.dec_button, R.id.clear_button, R.id.flip_button, R.id.per_button,
            R.id.div_button, R.id.mult_button, R.id.sub_button, R.id.plus_button
        )

        buttonIds.forEach { buttonId ->
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                onButtonClick(button.text.toString())
            }
        }

        val eqButton = findViewById<Button>(R.id.eq_button)
        eqButton.setOnClickListener {
            performCalculation()
        }
    }

    private fun onButtonClick(input: String) {
        currentInput.append(input)
        updateDisplay()
    }

    private fun updateDisplay() {
        textView.text = currentInput.toString()
    }

    private fun performCalculation() {
        val input = currentInput.toString()
        if (input.isNotEmpty()) {
            try {
                val result = ExpressionBuilder(input).build().evaluate()
                currentInput.clear()
                currentInput.append(result)
                updateDisplay()
            } catch (e: Exception) {
                // Handle invalid input or calculation errors
                currentInput.clear()
                currentInput.append("Error")
                updateDisplay()
            }
        }
    }
}
