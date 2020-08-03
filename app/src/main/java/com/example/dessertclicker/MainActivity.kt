package com.example.dessertclicker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.dessertclicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _revenue = 0
    private var dessertSold = 0

    // contains all views
    private lateinit var binding: ActivityMainBinding


    /** Dessert data class
     *
     * @param imageId
     * @param price
     * @param startProductionsAmount
     * Simple data class that contains dessert.. include image, the price it's sold, and star producion of the dessert
     * */
    data class Dessert(val imageId: Int, val price: Int, val startProductionsAmount: Int)

    // list of the dessert,in order of when they are produced
    private val allDessert = listOf(
        Dessert(R.drawable.cupcake, 5, 0),
        Dessert(R.drawable.donut, 10, 5),
        Dessert(R.drawable.eclair, 15, 20),
        Dessert(R.drawable.froyo, 30, 50),
        Dessert(R.drawable.gingerbread, 50, 100),
        Dessert(R.drawable.honeycomb, 100, 200),
        Dessert(R.drawable.icecreamsandwich, 500, 500),
        Dessert(R.drawable.jellybean, 1000, 1000),
        Dessert(R.drawable.kitkat, 2000, 2000),
        Dessert(R.drawable.lollipop, 3000, 4000),
        Dessert(R.drawable.marshmallow, 4000, 8000),
        Dessert(R.drawable.nougat, 5000, 16000),
        Dessert(R.drawable.oreo, 6000, 20000))


    private var currentDessert = allDessert[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.apply {
            revenue = _revenue
            amountSold = dessertSold


        }
    }
}