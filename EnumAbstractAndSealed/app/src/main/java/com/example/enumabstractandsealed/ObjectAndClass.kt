package com.example.enumabstractandsealed

class ObjectAndClass(nameParam: String, ageParam: Int) {
    var name: String = nameParam
    var a: Int = ageParam
    set(value) {
        field = value
    }
        get() { return field }

}

object Application {
    //In object : const - behave like static filed while accessing
    const val name = "MyApplication"
    //In object: lateinit - behave like static filed, both accessors remain instance methods
    lateinit var newName : String
    @JvmField
    val nameAsStaticField = "StaticApplication" // @JvmField - behave like static filed while accessing
}

private fun main() {
    println(Application.name)
    Application.newName = "YourApplication"
    println(Application.newName)
    println(Application.nameAsStaticField)
/*
    val ob = ObjectAndClass("Saifali", 25)
    println(ob.name + " " + ob.a)
    ob.a =29
    println(ob.name + " " + ob.a)*/
}