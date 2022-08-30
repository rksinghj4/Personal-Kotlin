package com.example.enumabstractandsealed


data class User(val name: String, val age: Int)

/**
 * It solved enum class problem.
 * Here each subtype of abstract class can hold different data type
 * Problem with abstract class:
 * 1. It can be access out side module.
 * 2. Else branch is needed if when is used as an expression
 */
abstract class NetworkStateUsingAbstract {
    object Loading : NetworkStateUsingAbstract()
    data class Success(val listOfUser: List<User>) : NetworkStateUsingAbstract()
    data class Failure(val errorMessage: String) : NetworkStateUsingAbstract()
}

fun getNetworkStateUsingAbstract(state: NetworkStateUsingAbstract): String {
      when (state) {
        NetworkStateUsingAbstract.Loading -> {
            "Loading... "
        }
        is NetworkStateUsingAbstract.Failure -> {
            "Failed error: " + state.errorMessage
        }
        is NetworkStateUsingAbstract.Success -> {
            "Success: " + state.listOfUser
        }
        else -> {
            "NONE"
        }
    }
    return ""
}

fun main() {
    println(getNetworkStateUsingAbstract(NetworkStateUsingAbstract.Loading))
    println(getNetworkStateUsingAbstract(NetworkStateUsingAbstract.Failure("Try again..")))
    println(
        getNetworkStateUsingAbstract(
            NetworkStateUsingAbstract.Success(
                arrayListOf(
                    User(
                        "Raj",
                        21
                    ),
                    User("Tanuj", 14)
                )
            )
        )
    )
}