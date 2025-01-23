package com.example.flowschannels

/**
 * merge, zip, combine
 * https://kt.academy/article/cc-flow-combine
 *
 * //Additional notes(AN): StateFlow has value property
 */
class Flow(private var value: String) {
    fun map(mapImpl: TransformationFunction): Flow {
        this.value = mapImpl.apply(this.value)
        return this//Flow is returned after transforming
    }

    fun collect(collectorImpl: Collector) {//It is terminal operator. It returns nothing
        collectorImpl.collect(this.value)//Flow value is collected here
    }
}

fun interface TransformationFunction {
    fun apply(value: String): String
}

//Functional  interfaces or Single Abstract Method (SAM) interface
//You can use a lambda expression, where implementation of SAM interface is expected.
// It will make our code more concise
fun interface Collector {
    fun collect(value: String)
}

fun String.transformToFullName() = "$this Kumar"

fun String.transformToUserName() = this.replace(" ", "")

fun String.transformToGmailId() = this.plus("@gmail.com").lowercase()

fun testFlow() {
    val firstName = "Raj"
//
//    Flow(firstName).map(object : Function {
//        override fun apply(value: String): String {
//            return value.transformToFullName()
//        }
//    })

    Flow(firstName).map { it -> //params -> first statement of block before arrow
        it.transformToFullName()//return - last statement of block
    }.map {
        it.transformToUserName()
    }.map {
        it.transformToGmailId()
    }.collect {
        println("Email: $it")
    }
}

private fun main() {
    testFlow()
}