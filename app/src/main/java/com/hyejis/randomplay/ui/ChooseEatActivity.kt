package com.hyejis.randomplay.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import com.hyejis.randomplay.utils.VerticalItemDecorator
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyejis.randomplay.R
import com.hyejis.randomplay.databinding.ActivityChooseEatBinding
import com.hyejis.randomplay.todayeat.EatItemAdapter
import com.hyejis.randomplay.todayeat.EatList
import com.hyejis.randomplay.utils.HorizontalItemDecorator
import com.hyejis.randomplay.viewmodel.EatViewModel

class ChooseEatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseEatBinding

    var datas = mutableListOf<EatList>()

    lateinit var categories: String

    private val viewModel by lazy {
        ViewModelProvider(this, EatViewModel.Factory(application)).get(EatViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_eat)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this  //live data 사용할 때

        categories = intent.getStringExtra("category").toString()
        binding.what.text = "오늘 먹을 $categories 메뉴는?"

        val mAdapter = EatItemAdapter(this)
        binding.eatList.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        if(categories == "모든음식"){
            //저장된 메뉴 모두 가져오기
            viewModel.getAll().observe(this, Observer { users ->
                // Update the cached copy of the users in the adapter.
                users?.let {
                    binding.what.text = "오늘 먹을 메뉴는?"
                    mAdapter.setItems(it)
                    datas = users as MutableList<EatList>
                }
            })

        }else{
            //필터링 해서 보여준다
            viewModel.getFoodCategory(categories).observe(this, Observer { users ->
                // Update the cached copy of the users in the adapter.
                users?.let {
                    mAdapter.setItems(it)
                    datas = users as MutableList<EatList>
                }
            })
        }

        binding.add.setOnClickListener {
            val dlg = EatDialog(this, categories)
            dlg.show()
        }

        binding.eatList.addItemDecoration(VerticalItemDecorator(10))
        binding.eatList.addItemDecoration(HorizontalItemDecorator(10))

        //아이템 클릭 동작
        mAdapter.setItemClickListener(object : EatItemAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this@ChooseEatActivity)
                builder.setTitle("삭제")
                builder.setMessage("메뉴룰 삭제 하시겠습니까?")

                builder.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                    viewModel.delete(datas[position])
                    mAdapter.notifyDataSetChanged()
                    builder.create().dismiss()
                })

                builder.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->
                    builder.create().dismiss()
                })
                builder.show()
            }
        })

        //음식 랜덤 뽑기
        binding.replay.setOnClickListener {
            binding.result.text = datas.random().food
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

    //스와이프 시 삭제
//        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val pos = viewHolder.adapterPosition
//                Log.d("tag","click item... $pos")
//              //  mAdapter.datas.removeAt(pos)
//                mAdapter.notifyDataSetChanged()
//            }
//        }
//
//        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
//        itemTouchHelper.attachToRecyclerView(binding.eatList)

}

