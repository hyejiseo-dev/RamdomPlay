package com.hyejis.randomplay

import com.hyejis.randomplay.utils.VerticalItemDecorator
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyejis.randomplay.databinding.ActivityChooseEatBinding
import com.hyejis.randomplay.todayeat.EatItemAdapter
import com.hyejis.randomplay.todayeat.EatList
import com.hyejis.randomplay.utils.HorizontalItemDecorator
import com.hyejis.randomplay.utils.SwipeToDeleteCallback

class ChooseEatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseEatBinding

   // lateinit var eatItemAdapter: EatItemAdapter
    val datas = mutableListOf<EatList>()

    lateinit var categories: String
 //   private lateinit var viewModel: EatViewModel
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

       // initRecycler()

        val mAdapter = EatItemAdapter(this)
        binding.eatList.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.items.observe(this, Observer { users ->
            // Update the cached copy of the users in the adapter.
            users?.let {
                mAdapter.setItems(it)
            }
        })

        binding.add.setOnClickListener {
            val dlg = EatDialog(this)
            dlg.show()
//            var contact = EatList(category = "test", food = "추가")
//            datas.add(contact)
//            eatItemAdapter.notifyDataSetChanged()
        }

        binding.eatList.addItemDecoration(VerticalItemDecorator(10))
        binding.eatList.addItemDecoration(HorizontalItemDecorator(10))

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

//        //아이템 클릭 동작
        mAdapter.setItemClickListener(object : EatItemAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
             //   viewModel.delete(mAdapter.items.get(position))
                //여기 다시 작성!
                viewModel.delete(
                    EatList(mAdapter.items.get(position).category,
                        mAdapter.items.get(position).food)
                )
                mAdapter.notifyDataSetChanged()
                Toast.makeText(
                    v.context,
                    "$position 번째 메뉴 클릭!... ${mAdapter.items.get(position)}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }
//
//    private fun initRecycler() {
//        eatItemAdapter = EatItemAdapter(datas)
//        binding.eatList.adapter = eatItemAdapter
//
//
//        datas.apply {
//            add(EatList(category = "한식", food = "설렁탕"))
//            add(EatList(category = "한식", food = "김치찌개"))
//            add(EatList(category = "한식", food = "순대국"))
//            add(EatList(category = "양식", food = "까르보나라"))
//            add(EatList(category = "중식", food = "짜장면"))
//            add(EatList(category = "디저트", food = "크로플"))
//
//            eatItemAdapter.datas = datas
//            eatItemAdapter.notifyDataSetChanged()
//        }
//
//        binding.eatList.addItemDecoration(VerticalItemDecorator(10))
//        binding.eatList.addItemDecoration(HorizontalItemDecorator(10))
//    }

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

