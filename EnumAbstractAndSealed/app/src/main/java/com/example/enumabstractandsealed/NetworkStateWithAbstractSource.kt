package com.example.enumabstractandsealed

/**
 * all subclasses or objects must be with in same file or same package.
 */
sealed class NetworkState {
    abstract val source: String //In sealed interface we can't have abstract property

    data class Success(val data: String, override val source: String) : NetworkState()

    data class Error(val error: String, override val source: String) : NetworkState()

    data class Loading(override val source: String) : NetworkState()
}


fun main() {
    val successNetworkState1 = NetworkState.Success("Any data 1", "source 1")
    val errorNetworkState1 = NetworkState.Error("Any error message 1", "source 1")
    val loadingNetworkState1 = NetworkState.Loading("source 1")

    val successNetworkState2 = NetworkState.Success("Any data 2", "source 2")
    val errorNetworkState2 = NetworkState.Error("Any error message 2", "source 2")
    val loadingNetworkState2 = NetworkState.Loading("source 2")

    when (getNetworkStateClass()) {
        is NetworkState.Success -> TODO()
        is NetworkState.Loading -> TODO()
        is NetworkState.Error -> TODO()
    }
}

fun getNetworkStateClass(): NetworkState {
    return NetworkState.Loading("test-source")
}


