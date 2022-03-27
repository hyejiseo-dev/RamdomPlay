package com.hyejis.randomplay

import com.hyejis.randomplay.utils.VerticalItemDecorator
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.hyejis.randomplay.databinding.ActivityChooseEatBinding
import com.hyejis.randomplay.todayeat.EatItemAdapter
import com.hyejis.randomplay.todayeat.EatList
import com.hyejis.randomplay.utils.SwipeToDeleteCallback
import com.hyejis.randomplay.utils.HorizontalItemDecorator

class ChooseEatActivity:AppCompatActivity() {

    private lateinit var binding:ActivityChooseEatBinding

    lateinit var eatItemAdapter: EatItemAdapter
    val datas = mutableListOf<EatList>()

    private val model: EatViewModel by viewModels()

    lateinit var categories : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_choose_eat)

        categories = intent.getStringExtra("category").toString()

        binding.what.text = "오늘 먹을 $categories 메뉴는?"

        initRecycler()

        binding.add.setOnClickListener {
            var contact = EatList(category="test" ,food = "추가")
            datas.add(contact)

            eatItemAdapter.notifyDataSetChanged()
        }

        //스와이프 시 삭제
//        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val pos = viewHolder.adapterPosition
//                datas.removeAt(pos)
//                eatItemAdapter.notifyItemRemoved(pos)
//            }
//        }
//
//        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
//        itemTouchHelper.attachToRecyclerView(binding.eatList)

        //아이템 클릭 동작
        eatItemAdapter.setItemClickListener(object : EatItemAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                Toast.makeText(v.context, "$position 번째 메뉴 클릭!... ${datas[position]}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun initRecycler() {
        eatItemAdapter = EatItemAdapter(datas)
        binding.eatList.adapter = eatItemAdapter


        datas.apply {
            add(EatList(category = "한식",food = "설렁탕"))
            add(EatList(category = "한식",food = "김치찌개"))
            add(EatList(category = "한식",food = "순대국"))
            add(EatList(category = "양식",food = "까르보나라"))
            add(EatList(category = "중식",food = "짜장면"))
            add(EatList(category = "디저트",food = "크로플"))

            eatItemAdapter.datas = datas
            eatItemAdapter.notifyDataSetChanged()
        }

        binding.eatList.addItemDecoration(VerticalItemDecorator(10))
        binding.eatList.addItemDecoration(HorizontalItemDecorator(10))
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

