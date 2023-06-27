package com.example.genericsandvariancedemo.function

import kotlin.reflect.KClass

/**
 * To access the type parameter inside called function
 * here we are passing an additional parameter of type KClass<T>.
 * To avoid passing of redundant parameter of KClass<T> type, we can use reified with inline.
 */

fun <T : Any> printValueAndItsType(value: T, type: KClass<T>) {
    println("Value = $value, It's type = ${type}")
}

/**
 * 1. reified keyword must be used with inline.
 * 2. Inline - request the compiler not to alocate memory for inlined function.
 * Just copy the code to call site.
 * 3. reified keyword - request the complier not to flag the error
 * if T::class(i.e. type of parameter) is accessed inside inline function,
 * because at the time you will copy the code of inline function at call site,
 * type of parameter can be accessed using reflection.
 *
 *
 * Inline keyword must be used with small function up to 2-3 line function.
 * If we use inline keyword with large function
 * then large piece of code will be copied to call site every time it is called.
 * So it will be against code reusability.
 *
 * Java doesn't support inline so can't support reified.
 * Java does not provide inline functions it is typically done by the JVM at execution time.
 *
 */

inline fun <reified T : Any> printValueAndItsType(value: T) {
    println("Value = $value, It's type = ${T::class}")
}

fun main(args: Array<String>) {
    printValueAndItsType(1, Int::class)
    printValueAndItsType(10.0f, Float::class)
    printValueAndItsType("Radha", String::class)

    println("________________________________________________")

    printValueAndItsType(2)
    printValueAndItsType(20.0f)
    printValueAndItsType("Krishna")

    println("________________________________________________")
    printValueAndItsType<Int>(3)
    printValueAndItsType<Float>(30.0f)
    printValueAndItsType<String>("Bihari ji")
}