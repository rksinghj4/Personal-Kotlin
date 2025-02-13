package com.raj.kotlinplayground.iflet

import kotlinx.coroutines.runBlocking

data class User(var name: String)

var user: User? = User("Raj")

fun main() {

    runBlocking {

        Thread {
            try {
                Thread.sleep(1000)
            } catch (_: Exception) {

            }
            /**
             * even after null check also It is possible to print if : null
             */
            if (user != null) {
                println("if : $user")
            }
            /**
             * It is more safe because a separate scoped object will be created inside {it -> }
             * which will never be null
             */
            user?.let {
                println("let : $it")//can't be null
            }
        }.start()

        Thread {
            try {
                Thread.sleep(1000)
            } catch (_: Exception) {

            }

            user = null

        }.start()

    }

}