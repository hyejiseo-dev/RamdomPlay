package com.hyejis.randomplay

import com.hyejis.randomplay.utils.VerticalItemDecorator
import android.os.Bundle
import android.view.MenuItem
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_choose_eat)

        val category = intent.getStringExtra("category")

        binding.what.text = "오늘 먹을 $category 메뉴는?"

        initRecycler()

        binding.add.setOnClickListener {
            var contact = EatList(food = "추가")
            datas.add(contact)

            eatItemAdapter.notifyDataSetChanged()
        }

        //스와이프 시 삭제
        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                datas.removeAt(pos)
                eatItemAdapter.notifyItemRemoved(pos)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.eatList)

        //아이템 클릭시 삭제
//        eatItemAdapter.setItemClickListener(object : EatItemAdapter.OnItemClickListener{
//            override fun onClick(v: View, position: Int) {
//                datas.removeAt(position)
//                eatItemAdapter.notifyDataSetChanged()
//            }
//        })

    }

    private fun initRecycler() {
        eatItemAdapter = EatItemAdapter(datas)
        binding.eatList.adapter = eatItemAdapter

        datas.apply {
            add(EatList(food = "설렁탕"))
            add(EatList(food = "김치찌개"))
            add(EatList(food = "순대국"))
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