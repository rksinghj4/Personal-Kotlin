package com.example.genericsandvariancedemo.abstract

interface Animal {
    /*val name: String
        get() = field*/

    var name: String
    var surname: String

    val fullName: String
        get() = "$name $surname"
        /*set(value) {
            field = value
        }*/

    open fun makeVoice() {
        print("<${this::class.simpleName}Animal voice>")
    }
}

data class Fox(override var name: String, override var surname: String) : Animal {
    override var fullName: String = "ZZZ"
}

fun main() {
    val fox = Fox("AA", "BB")
    println(fox.toString())
    fox.name = "PP"
    println(fox.fullName)
    fox.fullName = "AA BB"
    println(fox.fullName)
    fox.makeVoice() // <Animal voice>
}