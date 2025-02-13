package com.raj.kotlinplayground.const

object Const1 {
    val name = "Raj Singh"

    val email = fetchEmail()//must be initialize on object creation

    private fun fetchEmail(): String {
        println("Fetch email")
        return "abc@gmail.com"
    }
}

private fun main() {
    /**
     * To access any non const property of object (i.e Const1),
     * The moment Const1 is written, the object is created.
     * Thumb rule for object creation - all property must be initialize.
     */
    println(Const1.name)
}

/**
 * OP:
 * Fetch email
 * Raj Singh
 */