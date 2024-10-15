package com.example.enumabstractandsealed

 open class Person(val name: String, val age: Int) {
    open val address: String = "Khangarhi, Aligarh"

    open fun displayDetails(person: Person) {
        println(this)
        println("${person.name}, ${person.age}, ${person.address}" )
    }
}

class Employee(val profession: String, nameParam: String, agaParam: Int): Person(nameParam, agaParam) {
    override val address: String = "Bangalore, Karnataka"

    override fun displayDetails(employee: Person) {
        super.displayDetails(employee)
        println(this)
        println("${employee.name}, ${employee.age}, ${employee.address}" )
    }
}

private fun main() {
    val person = Person( "Saifali", 27)
    val emp = Employee("Engineer", "Raj", 33)

    person.displayDetails(person)
    emp.displayDetails(emp)
}