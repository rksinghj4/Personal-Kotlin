package com.example.enumabstractandsealed


sealed class NetworkStateUsingSealed {
    object Loading: NetworkStateUsingSealed()
    data class Success(val listOfUser: List<User>): NetworkStateUsingSealed()
    data class Failure(val errorMessage: String = "Network error"): NetworkStateUsingSealed()
}
//sealed class notes:
//If when is used as a statement: free to skip one or all branches. No need to write else also. (Same as abstract and enum)
//(warning: 'when' expression on sealed classes is recommended to be exhaustive)(Same as Enum)
fun getNetworkStateUsingSealedStatement(state: NetworkStateUsingSealed) {
     when (state) {
        NetworkStateUsingSealed.Loading -> {
            println("Loading... ")
        }
        is NetworkStateUsingSealed.Failure -> {
            println("Failed error: " + state.errorMessage)
        }
        /*is NetworkStateUsingSealed.Success -> {
            println("Success: " + state.listOfUser)
        }
        else -> {// Else is not needed at all, If when is used as an Statement.
            ""
        }*/
    }
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
         else -> {//Only difference in sealed class: Else is not needed at all, If when is used as an expression and all possibilities are covered.
             //If any possibility is missing then we must write else to cover the missing possibility. (when is used as expression)
             ""
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
