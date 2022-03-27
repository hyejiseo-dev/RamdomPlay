package com.hyejis.randomplay.todayeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hyejis.randomplay.R

class EatItemAdapter(private val itemList : List<EatList>) : RecyclerView.Adapter<EatItemAdapter.ViewHolder>() {

    var datas = mutableListOf<EatList>()

    //lateinit 선언, 초기화 필요
    private lateinit var itemClickListener : OnItemClickListener

    //ClickListener
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_menu_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datas[position]

        // 아이템 클릭 동작을 반드시 추가해 줘야함 - setItemClickListener
        holder.itemView.setOnClickListener {
            itemClickListener?.onClick(it, position)
        }

        holder.apply {
            bind(item)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.item)
        private val txtCategory: TextView = itemView.findViewById(R.id.category)
        private val delete: ImageView = itemView.findViewById(R.id.delete_btn)

        fun bind(item: EatList) {
            txtCategory.text = item.category
            txtName.text = item.food
            //삭제버튼
            delete.setOnClickListener {
                item.apply {
                    datas.removeAt(adapterPosition)
                    notifyDataSetChanged()
                }
            }
        }
    }


}