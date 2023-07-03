package com.example.genericsandvariancedemo.abstract

//abstract class
abstract class Employee(val name: String, val experience: Int) {
    // By default Non-Abstract Property are final
    final var address = "Aligarh"
    get() = field
    set(value) {
        field = value
    }
    // Abstract Property (Must be overridden by Subclasses)
    abstract var salary: Double

    // Abstract Methods (Must be implemented by Subclasses)
    abstract fun dateOfBirth(date:String)

    // Non-Abstract Method
    final fun employeeDetails() {
        println("Name of the employee: $name")
        println("Experience in years: $experience")
        println("Annual Salary: $salary")
    }
}

interface A
interface B
interface C

// derived class
class Engineer(name: String, experience: Int) : Employee(name,experience), A, B, C {
    //override var address = "" // Error: Final property can not be over ridden.
    override var salary = 500000.00
    override fun dateOfBirth(date:String) {
        println("Date of Birth is: $date")
    }
}

fun main(args: Array<String>) {
    val eng = Engineer("Praveen",2)
    eng.employeeDetails()
    eng.dateOfBirth("02 December 1994")
    println(eng.address)
    eng.address = "Bangalore"
    println(eng.address)
}