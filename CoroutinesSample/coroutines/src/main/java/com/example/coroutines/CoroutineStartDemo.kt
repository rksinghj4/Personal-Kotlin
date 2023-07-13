package com.example.coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking {//Sequential code execution

    println("Start main")

    /*val time = measureTimeMillis { //Sequential code execution
        val one = doSomethingOne() // 1st execute doSomethingOne
        val two = doSomethingTwo() // then execute doSomethingTwo
        println("${one} and  ${two}")

    }*/

    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingOne() } //  Parallel execution with other
        val two = async { doSomethingTwo() }  // Parallel execution with other
        val three = async(start = CoroutineStart.ATOMIC) { doSomethingThree() }  // Parallel execution with others
        try {
            //one.cancel()
            println(" ${one.await()},  ${two.await()},  ${three.await()}  ")
        } catch (e: CancellationException) {
            println("Cancellation: $e")
        }
    }

    println("Time taken $time")

    println("End main")
}

suspend fun doSomethingOne(): String {
    println("Start doSomethingOne")
    delay(1000)
    println("End doSomethingOne")
    return "One"
}

suspend fun doSomethingTwo(): String {
    println("Start doSomethingTwo")
    delay(1000)
    println("End doSomethingTwo")
    return "Two"
}

suspend fun doSomethingThree(): String {
    println("Start doSomethingThree")
    delay(1000)
    println("End doSomethingThree")
    return "Three"
}