package com.example.simplecalculator

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.simplecalculator.databinding.ActivityMainBinding

class MainActivityKotlin : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var currentString: String = "0"
    var typing : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* setContentView(R.layout.activity_main)*/

        /*xml -> LayoutInflater -> inflater -> decoreView -> window -> setcontent*/

        val layoutinfalter = LayoutInflater.from(this)

        binding = ActivityMainBinding.inflate(layoutinfalter)
        setContentView(binding.root)
        binding.btn0.setOnClickListener { handleText("0", true) }
        binding.btn1.setOnClickListener {
            handleText("1", true)
        }
        binding.btn2.setOnClickListener {
            handleText("2", true)
        }
        binding.btn3.setOnClickListener { handleText("3", true)}
        binding.btn4.setOnClickListener { handleText("4", true)}
        binding.btn5.setOnClickListener { handleText("5", true)}
        binding.btn6.setOnClickListener { handleText("6", true)}
        binding.btn7.setOnClickListener { handleText("7", true)}
        binding.btn8.setOnClickListener { handleText("8", true)}
        binding.btn9.setOnClickListener { handleText("9", true)}
        binding.btnDiv.setOnClickListener { handleText("/", true)}
        binding.btnMul.setOnClickListener { handleText("*", true)}
        binding.btnSub.setOnClickListener { handleText("-", true)}

        binding.btnPlus.setOnClickListener { handleText("+", true)}
        binding.btnEqual.setOnClickListener {

            var currentNumber = 0
            var operator: Char = '+'
            var lastNumber = 0
            var ans = 0
            val len = currentString.length

            for (i in 0 until len) {
                val char = currentString[i]

                if (char.isDigit()) {
                    currentNumber = currentNumber * 10 + (char - '0')
                }

                if (!char.isDigit() || i == currentString.length-1) {
                    if (operator == '+') {
                        ans = ans + lastNumber
                        lastNumber = currentNumber
                    }
                    else if (operator == '-') {
                        ans = ans + lastNumber
                        lastNumber = -currentNumber
                    }
                    else if (operator == '*') {
                        lastNumber = lastNumber * currentNumber
                    }
                    else if (operator == '/') {
                        lastNumber = lastNumber / currentNumber
                    }

                    currentNumber = 0
                    operator = char
                }
            }

            ans = ans + lastNumber
            binding.display.text = ans.toString()
        }
        binding.btnClear.setOnClickListener {
            currentString = "0"
            typing = false
            binding.display.setText(currentString)
        }

        binding.btnBackspace.setOnClickListener {
            if(currentString.length > 1){
                currentString = currentString.substring(0, currentString.length - 1)
                binding.display.setText(currentString)
            }else {
                currentString = "0"
                typing = false
                binding.display.setText(currentString)
            }
        }
    }

    private fun handleText(number: String, flag: Boolean) {

        if(!typing){
            currentString = ""
            typing = flag
        }

        currentString = currentString + number
        binding.display.setText(currentString)
    }
}


