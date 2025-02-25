package com.raj.kotlingeneral.shallowvsdeepcopy

import com.google.gson.Gson

/**
 * Shallow copy: create new objects and just copy the top level values/references.
 * In case of nested objects just references are copied
 * so they(old nad newly created objects) still share the same nested objects(locations).
 */
data class Address(var street: String, var city: String)//copy is by default Shallow

data class Person(val name: String, val address: Address)

data class AddressCloneable(var street: String, var city: String) : Cloneable {
    public override fun clone(): AddressCloneable {
        return AddressCloneable(this.street, this.city)//returning new object
    }
}

data class PersonCloneable(val name: String, val address: AddressCloneable) : Cloneable {
    public override fun clone(): PersonCloneable {
        return PersonCloneable(this.name, address.clone())//Deep copy - creating nested new object
    }
}

private fun main() {
    shallowCopy()
    println("**************************************************************************")
    deepCopyCloneApproach()
    println("**************************************************************************")
    deepCopyGsonApproach()
}

private fun shallowCopy() {
    val address = Address(street = "street1", city = "New Delhi")
    val oldPerson = Person(name = "Raj", address = address)

    val newPerson = oldPerson.copy(name = "Ronit")
    newPerson.address.city = "Greater Noida"
    println("shallowCopy")
    println("oldPerson = $oldPerson")
    println("newPerson = $newPerson")

    /**
     * oldPerson = Person(name=Raj, address=Address(street=street1, city=Greater Noida))
     * newPerson = Person(name=Ronit, address=Address(street=street1, city=Greater Noida))
     *
     */
}

private fun deepCopyCloneApproach() {
    val address = AddressCloneable(street = "street1", city = "New Delhi")
    val oldPerson = PersonCloneable(name = "Raj", address = address)

    val newPerson = oldPerson.clone()
    newPerson.address.city = "Greater Noida"
    println("deepCopy - Approach - by clone")
    println("oldPerson = $oldPerson")
    println("newPerson = $newPerson")

    /**
     * deepCopy - Approach1 - by clone
     * oldPerson = PersonCloneable(name=Raj, address=AddressCloneable(street=street1, city=New Delhi))
     * newPerson = PersonCloneable(name=Ronit, address=AddressCloneable(street=street1, city=Greater Noida))
     */
}

/**
 * Difference Between GSON and JSON in Android
 * https://www.geeksforgeeks.org/difference-between-gson-and-json-in-android/
 *
 * JSON - JavaScript Object Notation -  lightweight data-interchange format over internet.
 */
private fun deepCopyGsonApproach() {
    val gson = Gson()
    val address = Address(street = "street1", city = "New Delhi")
    val oldPerson = Person(name = "Raj", address = address)

    val newPersonJson = gson.toJson(oldPerson)
    println("newPerson json formate = $newPersonJson")

    val newPersonPOJO = gson.fromJson<Person>(newPersonJson, Person::class.java)

    newPersonPOJO.address.city = "Greater Noida"
    println("deepCopy - Approach - by Gson library serialization/deserialization")
    println("oldPerson = $oldPerson")
    println("newPersonPOJO = $newPersonPOJO")

    /**
     * newPerson json formate = {"name":"Raj","address":{"street":"street1","city":"New Delhi"}}
     * deepCopy - Approach1 - by Gson library serialization/deserialization
     * oldPerson = Person(name=Raj, address=Address(street=street1, city=New Delhi))
     * newPersonPOJO = Person(name=Raj, address=Address(street=street1, city=Greater Noida))
     */
}