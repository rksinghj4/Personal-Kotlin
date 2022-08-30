package com.example.genericsandvariancedemo.collections

fun main() {
    val hs1 = hashSetOf<String>("AA", "CC", "BB")//Mutable
    hs1.add("DD") //Check this with hover, HashSet uses HashMap internally,
    // which intern uses the hash table.

    val hs2 = hashSetOf<String>("TT", "PP", "LL")
    hs2.add("GG")
    hs1.addAll(hs2)
    println(hs1)
    hs1.retainAll(hs2)
    println(hs1)

    val hm1 = hashMapOf(1 to "Rinki", 2 to "Pinki", 3 to "Chinki") //Hash map uses the hash table.
    hm1.put(2, "Rak") //
    hm1.set(3, "Bharat")
    println("HashMap: $hm1")
}