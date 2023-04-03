package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.bitfit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


private const val TAG = "MainActivity/"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private fun replaceFragment(foodListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.food_frame_layout, foodListFragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        val fragmentManager:FragmentManager=supportFragmentManager

        val foodLogFragment:Fragment=FoodLogFragment()
        val dashBoardFragment:Fragment=DashBoardFragment()

        val bottomNavigationView:BottomNavigationView=findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener {
            item->lateinit var fragment:Fragment
            when(item.itemId){
                R.id.action_log->fragment=foodLogFragment
                R.id.action_dashboard->fragment=dashBoardFragment
            }
            replaceFragment(fragment)
            true
        }

        val button=findViewById<Button>(R.id.button2);
        button.setOnClickListener {
            val intent=Intent(this,AddActivity::class.java)
            this.startActivity(intent)
        }

        bottomNavigationView.selectedItemId=R.id.action_log
    }
}