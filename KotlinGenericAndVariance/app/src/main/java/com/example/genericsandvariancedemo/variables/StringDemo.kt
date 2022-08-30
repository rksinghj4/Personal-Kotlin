package com.example.genericsandvariancedemo.variables


fun main(args: Array<String>) {
    val str1 = "PQRSAAAA"
    val str2 = "PQRSAAAA"
    val int1: Int = 23
    val int2 = 32

    if (str1 === str2) {
        println("str1 " + str1.hashCode())
        println("str2 " + str2.hashCode())

    }
    else{
        println("str2 " + str2.hashCode())
    }

    println()

    println(int1.hashCode())
    println(int2.hashCode())

    println()


    ///Raw string
    val str3 = """Hi 
        | dear Raj
        |  How are
        |     you?""".trimMargin()

    println(str3)
}