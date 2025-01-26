package com.example.genericsandvariancedemo.function

inline fun higherOrderFun(str: String, mycall: (String) -> Unit) {

    // invokes the print() by passing the string str
    mycall(str)
}

/*
val lambda1 = {
    println("Lambda expression 1")
    return// 1. return is not allowed in lambda expression. It gives compile time error.
}
*/

val lambda2 = {
    println("Lambda expression 2")
}

//1. Return is allowed in lambda expression if it is passed as an arguments in inline function only.
//2. Return is not allowed in lambda expression if it is passed as an arguments in non inline function.
// Non inline function -  a function without inline keyword.

/**
 * inline fun + crossinline formal parameter - can't accept a lambda expression with return. Even if it is inline function.
 *
 *inline fun + crossinline formal parameter -  can accept a lambda expression with return@lable, where lable is to nearest block..
 *
 */
inline fun inlinedFun(
    lambda1: () -> Unit,
    noinline lambda2: () -> Unit,
    crossinline lambda3: () -> Unit
) {
    lambda1()
    lambda2()
    lambda3()
}

// main function
fun main(args: Array<String>) {
    higherOrderFun("A Computer Science portal for Geeks", ::print)

    lambda2()
    inlinedFun(
        lambda1 = {
            println("Return is allowed in lambda expression if it is passed as an actual argument to inline function")
            //return //Because of this return - Compiler will not perform inlining copy at call site
            // for succeeded lambda expression.
        },

        lambda2 = {
            println("noinline - parameter is not considered to be inline by compiler so Return is not allowed in lambda expression, ")
            //return // Gives compiler error.
        },

        lambda3 = {
            println("Return is not allowed in lambda expression if it is passed to crossinline parameter of inline function ")
            //return // Gives compiler error.
        }
    )

}