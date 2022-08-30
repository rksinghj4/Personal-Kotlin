package com.example.genericsandvariancedemo.collections


fun main(args: Array<String>) {
    //Ordered collection, duplicate allowed
    val immutableList = listOf("AA", 1, 2, "Raj", 5, 6.7, 5, 5)
    println("++++++++++++++++++++listOf+++++++++++++++++++++")
    immutableList.forEach {
        when (it) {
            is Int -> {
                println("$it  is Int")
            }
            is String -> {
                println("$it  is String")
            }
            else -> {
                println("$it  ")

            }
        }
    }

    //Unordered collection, duplicate not allowed
    val immutableSet = setOf("AA", 1, 2, "Raj", 5, 5, 5,  6.7)
    println("++++++++++++++++++++setOf+++++++++++++++++++++")
    immutableSet.forEach {
        when (it) {
            is Int -> {
                println("$it  is Int")
            }
            is String -> {
                println("$it  is String")
            }
            else -> {
                println("$it  ")
            }
        }
    }

    //Logical connection b/w key -value
    val immutableMap = mapOf(1 to "AA", 2 to "Raj", 5 to 6.7, 4 to 12)
    println("++++++++++++++++++++mapOf+++++++++++++++++++++")
    immutableMap.forEach {
        when (it.value) {
            is Int -> {
                println("$it  is Int")
            }
            is String -> {
                println("$it  is String")
            }
            else -> {
                println("$it  ")
            }
        }
    }
    //immutableList.add() // Error
    //immutableSet.add() // Error
    //immutableMap.add() // Error

}