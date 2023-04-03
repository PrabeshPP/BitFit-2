package com.example.bitfit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DashBoardAdapterAdapter(private val items:List<Food>): RecyclerView.Adapter<DashBoardAdapterAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val maximumTextView: TextView
        val minimumTextView: TextView


        init {
            maximumTextView=itemView.findViewById(R.id.textViewMax)
            minimumTextView=itemView.findViewById(R.id.textViewMin)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context=parent.context
        val inflater= LayoutInflater.from(context)
        val contactView=inflater.inflate(R.layout.food_item,parent,false)
        return ViewHolder(contactView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.maximumTextView.text=items.maxWith(Comparator.comparingInt { it.calories?.toInt() ?: 0 }).toString()
        holder.minimumTextView.text=items.minWith(Comparator.comparingInt { it.calories?.toInt() ?: 0 }).toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}