package com.example.genericsandvariancedemo.generic

open class Fruits{

}

class Mangos() : Fruits() {

}

class Containers<in T>(t: T) {
    fun add(list: List<T>) {
    }
}

fun main() {
    var fruits: Containers<Fruits> = Containers(Fruits())
    var mangos: Containers<Mangos> = Containers(Mangos())
    //fruits = mangos // Error : In Contravariance Child can hold Parent instance
    mangos = fruits

    println(fruits)
    println(mangos)

}