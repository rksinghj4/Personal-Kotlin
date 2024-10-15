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

//abstract class notes:
//If when is used as a statement: free to skip one or all branches. No need to write else. (Same as enum and sealed)
//Very Important Note: No warnings for missing branch and else(difference from enum and sealed where we get warnings).

fun getNetworkStateUsingAbstractStatement(state: NetworkStateUsingAbstract) {
     when (state) {
        NetworkStateUsingAbstract.Loading -> {
            println("Loading... ")
        }
        /*is NetworkStateUsingAbstract.Failure -> {// Note 'is' is used with data class
            println("Failed error: " + state.errorMessage)
        }
        is NetworkStateUsingAbstract.Success -> {
            println("Success: " + state.listOfUser)
        }
        else -> {// Else is not needed at all, If when is used as an Statement.
            println("NONE")
        }*/
    }

}

//If when is used as an expression:
//1.  Either cover all possible branches or if you miss any single branch then use else to cover the missing branches.

//2. Only difference in abstract class: Else must be written If when is used as an expression: Even if all possibilities are covered
fun getNetworkStateUsingAbstract(state: NetworkStateUsingAbstract): String {
    return when (state) {
        NetworkStateUsingAbstract.Loading -> {
            "Loading... "
        }
        is NetworkStateUsingAbstract.Failure -> {// Note 'is' is used with data class
            "Failed error: " + state.errorMessage
        }
        is NetworkStateUsingAbstract.Success -> {
            "Success: " + state.listOfUser
        }
        else -> {//Only difference in abstract class: Else must be written If when is used as an expression: Even if all possibilities are covered
            "NONE"
        }
    }

    //return ""
}

private fun main() {
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

