package com.example.simplecalculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.simplecalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String currentString = "";
    boolean typing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        /*xml -> layoutInfalter -> inflate -> decoreview -> window -> setcontent*/
//        LayoutInflater layoutInflater = LayoutInflater.from(this);
//        binding = ActivityMainBinding.inflate(layoutInflater);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

//        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btn0.setOnClickListener(view -> {handleText("0", true);});
        binding.btn1.setOnClickListener(view -> {handleText("1", true);});
        binding.btn2.setOnClickListener(view -> {handleText("2", true);});

        binding.btn3.setOnClickListener(view -> {handleText("3", true);});
        binding.btn4.setOnClickListener(view -> {handleText("4", true);});
        binding.btn5.setOnClickListener(view -> {handleText("5", true);});

        binding.btn6.setOnClickListener(view -> {handleText("6", true);});
        binding.btn7.setOnClickListener(view -> {handleText("7", true);});
        binding.btn8.setOnClickListener(view -> {handleText("8", true);});
        binding.btn9.setOnClickListener(view -> {handleText("9", true);});

        binding.btnDiv.setOnClickListener(view -> {handleText("/", true);});
        binding.btnMul.setOnClickListener(view -> {handleText("*", true);});
        binding.btnSub.setOnClickListener(view -> {handleText("-", true);});
        binding.btnPlus.setOnClickListener(view -> {handleText("+", true);});

        binding.btnEqual.setOnClickListener(v -> {

            int len = currentString.length();

            int total = 0;
            int lastNum = 0;
            int currentNum = 0;
            char operator = '+';

            for (int i = 0; i < len; i++) {
                char c = currentString.charAt(i);

                if (Character.isDigit(c)) {
                    currentNum = currentNum * 10 + (c - '0');
                }

                if (!Character.isDigit(c) || i == len - 1) {

                    if (operator == '+') {
                        total += lastNum;
                        lastNum = currentNum;
                    }
                    else if (operator == '-') {
                        total += lastNum;
                        lastNum = -currentNum;
                    }
                    else if (operator == '*') {
                        lastNum = lastNum * currentNum;
                    }
                    else if (operator == '/') {
                        lastNum = lastNum / currentNum;
                    }

                    operator = c;
                    currentNum = 0;
                }
            }

            total += lastNum;
            binding.display.setText(String.valueOf(total));
        });

        binding.btnBackspace.setOnClickListener(view -> {

            /*substring (starting, sting ka last index)*/

            if(currentString.length() > 1){
                /*last ki value ko remove karana hai*/
                currentString = currentString.substring(0, currentString.length()-1);
                binding.display.setText(currentString);
            }else{
                currentString = "0";
                binding.display.setText(currentString);
            }

        });
        binding.btnClear.setOnClickListener(view -> {
            currentString = "0";
            typing = false;
            binding.display.setText(currentString);
        });

    }

    private void handleText(String number, boolean flag /*true*/) {

        if(!typing){
            currentString = "";
            typing = flag;
        }

        currentString = currentString + number;
        binding.display.setText(currentString);
    }
}