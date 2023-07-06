package com.example.coroutines

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.EmptyCoroutineContext

class MyViewModel: ViewModel() {
    companion object {
        val TAG = "MyViewModel"
    }

    fun execute() {
        /* The summary of coroutine start options is:
        * * [DEFAULT] -- immediately schedules coroutine for execution according to its context;
        * * [LAZY] -- starts coroutine lazily, only when it is needed;
        * * [ATOMIC] -- atomically (in a non-cancellable way) schedules coroutine for execution according to its context;
        * * [UNDISPATCHED] -- immediately executes coroutine until its first suspension point _in the current thread_.
        */
        GlobalScope.launch(EmptyCoroutineContext, CoroutineStart.DEFAULT) {
            //Thread.Sleep(5000) is different from delay(5000), because delay method suspends only current coroutine not the entire thread
            //delay(5000)
            Log.d(TAG, "CoroutineStart.DEFAULT, GlobalScope executes in Thread: " + Thread.currentThread().name)
        }

        GlobalScope.launch(EmptyCoroutineContext, CoroutineStart.DEFAULT) {
            //Thread.Sleep(5000) is different from delay(5000), because delay method suspends only current coroutine not the entire thread
            //delay(5000)
            Log.d(TAG, "CoroutineStart.DEFAULT, GlobalScope2 executes in Thread: " + Thread.currentThread().name)
        }


        GlobalScope.launch(EmptyCoroutineContext, CoroutineStart.UNDISPATCHED) {
            Log.d(TAG, "CoroutineStart.UNDISPATCHED, GlobalScope executes in Thread: " + Thread.currentThread().name)
        }

        /*// lifecycleScope can not be access in ViewModel
        lifecycleScope.launch(EmptyCoroutineContext, CoroutineStart.DEFAULT) {

        }*/

        viewModelScope.launch(EmptyCoroutineContext, CoroutineStart.DEFAULT) {
            withContext(Dispatchers.IO) {
                Log.d(TAG, "CoroutineStart.DEFAULT, Dispatchers.IO, viewModelScope executes in Thread: " + Thread.currentThread().name)
            }
        }

        viewModelScope.launch(EmptyCoroutineContext, CoroutineStart.DEFAULT) {
            Log.d(TAG, "CoroutineStart.DEFAULT, viewModelScope executes in Thread: " + Thread.currentThread().name)
        }

        /*viewModelScope.launch {// removed parameters

        }*/

        Log.d(TAG, "execute fun executes in Thread: " + Thread.currentThread().name)
    }

}