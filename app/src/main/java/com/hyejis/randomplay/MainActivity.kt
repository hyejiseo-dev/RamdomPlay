package com.hyejis.randomplay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hyejis.randomplay.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btn1.setOnClickListener {
            val nextIntent = Intent(this, DiceActivity::class.java)
            startActivity(nextIntent)
        }

        binding.btn2.setOnClickListener {
            val nextIntent = Intent(this, RandomNumActivity::class.java)
            startActivity(nextIntent)
        }

        binding.btn3.setOnClickListener {
            val nextIntent = Intent(this, TodayEatActivity::class.java)
            startActivity(nextIntent)
        }

        binding.btn4.setOnClickListener {
            msgShow("3클릭..")
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
            msgShow("Dice")
            true
        }
        R.id.action_profile -> {
            msgShow("Profile")
            true
        }
        R.id.action_setting -> {
            msgShow("Setting")
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    fun msgShow(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}