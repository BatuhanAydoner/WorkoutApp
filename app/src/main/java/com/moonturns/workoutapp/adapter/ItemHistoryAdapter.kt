package com.moonturns.workoutapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moonturns.workoutapp.R
import com.moonturns.workoutapp.database.WorkoutAppEntity
import kotlinx.android.synthetic.main.item_history.view.*

class ItemHistoryAdapter(var historyList: ArrayList<WorkoutAppEntity>): RecyclerView.Adapter<ItemHistoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.txtDate.text = historyList[position].completedDate
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}