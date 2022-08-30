package com.example.genericsandvariancedemo.reflection

import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.isAccessible

fun main() {
    val person = Person("Raj", 22, "8431233557")
    val employee = Employee("Raj", 21, 10000)
    //val classReference = person::class
    val classReference = employee::class

    //Returns the primary constructor of this class, or `null`
    // if this class has no primary constructor.
    println("PrimaryConstructor ${classReference.primaryConstructor}")

    /**
     * All constructors declared in this class.
     */
    println("All Constructors  ${classReference.constructors}")

    println("Member properties : ")
    //Returns non-extension properties declared in this class and all of its superclasses.
    classReference.memberProperties.forEach {
        println("${it }, isAccessible = ${it.isAccessible}")
        it.isAccessible = true
        println("${it }, isAccessible = ${it.isAccessible}")
    }

    println("Declared Member properties : ")
    //Returns non-extension properties declared in this class.
    classReference.declaredMemberProperties.forEach {
        println("${it }, isAccessible = ${it.isAccessible}")
    }

    println("Declared Member Extension Properties : ")
    //Returns extension properties declared in this class.
    classReference.declaredMemberExtensionProperties.forEach {
        println("${it }, isAccessible = ${it.isAccessible}")
    }
}