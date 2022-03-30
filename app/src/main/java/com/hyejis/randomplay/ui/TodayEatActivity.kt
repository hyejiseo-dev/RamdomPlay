package com.hyejis.randomplay.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyejis.randomplay.R
import com.hyejis.randomplay.databinding.ActivityEatMainBinding

class TodayEatActivity: AppCompatActivity() {

    private lateinit var binding: ActivityEatMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_eat_main)

        var foods = resources.getStringArray(R.array.spinner_menu)
        var select = foods[0]

        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_menu,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.selectMenu.adapter = adapter
        }

        binding.selectMenu.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position != 0)
                   // Toast.makeText(this@TodayEatActivity, foods[position], Toast.LENGTH_SHORT).show()
                    select = foods[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.chooseMenu.setOnClickListener {
            val intent = Intent(applicationContext, ChooseEatActivity::class.java)
            intent.putExtra("category", select)
            startActivity(intent)
        }

        binding.lottieView.playAnimation()


    }
}