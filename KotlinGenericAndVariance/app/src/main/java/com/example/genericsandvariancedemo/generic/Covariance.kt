package com.example.genericsandvariancedemo.generic

open class Fruit{

}

class Mango() : Fruit() {

}

class Container<out T>(t:T) {
    val value = t
    val list = mutableListOf<Mango>()
    fun add(): List<T> {
        list.add(Mango())
       return list as List<T>
    }
}

fun main() {
    var fruit: Container<Fruit> = Container(Fruit())
    var mango: Container<Mango> = Container(Mango())

    //mango = fruit // Error : In Covariance Parent can hold child instance
    fruit = mango
    fruit.add()
    println(fruit)
    println(mango)

}