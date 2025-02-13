package com.raj.kotlinplayground.debug

var lastName = "Singh"

fun main() {
    /**
     * First one is better approach - because string concatenation is costly operation.
     * and it should happen only when debug is true.
     */
    debug1 { "Raj $lastName" }
    /**
     * Here concatenation happened then passed to function ( with out checking the condition)
     */
    debug2("Raj $lastName")
}

var debug = true

fun debug1(function: () -> String) {
    if (debug) println(function())
}

fun debug2(value: String) {
    if (debug) println(value)
}