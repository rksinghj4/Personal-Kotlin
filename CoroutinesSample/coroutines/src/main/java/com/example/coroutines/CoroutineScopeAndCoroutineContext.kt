package com.example.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {

    /**
     * this - referenced to the CoroutineScope object
     * runBlocking: BlockingCoroutine{Active}@21213b92
     */
    println("runBlocking: $this")
    println("runBlocking Coroutine context: $coroutineContext")//[BlockingCoroutine{Active}@21213b92, BlockingEventLoop@66480dd7]

    launch {
        /**
         * It launches the new coroutine
         * this - referencing to the newly launched CoroutineScope object,
         * Parent launch: StandaloneCoroutine{Active}@627551fb
         */
        println("Parent launch: $this")
        println("Parent launch Coroutine context: $coroutineContext")//[StandaloneCoroutine{Active}@578486a3, BlockingEventLoop@66480dd7]

        launch {
            /**
             * this - referencing to the newly launched CoroutineScope object,
             * which is different form it's parent.
             * Child launch: StandaloneCoroutine{Active}@578486a3
             * When statement was printed on console StandaloneCoroutine was active.
             */
            println("Child launch: $this")
            println("Child launch Coroutine context: $coroutineContext")
        }
    }

    async {
        /**
         * this - referencing to the newly launched CoroutineScope object,
         * async: DeferredCoroutine{Active}@2758fe70
         */
        println("async: $this") //this - referenced to the CoroutineScope object
        println("async Coroutine context: $coroutineContext")
    }

    println("Last statement should return the Unit, because main return the unit")

}