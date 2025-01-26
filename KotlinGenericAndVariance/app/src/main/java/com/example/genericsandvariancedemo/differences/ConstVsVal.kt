package com.example.genericsandvariancedemo.differences

/**
 * https://www.geeksforgeeks.org/whats-the-difference-between-const-and-val-in-kotlin/
 *
 * 1.const val  Must be at the top level or a member of an object or member of a companion object.
 * Const val can't be used locally(i.e inside function/class).
 * 2. const val Should be initialize from a primitive data-type or Strings, no other types allowed.
 * 3. const val variable can't be initialize with function call or class type/constructor call.
 * 4. const val doesn't have any custom getters.
 */
const val constVal ="AA"
val justVal = "BB"

fun returnConst() = constVal
fun returnInt() = 22
class TestConst(val a: String) {
    //Const val can't be used locally(i.e inside function/class).
    //const val notAllowedInSideClass = "AA" //Error
}

//const val testConst = TestConst(" ABC") // Error: Const 'val' has type 'TestConst'.
// Only primitives and String are allowed
//const val testConst2 = returnConst() // Error:  Const 'val' initializer should be a constant value
fun main(args :Array<String>) {
    val c = constVal
    val d = justVal
    val e = "PP"
}