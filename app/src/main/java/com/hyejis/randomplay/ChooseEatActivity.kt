package com.hyejis.randomplay

import com.hyejis.randomplay.utils.VerticalItemDecorator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyejis.randomplay.databinding.ActivityChooseEatBinding
import com.hyejis.randomplay.todayeat.EatItemAdapter
import com.hyejis.randomplay.todayeat.EatList
import com.hyejis.randomplay.utils.HorizontalItemDecorator

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
                viewModel.delete(datas[position])
                mAdapter.notifyDataSetChanged()
                Toast.makeText(
                    v.context,
                    "$position 번째 메뉴 삭제!...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
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

