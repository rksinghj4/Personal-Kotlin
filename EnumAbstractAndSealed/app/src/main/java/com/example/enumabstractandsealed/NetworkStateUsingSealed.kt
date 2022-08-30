package com.example.enumabstractandsealed

sealed class NetworkStateUsingSealed {
    object Loading: NetworkStateUsingSealed()
    data class Success(val listOfUser: List<User>): NetworkStateUsingSealed()
    data class Failure(val errorMessage: String = "Network error"): NetworkStateUsingSealed()
}


fun getNetworkStateUsingSealed(state: NetworkStateUsingSealed): String {
    return when (state) {
        NetworkStateUsingSealed.Loading -> {
            "Loading... "
        }
        is NetworkStateUsingSealed.Failure -> {
            "Failed error: " + state.errorMessage
        }
        is NetworkStateUsingSealed.Success -> {
            "Success: " + state.listOfUser
        }
    }
}

fun main() {
    println(getNetworkStateUsingSealed(NetworkStateUsingSealed.Loading))
    println(getNetworkStateUsingSealed(NetworkStateUsingSealed.Failure("Try again..")))
    println(
        getNetworkStateUsingSealed(
            NetworkStateUsingSealed.Success(
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
