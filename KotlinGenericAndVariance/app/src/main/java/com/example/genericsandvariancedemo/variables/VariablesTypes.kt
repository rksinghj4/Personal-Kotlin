package com.example.genericsandvariancedemo.variables
import java.util.Scanner;

import com.example.genericsandvariancedemo.myjava.KotlinKeyWordsAsIdentifiersInJava

/**
 * Immutable variable is not a constant because it can be initialized with the value of a variable.
 * It means the value of immutable variable doesn’t need to be known at compile-time, and
 * if it is declared inside a construct that is called repeatedly, it can take on a different
 * value on each function call.
 */
class VariablesTypes {

    val isNull: Boolean
        get() = this == null

    lateinit var mutable: KotlinKeyWordsAsIdentifiersInJava

    val immutable1 = 2
    get() = field //Backing field

    val immutable2 = mutable

    val `for` = 2 //for is hard keyword in kotlin
    val out = 1 //out is soft keyword in Kotlin

    companion object {
        //const is allowed with val but not var
        const val a = 2 // const are allowed on top level or inside objects or companion object
    }

    fun display() {
        var a: String? = "ab"
        a = "Two"
        a = null
        var b: String? = "bc"

        if(a == b) {
            println(" a is equal to b")
        } else {
            println(" a is not equal to b")
        }
    }

}

fun main() {
    val a = 2
    val b = 3
    var str: String? = null
    //str = "abc"
    println(str?.length)

    val c = a.minus(b)

    println(c)
}

enum class Month(val m:Int, month: String) {
    JAN(1, "January"),
    FEB(2, "February"),
    MARCH(3, "March"),
    APRIL(4, "April")
}

/*
fun main(args: Array<String>) {
    var reader = Scanner(System.`in`)
    print("Enter the month number:")
    var monthOfYear = reader.nextInt()
    var mymonth = Month.JAN

    //As an expression, the else branch is mandatory,
    // unless the compiler can prove that all possible
    // cases are covered with branch conditions.
    var month = when(mymonth) {
        Month.JAN ->"January"
        Month.FEB->"February"
        Month.MARCH->"March"
        Month.APRIL->"April"
       */
/* else -> {
            println("Not a month of year")
        }*//*

    }

    //As an statement, the else branch is optional.
    when(mymonth) {
        Month.JAN ->"January"
        Month.FEB->"February"
        Month.MARCH->"March"
        */
/* else -> {
             println("Not a month of year")
         }*//*

    }
    println(month)
}

*/
