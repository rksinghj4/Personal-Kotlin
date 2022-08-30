package com.example.genericsandvariancedemo.generic

class ArrayUtil<T>(val arrayList: List<T>) {

    fun findElement(element: T, foundElement: (index: Int, element: T?) -> Unit) {
        for (i in arrayList.indices) {
            if (element == arrayList[i]) {
                foundElement(i, element)
                return
            }
        }
        foundElement(-1, null)
    }
}

fun main() {
    val arrayUtil1 = ArrayUtil<Int>(listOf(1, 2, 3, 4))
    val arrayUtil2 = ArrayUtil<String>(listOf("1", "2", "3", "4"))

    arrayUtil1.findElement(3) { index, element ->
        println("Index at $index")
        println("Element is $element")
    }

    arrayUtil2.findElement("3") { index, element ->
        println("Index at $index")
        println("Element is $element")
    }
}