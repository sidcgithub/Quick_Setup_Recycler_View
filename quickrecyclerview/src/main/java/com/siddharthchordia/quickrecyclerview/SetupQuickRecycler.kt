package com.siddharthchordia.quickrecyclerview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SetupQuickRecycler {

    companion object
    {
        fun setupRecyclerView(context:Context,recyclerView: RecyclerView, listener:OnQuickItemClickListener,dataArray:MutableList<QuickItem>)
        {
            var viewManager = LinearLayoutManager(context)
            var viewAdapter = QuickAdapter(dataArray,listener)

            recyclerView.apply {
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                setHasFixedSize(true)

                // use a linear layout manager
                layoutManager = viewManager

                // specify an viewAdapter (see also next example)
                adapter = viewAdapter

            }

        }
    }
}