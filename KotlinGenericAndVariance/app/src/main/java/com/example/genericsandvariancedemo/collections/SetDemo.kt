package com.example.genericsandvariancedemo.collections

fun main(args: Array<String>) {
    //creating an empty set of strings
    val seta = mutableSetOf<String>()
    //creating an empty set of integers
    val setb = mutableSetOf<Int>()


    //checking if set is empty or not
    println("seta.isEmpty() is ${seta.isEmpty()}")

    // Since Empty sets are equal

    //checking if two sets are equal or not
    println("seta == setb is ${seta == setb}")

    println("Hashcode of seta = ${seta.hashCode()}")
    println("Hashcode of setb = ${setb.hashCode()}")

    println("seta.hashCode() == setb.hashCode() is ${seta.hashCode() == setb.hashCode()}")

    println(seta) //printing first set

    val hashSet = HashSet<String>(2, .7f)

    
}