package com.raj.coroutines

fun taskA1(): String {
    return "A1"
}

fun taskA2(): String {
    return "A2"
}

fun taskB1(): String {
    return "B1"
}

fun taskB2(): String {
    return "B2"
}

fun functionA() {
    val a1 = taskA1()
    val a2 = taskA2()
}

fun functionB() {
    val b1 = taskB1()
    val b2 = taskB2()
}

// Assumption: We want to write code that runs in the following order
// taskA1 -> taskB1 -> taskA2 -> taskB2
/**
 * Condition : To achieve above we can't directly call this taskA1() taskB1() taskA2() taskB2()
 */
fun test() {
    // some code here
}

// Given our above assumption, Coroutines will generate code like below using the interface
fun testConverted() {
    /**
     * So here the actual collaboration is happening among tasks
     */
    functionA(1, object : Continuation1 {

        override fun resumeWith(result: String) {
            println(result)
            functionB(1, object : Continuation1 {

                override fun resumeWith(result: String) {
                    println(result)

                    functionA(2, object : Continuation1 {

                        override fun resumeWith(result: String) {
                            println(result)

                            functionB(2, object : Continuation1 {
                                override fun resumeWith(result: String) {
                                    println(result)
                                }

                            })

                        }
                    })

                }
            })

        }
    })
}


fun functionA(case: Int, continuation: Continuation1?) {
    when (case) {
        1 -> {
            val a1 = taskA1()
            continuation?.resumeWith(a1)
        }

        2 -> {
            val a2 = taskA2()
            continuation?.resumeWith(a2)
        }
    }
}

fun functionB(case: Int, continuation: Continuation1?) {
    when (case) {
        1 -> {
            val b1 = taskB1()
            continuation?.resumeWith(b1)
        }

        2 -> {
            val b2 = taskB2()
            continuation?.resumeWith(b2)
        }
    }
}

/**
 * Under the hood Kotlin coroutines uses same Continuation SAM
 */
fun interface Continuation1 {
    fun resumeWith(result: String)
}

private fun main() {
    testConverted()
}