package com.example.genericsandvariancedemo.function

inline fun higherfunc( str : String, mycall :(String)-> Unit) {

    // inovkes the print() by passing the string str
    mycall(str)
}

// main function
fun main(args: Array<String>) {
    print("GeeskforGeeks: ")
    higherfunc("A Computer Science portal for Geeks",::print)
}