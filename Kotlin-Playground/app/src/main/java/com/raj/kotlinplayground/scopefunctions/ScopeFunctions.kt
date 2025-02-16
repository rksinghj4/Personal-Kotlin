package com.raj.kotlinplayground.scopefunctions

/**
 * Inside extension function receiver can be accessed using this keyword.
 * Inside accepted lambda same object can accessed using it
 * OR this
 *
 *  if (T) - it (receiver is passed as parameter to lambda)
 *  if T.() - this
 */
inline fun <T, R> T.myLet(block: (T) -> R): R {
    return block(this)
}

inline fun <T, R> T.myRun(block: T.() -> R): R {
    return block()
}

/**
 * with is not extension function but it accepts extension fun as parameter
 */

inline fun <T, R> myWith(receiver: T, block: T.() -> R): R {
    return receiver.block()//returns result of the block
}

inline fun <T> T.myAlso(block: (T) -> Unit): T {
    block(this)
    return this
}

inline fun <T> T.myApply(block: T.() -> Unit): T {
    block()
    return this
}

/**
 * Hint: Remove T completely,
 * It is not a extension function. It accept a lambda without param
 */
inline fun <R> run(block: () -> R): R {
    return block()
}

data class Employee(val name: String)

private fun main() {
    val employee: Employee? = Employee("Raj").also {
        println("In side also - here we can have some side effect like logging of object initialization")
        //Block accepted by also returns Unit
    }
    employee?.let {
        println(employee.name)
    }

    employee?.myLet {
        println(employee.name)
    }
}
