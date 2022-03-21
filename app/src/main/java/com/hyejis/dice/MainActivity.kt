package com.hyejis.dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hyejis.dice.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val diceImage1 = binding.dice1
        val diceImage2 = binding.dice2

        binding.diceStartBtn.setOnClickListener {

            val num1 = Random.nextInt(1,6)
            val num2 = Random.nextInt(1,6)

            when(num1){
                1 -> diceImage1.setImageResource(R.drawable.dice_1)
                2 -> diceImage1.setImageResource(R.drawable.dice_2)
                3 -> diceImage1.setImageResource(R.drawable.dice_3)
                4 -> diceImage1.setImageResource(R.drawable.dice_4)
                5 -> diceImage1.setImageResource(R.drawable.dice_5)
                6 -> diceImage1.setImageResource(R.drawable.dice_6)
            }

            when(num2){
                1 -> diceImage2.setImageResource(R.drawable.dice_1)
                2 -> diceImage2.setImageResource(R.drawable.dice_2)
                3 -> diceImage2.setImageResource(R.drawable.dice_3)
                4 -> diceImage2.setImageResource(R.drawable.dice_4)
                5 -> diceImage2.setImageResource(R.drawable.dice_5)
                6 -> diceImage2.setImageResource(R.drawable.dice_6)
            }

            binding.diceSum.text = "총 합: "+(num1 + num2).toString()
        }


        diceImage1.setImageResource(R.drawable.dice_1)
        diceImage2.setImageResource(R.drawable.dice_2)
    }
}