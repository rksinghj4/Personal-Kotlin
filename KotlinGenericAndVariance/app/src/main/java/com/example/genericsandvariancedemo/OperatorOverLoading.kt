package com.example.genericsandvariancedemo

class OperatorOverLoading(val str: String) {
    operator fun plus(a: Int): String {
        return this.str + a
    }

    operator fun minus(a: Int): String {
        return this.str.substring(0, this.str.length - 2)
    }

    operator fun times(a: Int) : String {
        var i: Int = 0
        var timesStr = ""
        while (i < a) {
            timesStr += this.str
            i++
        }
        return timesStr
    }

    operator fun div(a: Int) : String {
        var i: Int = 0
        var timesStr = this.str
        while (i< a) {
            timesStr = timesStr.substring(0, a)
            i++
        }
        return timesStr
    }
}

fun  main() {
    val obj = OperatorOverLoading("abcdefghijklmnopqrst")
    println(obj+2)
    println(obj-2)
    println(obj*2)
    println(obj/2)
}