package com.moonturns.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.moonturns.workoutapp.adapter.ItemHistoryAdapter
import com.moonturns.workoutapp.database.WorkoutAppDatabase
import com.moonturns.workoutapp.database.WorkoutAppEntity
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {

    private var itemHistoryAdapter: ItemHistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(tbHistoryActivity)
        var action = supportActionBar
        if (action != null) {
            action.setDisplayHomeAsUpEnabled(true)
        }
        tbHistoryActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        getAllHistory()
    }

    // Init rvHistory
    private fun initRecyclerview() {
        rvHistory.apply {
            adapter = itemHistoryAdapter
            layoutManager =
                LinearLayoutManager(this@HistoryActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    // Get history data from Room database
    private fun getAllHistory() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                itemHistoryAdapter = ItemHistoryAdapter(
                    WorkoutAppDatabase.invoke(this@HistoryActivity).workoutAppDao()
                        .getAll() as ArrayList<WorkoutAppEntity>
                )
            }

            initRecyclerview()
        }
    }
}