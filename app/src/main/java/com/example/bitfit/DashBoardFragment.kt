package com.example.bitfit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text


class DashBoardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var items= mutableListOf<Food>()
        val view= inflater.inflate(R.layout.fragment_dash_board, container, false)
        val minView=view.findViewById<TextView>(R.id.textViewMin)
        val maxView=view.findViewById<TextView>(R.id.textViewMax)
        val totalCaloriesView=view.findViewById<TextView>(R.id.textViewTotalCalories)
        lifecycleScope.launch {
            (requireActivity().application as FoodApplication).db.foodDao().getAll().collect{
                    databaseList->databaseList.map {entity->
                Food(
                    entity.foodName, entity.foodCalories
                )
            }.map {mappedList->
                (items).addAll(listOf(mappedList))
                minView.text=items.minWith(Comparator.comparingInt { it.calories?.toInt() ?: 0 }).calories
                maxView.text=items.maxWith(Comparator.comparingInt { it.calories?.toInt() ?: 0 }).calories
                totalCaloriesView.text=items.map { it.calories?.toInt() ?: 0 }.sum().toString()
            }
            }
        }
        return view
    }

    companion object {
        fun newInstance():DashBoardFragment{
            return DashBoardFragment()
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }
}