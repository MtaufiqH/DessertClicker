package com.example.dessertclicker

import android.app.Application
import timber.log.Timber

/**
 * Created By Taufiq on 8/4/2020.
 *
 */

class ClickerApplication : Application(){
	
	
	override fun onCreate() {
		super.onCreate()
		
		Timber.plant(Timber.DebugTree())
		
	}
	
	
}