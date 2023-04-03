package com.example.bitfit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

private const val TAG = "FoodListFragment"
class FoodLogFragment : Fragment() {
    private var items= mutableListOf<Food>()
    private  lateinit var itemsRv: RecyclerView
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val view=inflater.inflate(R.layout.fragment_food_log,container,false)
        // Inflate the layout for this fragment
        val layoutManager=LinearLayoutManager(context)
        itemsRv=view.findViewById(R.id.recyclerViewFood)
        itemsRv.layoutManager=layoutManager
        itemsRv.setHasFixedSize(true)
        foodAdapter=FoodAdapter(items)
        itemsRv.adapter=foodAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            (requireActivity().application as FoodApplication).db.foodDao().getAll().collect{
                    databaseList->databaseList.map {entity->
                Food(
                    entity.foodName, entity.foodCalories
                )
            }.map {mappedList->
                (items).addAll(listOf(mappedList))
                foodAdapter.notifyDataSetChanged()

            }
            }
        }
    }



    companion object {

        fun newInstance():FoodLogFragment{
            return FoodLogFragment()
        }
    }
}