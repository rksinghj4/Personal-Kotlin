package com.example.genericsandvariancedemo.generic

open class Fruit_ {
}

class Mango_() : Fruit_() {
}

class Container_<T>(t: T) {//It is invariant because there is no in/out keyword.
    fun add(list: List<T>) {
    }
}

/*
 * Kotlin makes arrays invariant by default. By extension, generic types are invariant in Kotlin.
 * This can be managed by the out and in keywords.
 *
 * Invariance is the property by which
 * a standard generic function/class already defined for a particular data type,
 * cannot accept or return another datatype.
 *
 *
 *
 * Arrays in Kotlin are invariant.
 * Arrays in Kotlin are not built on native types, but are instead based on a Java array.
 * Although these are similar, they do behave slightly differently.
 * In Java, we can assign an array of a type to an array of its parent type.
 * (In java array supports Covariance)
 *
 * Arrays in Kotlin are invariant, which means that an array of a specific type
 * cannot be assigned to an array of its parent type.
 * It is not possible to assign Array<Integer> to Array<Any>.
 * This provides implicit type safety and prevents possible runtime errors in the application.
 *  Kotlin also provides specialized classes to create arrays of primitive data types,
 *  including ByteArray, ShortArray, and IntArray.
 */

fun main() {

    var fruit1: Container_<out Fruit_> = Container_(Fruit_())
    var fruit2: Container_<in Fruit_> = Container_(Fruit_())
    var fruit3: Container_<Fruit_> = Container_(Fruit_())

    var fruit11: Container_<out Fruit_> = Container_<Mango_>(Mango_())
    //var fruit22: Container_<in Fruit_> = Container_<Mango_>(Mango_()) //Error: Type mismatch
    //var fruit33: Container_<Fruit_> = Container_<Mango_>(Mango_()) //Error: Type mismatch

    var fruit4: Container_<out Fruit_> = Container_(Mango_())
    var fruit5: Container_<in Mango_> = Container_(Fruit_())

    var mango1: Container_<Mango_> = Container_(Mango_())
    var mango2: Container_<Fruit_> = Container_(Mango_())

    //var mango3: Container_<Fruit_> = Container_<Mango_>(Mango_()) //Error: Type mismatch

    fruit1 = mango1 //Covariance/Producer-out - Parent can hold child

    //fruit2 = mango1 // Error: (Consumer in) - Because child can consume parent but not opposite

    //Consumer in - child can accept parent
    fruit5 = fruit1
    fruit5 = fruit2
    fruit5 = fruit3

    //var mango3: Container_<Mango_> = Container_(Fruit_()) // Error: Bu default (without in  hard keyword), child can not hold parent.
    //mango1 = fruit3 // Error : Bu default (without "in"  hard keyword), child can not hold parent.
    //fruit3 = mango1 // Error : Bu default (without "out" soft keyword), parent can not hold child.
    //mango1 = fruit3 // Error : Invariance, neither parent nor child can hold each other type

    println(fruit1)
    println(fruit2)
    println(mango1)
    println(mango2)
    testInvariance()
}

fun test() {
    //val immutableListDoesNotSupportContravariance: List<String> = listOf(Any())//Error: Type Mismatch
    val immutableListSupportsCovariance: List<Any> = listOf("String")
    //val mutableListAreInVariant: MutableList<String> = mutableListOf(Any())//Error: Type Mismatch
    val mutableList: MutableList<String> = mutableListOf("String")
    println("Read from MutableList: ${mutableList[0]}")//Read operation
    mutableList[0] = "New String" //Write operation
}

/**
 * MutableList, ArrayList and Array are invariant in kotlin, because they support both Read and write operation.
 *
 */
private fun testInvariance() {
    var strArrayList: ArrayList<String> = arrayListOf("aa", "bb")
    strArrayList[1] = "CC"
    //strArrayList = arrayListOf<Any>()//Error: Type mismatch
    //val test : ArrayList<String> = arrayListOf<Any>()//Error: Type mismatch
    //val anyArrayList1: ArrayList<Any> = strArrayList// Error: Type mismatch
    //val anyArrayList2: ArrayList<Any> = arrayListOf<String>("aa", "bb")//Error: Type mismatch
    // No Error at below line , because compile treated it as arrayListOf<Any>("aa", "bb")
    val anyArrayList3: ArrayList<Any> = arrayListOf("aa", "bb")
    anyArrayList3[1] = 1//It will crash, No
    println("anyArrayList3 = $anyArrayList3")//Out put: anyArrayList3 = [aa, 1]

    val intArr: Array<Int> = arrayOf(1, 2)
    intArr[0] = 12
    //val anyArr1: Array<Any> = intArr// Error: Type mismatch
    val anyArr2: Array<Any> = arrayOf(1, 2)// No Error. But Why?
    anyArr2[1] = "Str"
    println("anyArr2[1] = ${anyArr2.toList()}")//Out put: anyArr2[1] = [1, Str]

}



