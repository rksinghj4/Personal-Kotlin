package com.raj.kotlinplayground.const

object Const2 {
    const val name = "Raj Singh"

    val email = fetchEmail()//must be initialize on first access

    private fun fetchEmail(): String {
        println("Fetch email")
        return "abc@gmail.com"
    }
}

private fun main() {
    val ct = Const2//Object is created so email mut be initialized.
    println(ct.name)
    /**
     * To access any const property of object,
     * The moment Const2 is written, the object is not created if we access const val
     *
     * value of const val is directly copied at call site.
     */

    //println(Const2.name)//Object is not created because of const val

    println(Const2.email)//Object is created because non const
}

/**
 * OP:
 * Raj Singh
 */