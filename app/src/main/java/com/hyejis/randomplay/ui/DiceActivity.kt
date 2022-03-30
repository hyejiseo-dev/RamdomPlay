package com.hyejis.randomplay.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyejis.randomplay.R
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> {
            val nextIntent = Intent(this, DiceActivity::class.java)
            startActivity(nextIntent)
            true
        }
        R.id.action_profile -> {
            val nextIntent = Intent(this, RandomNumActivity::class.java)
            startActivity(nextIntent)
            true
        }
        R.id.action_setting -> {
            val nextIntent = Intent(this, TodayEatActivity::class.java)
            startActivity(nextIntent)
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}