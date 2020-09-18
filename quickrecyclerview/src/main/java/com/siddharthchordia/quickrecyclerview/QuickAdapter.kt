package com.siddharthchordia.quickrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class QuickAdapter(
    private val myDataset: MutableList<QuickItem>,
    val listener: OnQuickItemClickListener
) : RecyclerView.Adapter<QuickAdapter.QuickViewHolder>() {

    val myDatasetCopy: MutableList<QuickItem>

    init {
        myDatasetCopy = mutableListOf()

        myDatasetCopy.addAll(myDataset)


    }

    class QuickViewHolder(val rootItemView: View, val listener: OnQuickItemClickListener, val myDataset: MutableList<QuickItem>,
                          val myDatasetCopy:MutableList<QuickItem>) :
        RecyclerView.ViewHolder(rootItemView), View.OnClickListener {

        init {
            rootItemView.setOnClickListener(this)
        }

        override fun onClick(item: View?) {

            var originalPosition = myDatasetCopy.indexOf(myDataset[adapterPosition])

            listener.onQuickItemClick(item, adapterPosition, rootItemView, originalPosition)
        }

    }

    override fun getItemViewType(position: Int): Int {

        return myDataset[position].layoutId
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuickAdapter.QuickViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return QuickViewHolder(itemView, listener,myDataset, myDatasetCopy)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: QuickAdapter.QuickViewHolder, position: Int) {


        myDataset[position].bindingMap.forEach { (text, viewId) ->
            var view = holder.rootItemView.findViewById<View>(
                viewId
            )
            if ( view is TextView)
            {
                view.text = text
            }
            else
            {

                Glide.with(holder.rootItemView).load(text).into(view as ImageView)
            }
        }

    }

    fun filter(text: String, vararg vId: Int) {
        var text = text
        myDataset.clear()
        if (text.isEmpty()) {
            myDataset.addAll(myDatasetCopy)
        } else {
            text = text.toLowerCase()
            for (item in myDatasetCopy) {

                var flag = false

                item.bindingMap.forEach { key, viewId ->
                    if (vId.contains(viewId)) {
                        if (key.toLowerCase().contains(text)
                        ) {
                            flag = true
                        }
                    }


                }
                if (flag) {
                    myDataset.add(item)
                }


            }
        }
        notifyDataSetChanged()
    }


}