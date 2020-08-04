package com.example.dessertclicker

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
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
		Dessert(R.drawable.oreo, 6000, 20000)
	)
	
	
	private var currentDessert = allDessert[0]
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		
		
		binding.apply {
			
			dessertButton.setOnClickListener {
				onDessertClicked()
			}
			
			// set text to the right values
			revenue = _revenue
			amountSold = dessertSold
			
			
			// make sure the correct dessert is showing
			dessertButton.setImageResource(currentDessert.imageId)
		}
		
	}
	
	/**
	 *   update the score when the dessert is clicked. possibly show new dessert
	 * */
	
	private fun onDessertClicked() {
		// update the score
		_revenue += currentDessert.price
		dessertSold++
		
		binding.revenue = _revenue
		binding.amountSold= dessertSold
		
		//show the new dessert
		showCurrentDessert()
		
		
	}
	
	// determine which  dessert to show
	private fun showCurrentDessert() {
		var newDessert = allDessert[0]
		for (dessert in allDessert) {
			if (dessertSold >= dessert.startProductionsAmount) {
				newDessert = dessert
				binding.dessertButton.setImageResource(newDessert.imageId)
				
				// The list of desserts is sorted by startProductionAmount. As you sell more desserts,
				// you'll start producing more expensive desserts as determined by startProductionAmount
				// We know to break as soon as we see a dessert who's "startProductionAmount" is greater
				// than the amount sold.
			} else break
		}
		
		// if the new dessert is actually different from current dessert, update the image
		if (newDessert != currentDessert) {
			currentDessert = newDessert
			binding.dessertButton.setImageResource(newDessert.imageId)
		}
		
	}
	
	
	// menu
	private fun shareMenus() {
		val shareIntent = ShareCompat.IntentBuilder.from(this)
			.setText(getString(R.string.share_text, dessertSold, _revenue))
			.setType("text/plain")
			.intent
		
		try {
			startActivity(shareIntent)
		} catch (ex: ActivityNotFoundException) {
			Toast.makeText(this, getString(R.string.sharing_not_available), Toast.LENGTH_SHORT)
				.show()
		}
	}
	
	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.main_menu, menu)
		return super.onCreateOptionsMenu(menu)
		
		
	}
	
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.share_menu -> shareMenus()
		}
		return super.onOptionsItemSelected(item)
		
	}
}