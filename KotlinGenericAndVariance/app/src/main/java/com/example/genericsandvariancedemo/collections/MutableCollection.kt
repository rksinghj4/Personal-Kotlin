package com.example.genericsandvariancedemo.collections

fun main(args: Array<String>) {
    //Ordered collection, duplicate allowed
    val mutableList = mutableListOf("AA", 1, 2, "Raj", 5, 6.7, 5, 5)
    mutableList.add(1)
    mutableList.remove(2)
    println("++++++++++++++++++++mutableListOf+++++++++++++++++++++")
    mutableList.forEach {
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
    val mutableSet = mutableSetOf("AA", 1, 2, "Raj", 5, 5, 5,  6.7)
    mutableSet.add(2)
    mutableSet.remove(2)
    println("++++++++++++++++++++mutableSetOf+++++++++++++++++++++")
    mutableSet.forEach {
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
    val mutableMap = mutableMapOf(1 to "AA", 2 to "Raj", 5 to 6.7, 4 to 12)
    mutableMap.put(1, "Hey")
    mutableMap.remove(2)
    println("++++++++++++++++++++mutableMapOf+++++++++++++++++++++")
    mutableMap.forEach {
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

    mutableList.clear()
    mutableSet.clear()
    mutableMap.clear()
    println(mutableList)
    println(mutableSet)
    println(mutableMap)
}