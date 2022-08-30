package com.example.genericsandvariancedemo.InnerAndNestedClass

// outer class declaration
class outerClass {
    var str1 = "Outer class"
    var str2 = direct // Directly accessible from companion object

    //Accessibility from nestedClass
    //val fName = firstName// Error
    //val fName2 = nestedClass.firstName// Error
    //val fName2 = nestedClass().firstName //Error: Stack over flow

    // nested class act as static
    class nestedClass {
        val firstName  = "Praveen"
        private val lastName = "Ruhil"
        //var str3 = str1 //Error
        val str4 = outerClass().str1

        internal fun myFun2(): String  {
            return lastName
        }
    }

    companion object {
        const val direct = "Directly accessible from out class"
        //var str5 = str1 //Error
        val str6 = outerClass().str1
        fun myFun1() {
            println(direct)
            println(str6)
            print(nestedClass().firstName)
            println(" " + nestedClass().myFun2())
            //println(" " + nestedClass().lastName)//Error: private member can't be access directly

        }
    }

    fun myFun3()  {
        myFun1()
    }

    fun firstName() = nestedClass().firstName
}

fun main(args: Array<String>) {
    val nestedClassObj = outerClass.nestedClass()
    // accessing member of Nested class
    println(nestedClassObj.str4)
    println(outerClass.str6)
    println("LastName " + nestedClassObj.myFun2())

    outerClass.myFun1()

}