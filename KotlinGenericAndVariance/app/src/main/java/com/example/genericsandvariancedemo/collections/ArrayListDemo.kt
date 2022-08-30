package com.example.genericsandvariancedemo.collections

fun main(args: Array<String>) {
    // creating empty arraylist using constructor
    var arraylist = ArrayList<String>()
    // creating new arraylist1
    var arraylist1 = ArrayList<String>()
    var arraylist2 = ArrayList<Int>()

    println("objArrayList<String> == objArrayList<Int>(): ${arraylist == arraylist2}")
    println("objArrayList<String>.hashCode() == objArrayList<Int>().hashCode():" +
            " ${arraylist.hashCode() == arraylist2.hashCode()}")
    println("objArrayList<String>.hashCode() = ${arraylist.hashCode()}")
    println("objArrayList<Int>().hashCode() = ${arraylist2.hashCode()}")

    //adding String elements in the list
    arraylist.add("Geeks")
    arraylist.add("Geeks")

    arraylist.add(1, "For")//Insert the element at index 1, index is optional

    //adding all elements from arraylist to arraylist1
    println("Elements in arraylist1 -->")
    arraylist1.add("In Noida")
    arraylist1.add("Computer")
    arraylist1.add("Portal")
    arraylist1[2] = "Science" //    arraylist1.set(2, "Science")
    arraylist1.addAll(1, arraylist)//Insert the collection at index 1, index is optional
    for (i in arraylist1)
        println(i)
}