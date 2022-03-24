package com.hyejis.randomplay

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyejis.randomplay.databinding.ActivityChooseEatBinding

class ChooseEatActivity:AppCompatActivity() {

    private lateinit var binding:ActivityChooseEatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_choose_eat)

        val category = intent.getStringExtra("category")

        binding.what.text = "오늘 먹을 $category 메뉴는?"

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}