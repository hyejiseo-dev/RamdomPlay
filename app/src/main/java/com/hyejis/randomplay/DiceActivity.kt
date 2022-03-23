package com.hyejis.randomplay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyejis.randomplay.databinding.ActivityDiceBinding
import kotlin.random.Random

class DiceActivity:AppCompatActivity(){

    private lateinit var binding: ActivityDiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dice)

        val diceImage1 = binding.dice1
        val diceImage2 = binding.dice2

        //초기 화면
        diceImage1.setImageResource(R.drawable.dice_1)
        diceImage2.setImageResource(R.drawable.dice_1)

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

    }
}