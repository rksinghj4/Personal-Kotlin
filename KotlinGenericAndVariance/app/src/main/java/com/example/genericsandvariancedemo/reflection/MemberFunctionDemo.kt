package com.example.genericsandvariancedemo.reflection

import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberExtensionFunctions
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.jvm.isAccessible

fun Employee.employeeExtension() {
    println("Employee.employeeExtension() called")
}

fun Person.personExtension() {
    println("Person.personExtension() called")
}

fun main() {
    val person = Person("Raj", 22, "8431233557")
    val employee = Employee("Raj", 21, 10000)
    //val classReference = person::class
    //val classReference = employee::class
    val classReference = Employee::class
    employee.employeeExtension()
    person.personExtension()

    println("Declared members are:")
    /**
     * Returns all functions and properties declared in this class.
     * Does not include members declared in supertypes.
     */
    classReference.declaredMembers.forEach {
        println("${it }, isAccessible = ${it.isAccessible}")
    }

    println("Declared functions are:")

    /**
     * Returns all functions declared in this class.
     * If this is a Java class, it includes all non-static methods (both extensions and non-extensions)
     * declared in the class and the superclasses, as well as static methods declared in the class.
     */
    classReference.declaredFunctions.forEach {
        println("${it }, isAccessible = ${it.isAccessible}")
    }

    println("Declared Member functions are:")

    /**
     * Returns non-extension non-static functions declared in this class.
     */
    classReference.declaredMemberFunctions.forEach {
        println("${it }, isAccessible = ${it.isAccessible}")
    }

    println("Declared Member Extension Functions are:")
    /**
     * Returns extension functions declared in this class.
     */
    classReference.declaredMemberExtensionFunctions.forEach {
        println("${it }, isAccessible = ${it.isAccessible}")
    }
}

