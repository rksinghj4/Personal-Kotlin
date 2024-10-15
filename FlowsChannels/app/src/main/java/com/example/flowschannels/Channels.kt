package com.example.flowschannels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random


private fun main() {
    val channelCapacity = 2
    val streamLimit = 7
    //val channel = Channel<Int>(channelCapacity)
    //By Default Channel.RENDEZVOUS - no buffer. Producer and consumer are activated in turn.
    //val channel = Channel<Int>()
    //Channel.CONFLATED: Buffer capacity is 1,  onBufferOverflow = DROP_OLDEST.
    val channel = Channel<Int>(Channel.CONFLATED)//Loss of data can be observed.

    runBlocking {
        launch {//Inherit: Run on underlying thread i.e. main
            println("Coroutine 1 running on: ${Thread.currentThread().name}")
            (1..streamLimit).onEach {
                delay(Random.nextLong(100))
                println("Sending $it")
                channel.send(it)
            }
        }

        launch {//Inherit: Run on underlying thread: ie. main
            println("Coroutine 2 running on: ${Thread.currentThread().name}")
            (1..streamLimit).onEach {
                delay(Random.nextLong(1000))
                println("Receiving ${channel.receive()}")
            }

        }
    }

}