package com.example.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking {
    val numFlow = flowOf(1, 2, 3).onEach {
        println("flowOf int Start: ${System.currentTimeMillis()}")
        delay(1000L)
        println("flowOf int  End: ${System.currentTimeMillis()}")
    }
    val alphabetsFlow = flowOf("a", "b", "c", "d").onEach {
        println("flowOf String Start: ${System.currentTimeMillis()}")
        delay(15000L)
        println("flowOf String End: ${System.currentTimeMillis()}")
    }
    //combineFlows(numFlow, alphabetsFlow)
    mergeFlows(numFlow, alphabetsFlow)
    //zipFlows(numFlow, alphabetsFlow)
}

private suspend fun combineFlows(flow1: Flow<Int>, flow2: Flow<String>) {
   val time =  measureTimeMillis {
        combine(flow1, flow2) { f1, f2 ->
            println("Combine trigger: ${System.currentTimeMillis()}")

            "$f1 -> $f2"
        }.collect {
            println("Combine: ${System.currentTimeMillis()}")
            println(it)
        }
    }
    println("Combine Time taken = $time")
}

private suspend fun zipFlows(flow1: Flow<Int>, flow2: Flow<String>) {
    val time =  measureTimeMillis {
        flow1.zip(flow2) { f1, f2 ->
            println("Zip trigger: ${System.currentTimeMillis()}")

            "$f1 -> $f2"
        }.collect {
            println("Zip : ${System.currentTimeMillis()}")
            println(it)
        }
    }
    println("Zip Time taken = $time")
}


private suspend fun mergeFlows(flow1: Flow<Int>, flow2: Flow<String>) {
    val time =  measureTimeMillis {
        merge(flow1, flow2) .collect {
            println("Merge: ${System.currentTimeMillis()}")
            println(it)
        }
    }
    println("merge Time taken = $time")
}