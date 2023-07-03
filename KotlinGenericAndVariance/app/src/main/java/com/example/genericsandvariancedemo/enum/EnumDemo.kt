package com.example.genericsandvariancedemo.enum

/*
enum class DAYS {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;
}

fun main() {
    val day = DAYS.SUNDAY
    when(day) {
        DAYS.SUNDAY -> println("Today is Sunday")
        DAYS.MONDAY -> println("Today is Monday")
        DAYS.TUESDAY -> println("Today is Tuesday")
        DAYS.WEDNESDAY -> println("Today is Wednesday")
        DAYS.THURSDAY -> println("Today is Thursday")
        DAYS.FRIDAY -> println("Today is Friday")
        DAYS.SATURDAY -> println("Today is Saturday")
        //else -> {}
    }
}
*/

class MyClass {
    companion object {
        // member function of companion object
        fun display(str: String): String {
            return str
        }
    }
}

// extension function of companion object
fun MyClass.Companion.abc() {
    println("Extension function of companion object")
}

fun main(args: Array<String>) {
    val ob = MyClass.display("Function declared in companion object")
    println(ob)
    // invoking the extension function
    val ob2 = MyClass.abc()


}

