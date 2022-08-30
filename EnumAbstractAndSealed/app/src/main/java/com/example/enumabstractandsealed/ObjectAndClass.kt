package com.example.enumabstractandsealed

class ObjectAndClass(nameParam: String, ageParam: Int) {
    var name: String = nameParam
    var a: Int = ageParam
    set(value) {
        field = value
    }
        get() { return field }

}

fun main() {
    val ob = ObjectAndClass("Saifali", 25)
    println(ob.name + " " + ob.a)
    ob.a =29
    println(ob.name + " " + ob.a)

}