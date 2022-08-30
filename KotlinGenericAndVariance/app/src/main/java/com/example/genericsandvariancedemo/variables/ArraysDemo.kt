package com.example.genericsandvariancedemo.variables

fun main(args: Array<String>) {

    val arr1 = arrayOf(10, 20, 30)
    val arr2 = arrayOf<Int>(1, 2, 3)

    //Accessing array without index
    for (num in arr1) {
        print(num)
    }
    println()
    //Accessing array with index
    for (i in arr1.indices) {
        print(arr1[i])
    }
    println()
    //Accessing array using withIndex() method
    for ((index, value) in arr1.withIndex()) {
        println(" Value at index $index = $value")
    }
    println()
    //Accessing array using withIndex() method
    arr1.forEach { element ->
        println("Value at index $element")
    }

}