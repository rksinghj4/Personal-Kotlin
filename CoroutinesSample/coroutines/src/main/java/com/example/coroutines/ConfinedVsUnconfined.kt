package com.example.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    // this : instance of CoroutineScope
    // coroutineContext : instance of coroutineContext

    /**
     * Without parameter: CONFINED [CONFINED DISPATCHER]
     * Inherits CoroutineContext from immediate parent,
     * Even after delay or any custom suspend function, it continue to run on the same thread
     */
    launch {
        println("C1: ${Thread.currentThread().name}") //Thread: main
        delay(1000)
        println("C1 after delay: ${Thread.currentThread().name}") //Thread: main
    }

    /**
     * With parameter: Dispatcher.Default [Similar to GlobalScope.launch {} ]
     * Gets its own context at Global level, Executes in a separate thread.
     * after delay or suspend function, it may continue to run on the same thread or different thread.
     */
    launch(Dispatchers.Default) {
        println("C2: ${Thread.currentThread().name}") //Thread T1
        delay(2000)
        println("C2 after delay: ${Thread.currentThread().name}") // Thread T1 or some other thread
    }


    /**
     * With parameter: Dispatchers.Unconfined [UNCONFINED DISPATCHER]
     * Inherits CoroutineContext from immediate parent,
     * after delay() or suspend function, it will resume on different thread.
     */
    launch(Dispatchers.Unconfined) {
        println("C3: ${Thread.currentThread().name}") //Thread: main
        delay(1000)
        println("C3 after delay: ${Thread.currentThread().name}") // Run on some different thread T2

        /**
         * Using coroutineContext property to flow context from parent to child.
         * coroutineContext - act as confined
         */
        launch(coroutineContext) {
            println("C4: ${Thread.currentThread().name}") //Thread : same as C3 after delay (i.e T2)
            delay(1000)
            println("C4 after delay: ${Thread.currentThread().name}") //Thread: Same as C4: (i.e before delay, T2)
        }

    }

    /**
     * Using coroutineContext property to flow context from parent to child.
     * coroutineContext - act as confined
     */
    launch(coroutineContext) {
        println("C5: ${Thread.currentThread().name}") //Thread : main
        delay(1000)
        println("C5 after delay: ${Thread.currentThread().name}") //Thread: main
    }

    println("Last statement should return the Unit, because main return the unit")
}