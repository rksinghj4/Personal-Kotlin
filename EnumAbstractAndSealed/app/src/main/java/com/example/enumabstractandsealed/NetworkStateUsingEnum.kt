package com.example.enumabstractandsealed

/**
 * Problem here is we can't pass data of different type, only boolean can be passed
 * Note: else branch is not required in enum
 */
enum class NetworkStateUsingEnum(val isConnect: Boolean) {
    CONNECTED(true),
    DISCONNECTED(false);
}

fun getNetworkState(state: NetworkStateUsingEnum): String {
    return when(state) {
        NetworkStateUsingEnum.CONNECTED -> {
            "IsConnected: " + state.isConnect
        }
        NetworkStateUsingEnum.DISCONNECTED -> {
            "IsConnected: " + state.isConnect
        }
        else -> {
            "NONE"
        }
    }

}
fun main() {
    println(getNetworkState(NetworkStateUsingEnum.CONNECTED))
    println(getNetworkState(NetworkStateUsingEnum.DISCONNECTED))
}