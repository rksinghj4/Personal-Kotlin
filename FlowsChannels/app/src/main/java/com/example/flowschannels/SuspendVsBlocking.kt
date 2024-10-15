package com.example.flowschannels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

private suspend fun longRunningTask() {
    withContext(Dispatchers.IO) {
        //Long running task
        delay(5000)
    }
}

private val  dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private fun suspendingFunction() {
    CoroutineScope(dispatcher).launch {
        println("Task1: Before longRunningTask")
        longRunningTask()//Suspension point: underlying thread is free to do other task
        println("Task1: After longRunningTask")
    }

    CoroutineScope(dispatcher).launch {
        println("Task2: Before longRunningTask")
        longRunningTask()//Suspension point: underlying thread is free to do other task
        println("Task2: After longRunningTask")
    }
}

private fun blockingFunction() {
    CoroutineScope(dispatcher).launch {
        runBlocking {
            println("Task1: Before longRunningTask")
            longRunningTask()//Blocking point: underlying thread is blocked because of runBlocking
            println("Task1: After longRunningTask")
        }
    }

    CoroutineScope(dispatcher).launch {
        runBlocking {
            println("Task2: Before longRunningTask")
            longRunningTask()//Blocking point: underlying thread is blocked because of runBlocking
            println("Task2: After longRunningTask")
        }
    }
}

private fun main() {
    //suspendingFunction()
    blockingFunction()
}