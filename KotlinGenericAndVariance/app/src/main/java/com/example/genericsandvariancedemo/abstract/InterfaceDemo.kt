package com.example.genericsandvariancedemo.abstract

/**
 * 1. Property in interface can't have backing field.
 * 2. final modifier is not allowed in interface.
 * 3. Interface can't hold the state.
 * 4. We can't instantiate the interface.
 * 5. Members inside interface are by default open (or abstract if not define).
 */

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
        print("<${this::class.simpleName} Animal voice>")
    }

    abstract fun walk()
}

data class Fox(override var name: String, override var surname: String) : Animal {
    override var fullName: String = "ZZZ"
    override fun walk() {
        println("$fullName is walking")
    }
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