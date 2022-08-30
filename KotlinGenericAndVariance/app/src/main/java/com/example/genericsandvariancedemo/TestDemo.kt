package com.example.genericsandvariancedemo


class TestDemo {
    companion object {
        val TAG = "TestDemo"
    }

    fun display() {
        print(TAG)
    }
}
fun main1(arr :Array<String>? = null) {
    print("Hello ${arr?.get(0)}")
}

fun main() {
    main1(arrayOf("Amresh"))
}
