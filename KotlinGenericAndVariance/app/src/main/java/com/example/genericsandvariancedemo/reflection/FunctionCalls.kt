package com.example.genericsandvariancedemo.reflection

import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

fun add(a: Int, b: Int): Int {
    return a + b;
}

fun add(a: String, b: String): String {
    return """$a$b"""
}

fun isDivisibleBy3(a: Int): Boolean {
    return a % 3 == 0
}

fun isDivisibleBy2(a: Int): Boolean {
    return a % 2 == 0
}

fun main() {
    val personObj = Person("Raj", 22)
    // Function reference obtained using :: operator
    val ref1 = ::isDivisibleBy3
    val array = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println(array.filter(ref1))

    // Function reference obtained for an overloaded function
    // By explicitly specifying the type
    val ref2: (String, String) -> String = ::add
    println(ref2)
    ref2("Raju", "Singh")

    // Function reference obtained implicitly
    val x = add(3, 5)
    println("3 + 5 = $x")

    val isDivisibleBy2Ref = ::isDivisibleBy2
    println(isDivisibleBy2Ref)
    println("Is 3 divisible by 2 : ${isDivisibleBy2Ref(3)}")

    val personNameRef = Person::name
    //val personAgeRef = Person::age //Error: Can't access private.

    val declaredMemberProperties = Person::class.declaredMemberProperties
    val privatePropertyAge = declaredMemberProperties.find { it.name == "age" }
    println("Age isAccessible: ${privatePropertyAge?.isAccessible}")
    privatePropertyAge?.isAccessible = true
    println("Age isAccessible: ${privatePropertyAge?.isAccessible}")
    println("Age of ${personNameRef.get(personObj)} ${privatePropertyAge?.get(personObj)}")

    val personDisplayDetailsRef = Person::displayDetails
    println("DisplayDetails: ")
    personDisplayDetailsRef(personObj, "1222222222")

    val personExtensionRef = Person::personExtension
    val declaredMemberFunctions = Person::class.declaredMemberFunctions

    declaredMemberFunctions.forEach {
        println("$it, isAccessible: ${it.isAccessible} ")
    }

    val privateMethod = declaredMemberFunctions.find { it.name == "displayPhone" }
    println("displayPhone: ${privateMethod?.isAccessible}")
    privateMethod?.isAccessible = true
    println("displayPhone: ${privateMethod?.isAccessible}")
    privateMethod?.call(personObj)

}