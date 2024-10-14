package com.example.flowschannels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


/**
 * MutableStateFlow generate a hot flow.
 * 1.Needs an initial value and emits it as soon as the collector starts collecting.
 * 2. It emits data even when there is no collector.
 * 3. It can store the data
 * 4. It can have multiple collector. (1 to N)
 * 5. It has value property, which keeps history of last known value.
 * 6. It does not emit consecutive repeated values.
 * 7. It emits last know value.
 * 8. Similar to LiveData except for the Lifecycle awareness of the Android component.
 * We should use repeatOnLifecycle scope with StateFlow to add the Lifecycle awareness to it,
 * then it will become exactly like LiveData.
 */
private suspend fun producer(): MutableStateFlow<Int> {
    val mutableStateFlow = MutableStateFlow<Int>(-1)// initial vale here
    CoroutineScope(Dispatchers.IO).launch {
        repeat(5) {
            println("Emitted: $it")
            mutableStateFlow.value = it
            delay(1000)
        }
    }

    return mutableStateFlow
}


fun hotFlowWithMutableStateFlow() = runBlocking {
    withContext(Dispatchers.IO) {
        val data: MutableStateFlow<Int> = producer()
        println("Consumer Coroutine: Launched")
        CoroutineScope(this.coroutineContext).launch {
            data.collect { value ->
                println("Collected 1st: $value")
            }
        }
        delay(5000)
        CoroutineScope(this.coroutineContext).launch {
            data.collect { value ->
                println("Collected 2nd: $value")
            }
        }
    }
}

private fun main() {
    println("Calling: hotFlow()")
    //hotFlowWithMutableStateFlow()
    hotFlowWithMutableSharedFlow()
}


suspend fun producerWithMutableSharedFlow(): MutableSharedFlow<Int> {
    val mutableSharedFlow = MutableSharedFlow<Int>(replay = 10)//No initial vale here
    CoroutineScope(Dispatchers.IO).launch {
        repeat(5) {
            delay(1000)
            println("Emitted: $it")
            mutableSharedFlow.emit(it)//value property is not there.
        }
    }

    return mutableSharedFlow
}

fun hotFlowWithMutableSharedFlow() = runBlocking {
    withContext(Dispatchers.IO) {
        val data: MutableSharedFlow<Int> = producerWithMutableSharedFlow()
        println("Consumer Coroutine: Launched")
        CoroutineScope(this.coroutineContext).launch {
            data.collect { value ->
                println("Collected 1st: $value")
            }
        }
        delay(6000)
        CoroutineScope(this.coroutineContext).launch {
            data.collect { value ->
                println("Collected 2nd: $value")
            }
        }
    }
}