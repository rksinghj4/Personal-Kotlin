package com.example.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Without coroutine:
 * main will exit once all the work inside it is done.
 *
 * Process finished with exit code 0 - will be printed after all thread complete their work.
 * So it's underlying thread will wait for all other threads to complete their work
 *
 * Note: With threads by default main will act as runBlocking.
 */
/*fun main(args: Array<String>) {
    println("Main start: ${Thread.currentThread().name}")
    thread {
        println("Fake work start: ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("Fake work end: ${Thread.currentThread().name}")
    }
    println("Main end: ${Thread.currentThread().name}")
}*/
/**
 * Out put:
 * Main start: main
 * Main end: main
 * Fake work start: Thread-0
 * Fake work start: Thread-0

 * Process finished with exit code 0
 */


/**
 *
 * without runBlocking main function is launching coroutine using GlobalScope.launch { }:
 *
 * main will exit immediately after the underlying thread complete it's own work,
 * without waiting for other children coroutine.
 *
 * It will not wait for child coroutine completion, because outer block is a non coroutine world
 * without runBlocking so it is not blocking the underlying thread until
 * all of it's children coroutine complete their work.
 *
 * Process finished with exit code 0 - will be printed after underlying thread complete it's own work (not the child's work).
 * It means it's underlying thread will not wait for all other coroutine to complete their work.
 *
 * It is unstructured concurrency: because main function is launched without scope - runBlocking or BlockingCoroutine,
 * so main function is exiting before completing it's entire work.
 *
 *
 */
/*
fun main(args: Array<String>) {
    println("Main start: ${Thread.currentThread().name}")
    GlobalScope.launch {
        println("Fake work start: ${Thread.currentThread().name}")
        delay(1000)
        println("Fake work end: ${Thread.currentThread().name}")
    }
    println("Main end: ${Thread.currentThread().name}")
}
*/

/**
 * Out put:
 * Main start: main
 * Main end: main

 * Process finished with exit code 0
 */


/**
 *
 * with runBlocking main function is launching coroutine using GlobalScope.launch { }
 * GlobalScope - gives unstructured concurrency
 *
 * It is unstructured concurrency: because coroutine is launched
 * with "GlobalScope" in a parent scope which is  BlockingCoroutine{Active}@21213b92,
 * so main function/underlying thread will not wait for children to complete.
 */
/*fun main(args: Array<String>) = runBlocking {
    println("Main start: ${Thread.currentThread().name}")
//    val scope = CoroutineScope(Job() + Dispatchers.Default)
    GlobalScope.launch {//Losing the work
        println("Fake work start: ${Thread.currentThread().name}") // thread T1
        delay(100) // here any suspending function can change the thread of succeeding statement execution.
        println("Fake work end: ${Thread.currentThread().name}") // Either on T1 or some other thread
    }
    //job.join() //wait for completion of job
    println("Main end: ${Thread.currentThread().name}")
}*/

/**
 * Out put:
 * Main start: main
 * Main end: main
 * Fake work start: DefaultDispatcher-worker-1

 * Process finished with exit code 0
 */

/**
 *
 * with runBlocking main function is launching coroutine using coroutineScope { }:
 *
 * Process finished with exit code 0 - will be printed after all coroutines complete their work.
 * So it's underlying thread will wait for all other coroutines to complete their work.
 *
 * It is structured concurrency: because coroutine is launched
 * with "coroutineScope" in a parent scope which is  BlockingCoroutine{Active}@21213b92,
 * so main function/underlying thread will wait for completing it's entire work.
 */
fun main(args: Array<String>) = runBlocking {
    println("Main start: ${Thread.currentThread().name}")
//    val scope = CoroutineScope(Job() + Dispatchers.Default)
    coroutineScope {//gives structured concurrency
        println("Fake work start: ${Thread.currentThread().name}")
        delay(100)
        println("Fake work end: ${Thread.currentThread().name}")
    }
    //job.join()
    println("Main end: ${Thread.currentThread().name}")
}

/**
 * Out put:
 * Main start: main
 * Fake work start: main
 * Fake work end: main
 * Main end: main

 * Process finished with exit code 0
 */