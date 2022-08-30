package com.example.genericsandvariancedemo.reflection

import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

open class Person(open val name: String, private var age: Int) {
    private var phone = "9895977503"
    var address = "India"
    constructor(name: String, age: Int, phone: String) : this(name = name, age = age)

    fun displayDetails(updatePhone: String) {
        phone = updatePhone
        println("$name $age $phone $address")
    }

    private fun displayPhone() {
        println("Phone $phone")
    }
}

class Employee(override val name: String, age: Int, val salary: Int): Person(name, age) {
    var department = "Google"
    fun displayEmployeeDetails() {
        println(this)
    }
    private fun displayDepartment() {
        println("Phone $department")
    }

    companion object {
        @JvmStatic
        fun employeeCompanionFun() {
            println("Employee Companion Fun called")
        }
    }
}

fun main() {

    val person1 = Person("Raj", 33)
    //val classReference = Person::class
    val classReference = person1::class //Bounded class reference
    //val classReference = Employee::class

    //Note: A Kotlin class reference differs from a Java class reference on the JVM platform.
    // You can use the .java property on a KClass instance to get a Java class reference.

    //val classReference = person1::class.java //Java class reference


    println("personClassReference = ${classReference}")
    println("simpleName = ${classReference.simpleName}") //OP: simpleName = Person
    println("Person class qualifiedName: ${classReference.qualifiedName}")
    /**
     * All constructors declared in this class.
     */
    println("Person class constructors: ${classReference.constructors}")
    println("Person class members: ")
    /**
     * All functions and properties accessible in this class, including those declared in this class
     * and all of its superclasses. Does not include constructors.
     */
    classReference.members.forEach {
        println("${it }, isAccessible = ${it.isAccessible}")
    }

    println("Person class membersProperties: ")
    /**
     * Returns non-extension properties declared in this class and all of its superclasses.
     */
    classReference.memberProperties.forEach {
        println("${it }, isAccessible = ${it.isAccessible}")
    }
    println("Person class isData: ${classReference.isData}")
    println("Person class isAbstract: ${classReference.isAbstract}")
    println("Person class isFinal: ${classReference.isFinal}")
    println("Person class isCompanion: ${classReference.isCompanion}")
    println("Person class isFun: ${classReference.isFun}")
    println("Person class isInner: ${classReference.isInner}")
    println("Person class isOpen: ${classReference.isOpen}")
    println("Person class isSealed: ${classReference.isSealed}")
    println("Person class isValue: ${classReference.isValue}")

    println("Person class nestedClasses: ${classReference.nestedClasses}")
    println("Person class objectInstance: ${classReference.objectInstance}")
    println("Person class sealedSubclasses: ${classReference.sealedSubclasses}")
    println("Person class supertypes: ${classReference.supertypes}")
    println("Person class isSubclassOf: ${classReference.isSubclassOf(Any::class)}")
    println("Person class typeParameters: ${classReference.typeParameters}")
    println("Person class javaPrimitiveType: ${classReference.javaPrimitiveType}")


    val employee = Employee("Raj", 33, 1000)
    val employeeClassReference = Employee::class
    println("simpleName = ${employeeClassReference.simpleName}") //OP: simpleName = Employee
}