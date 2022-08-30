package com.example.genericsandvariancedemo.reflection

import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.isAccessible

fun main() {
    val person = Person("Raj", 22, "8431233557")
    val employee = Employee("Raj", 21, 10000)
    //val classReference = person::class
    val classReference = employee::class

    println("PrimaryConstructor ${classReference.primaryConstructor}")
    println("All Constructors  ${classReference.constructors}")

    println("All Members : ")
    /**
     * All functions and properties accessible in this class, including those declared in this class
     * and all of its superclasses. Does not include constructors.
     */
    classReference.members.forEach {
        println("${it}, isAccessible = ${it.isAccessible}")
        it.isAccessible = true
        println("${it}, isAccessible = ${it.isAccessible}")
    }
    println("\n \n Declared Members : ")
    /**
     * Returns all functions and properties declared in this class.
     * Does not include members declared in supertypes.
     */    classReference.declaredMembers.forEach {
        println("${it}, isAccessible = ${it.isAccessible}")
    }
}