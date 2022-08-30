package com.example.enumabstractandsealed

sealed class CommonErrors : UserErrors, LoginErrors {
    object Authorise : CommonErrors()
    object ServerErrors : CommonErrors()
}

/**
 * If UserErrors and LoginErrors both have CommonErrors
 * then CommonErrors sealed class should implement
 * both UserErrors and LoginErrors interface
 *
 * Note: Inheritance ka opposite hota h - here common code children m rakha h
 */
sealed interface UserErrors {
    object EmailAndPassword : UserErrors
}

sealed interface LoginErrors {
    object LoginFailed : LoginErrors
}

fun fetchUserData(userErrors: UserErrors) {

    when (userErrors) {
        UserErrors.EmailAndPassword -> {

        }
        CommonErrors.Authorise -> {

        }
        CommonErrors.ServerErrors -> {

        }
    }
}

fun loginUser(loginErrors: LoginErrors) {

    when (loginErrors) {
        LoginErrors.LoginFailed -> {

        }
        CommonErrors.Authorise -> {

        }
        CommonErrors.ServerErrors -> {

        }
    }
}

fun main() {
    val arr = arrayOf("")
    /*val fn1 = { a: Int, b: Int -> addition(a, b) }
    val fn2 = { a: Int, b: Int -> subtraction(a, b) }
    val fn3 = { a: Int, b: Int -> divide(a, b) }
    val fn4 = { a: Int, b: Int -> multiplication(a, b) }

    println(fn1(3, 2))*/

    println(DoFunctionalityOnData.ADDITION.fn(3, 5))
    println(DoFunctionalityOnData.SUBTRACTION.fn(3, 5))
}

fun addition(a: Int, b: Int) = a + b
fun subtraction(a: Int, b: Int) = a - b
fun divide(a: Int, b: Int) = a / b
fun multiplication(a: Int, b: Int) = a * b

enum class DoFunctionalityOnData(
    val functionalityType: String,
    val fn: ((a: Int, b: Int) -> Int)
) {
    ADDITION("add", { a: Int, b: Int -> addition(a, b) }),
    SUBTRACTION("sub", { a: Int, b: Int -> subtraction(a, b) }),
    DIVISION("divide", { a: Int, b: Int -> divide(a, b) })
}
