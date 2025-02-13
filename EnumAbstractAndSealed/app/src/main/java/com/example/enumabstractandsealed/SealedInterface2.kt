package com.example.enumabstractandsealed

sealed interface NetworkState2 {
    /**
     * In sealed interface properties are by default final. We can't have abstract property.
     */
    val source: String
    abstract val source2: String//abstract keyword is redundant in sealed interface

    data class Success(val data: String, override val source: String) : NetworkState()

    data class Error(val error: String, override val source: String) : NetworkState()

    data class Loading(override val source: String) : NetworkState()

    abstract fun doSomething()
}

/**
 * all subclasses or objects must be with in same file or same package.
 * e.g. NetworkState is in same package of MyState
 */
data class MyState(val test: String, override val source: String) : NetworkState()

fun main() {
    val successNetworkState1 = NetworkState.Success("Any data 1", "source 1")
    val errorNetworkState1 = NetworkState.Error("Any error message 1", "source 1")
    val loadingNetworkState1 = NetworkState.Loading("source 1")
    val myState = MyState("test", "Source 3")
}


