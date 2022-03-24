package com.hyejis.randomplay.todayeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hyejis.randomplay.R

class EatItemAdapter(private val itemList : List<EatList>) : RecyclerView.Adapter<EatItemAdapter.ViewHolder>() {

    var datas = mutableListOf<EatList>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_menu_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datas[position]

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
        holder.apply {
            bind(item)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.item)

        fun bind(item: EatList) {
            txtName.text = item.food
        }
    }

    //ClickListener
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

}