package com.example.enumabstractandsealed

/**
 * Problem here is we can't pass data of different type, only boolean can be passed
 * Note: else branch is not required in enum
 */
enum class NetworkStateUsingEnum(val isConnect: Boolean) {
    CONNECTED(true),
    DISCONNECTED(false);
}

//enum class notes:
//If when is used as a statement: free to skip one or all branches. No need to write else also, (Same as abstract and sealed)
// (only gives warning: 'when' expression on enum is recommended to be exhaustive). (Same as sealed)
fun getNetworkStateStatement(state: NetworkStateUsingEnum) {
     when(state) {
        NetworkStateUsingEnum.CONNECTED -> {
            println("IsConnected: " + state.isConnect)
        }
        /*NetworkStateUsingEnum.DISCONNECTED -> {
            println("IsConnected: " + state.isConnect)
        }

        else -> {// Else is not needed at all, If when is used as an Statement.
            println("NONE")
        }*/
    }
}
//enum class notes:
//If when is used as an expression: Either cover all possible branches or
// if you miss any single branch then use else to cover the missing branches.
fun getNetworkState(state: NetworkStateUsingEnum): String {
     return when(state) {
        NetworkStateUsingEnum.CONNECTED -> {
            "IsConnected: " + state.isConnect
        }
        NetworkStateUsingEnum.DISCONNECTED -> {
            "IsConnected: " + state.isConnect
        }
         else -> {//Only difference in enum class: Else is not needed at all, If when is used as an expression and all possibilities are covered.
             //If any possibility is missing then we must use else. (when is used as expression)
             "NONE"
        }
    }
}
fun main() {
    println(getNetworkState(NetworkStateUsingEnum.CONNECTED))
    println(getNetworkState(NetworkStateUsingEnum.DISCONNECTED))
}