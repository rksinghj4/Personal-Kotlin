package com.raj.kotlinplayground.callbackFlow

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

interface LocationListener {
    fun onLocationUpdate(location: Location)
}

fun test1() {

    // create a location listener
    val locationListener = object : LocationListener {

        override fun onLocationUpdate(location: Location) {
            // do something with the updated location
        }

    }

    // register for location updates
    LocationManager.registerForLocation(locationListener)

    // unregister in onDestroy()
    LocationManager.unregisterForLocation(locationListener)
}

fun test2() {
    GlobalScope.launch {
        getLocationFlow().collect { location ->

        }
    }

}


fun getLocationFlow(): Flow<Location> {
    return callbackFlow {

        val locationListener = object : LocationListener {

            override fun onLocationUpdate(location: Location) {
                trySend(location)
            }

        }

        LocationManager.registerForLocation(locationListener)

        awaitClose {
            LocationManager.unregisterForLocation(locationListener)
        }

    }
}

class Location {}

object LocationManager {

    fun registerForLocation(listener: LocationListener) {

    }

    fun unregisterForLocation(listener: LocationListener) {

    }

}


