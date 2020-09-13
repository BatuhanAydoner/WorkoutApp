package com.moonturns.workoutapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moonturns.workoutapp.R
import kotlinx.android.synthetic.main.item_exercise_status.view.*

class ItemExerciseAdapter(var exerciseItemList: ArrayList<Int>): RecyclerView.Adapter<ItemExerciseAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise_status, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exerciseItemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.txtItem.text = "" + exerciseItemList[position]
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    fun addItem(item: Int) {
        exerciseItemList.add(item)
        notifyDataSetChanged()
    }
}