package com.example.genericsandvariancedemo

fun main(args: Array<String>) {
    printWeekDay(1)
    printWeekDay("TUE")
    printWeekDay(1.2)
    printWeekDay('S')

}

fun printWeekDay(n: Any) {
    when (n) {
        1 -> {
            println("Sunday")
        }
        1.2 -> {
            println("Sunday")
        }
        'S' -> {
            println("Sunday")
        }
        "SUN" -> {
            println("Sunday")
        }
        2 -> {
            println("Monday")
        }
        "MON" -> {
            println("Monday")
        }
        3 -> {
            println("Tuesday")
        }
        "TUE" -> {
            println("Tuesday")
        }
        4 -> {
            println("Wednesday")
        }
        "WED" -> {
            println("Wednesday")
        }
    }
}
