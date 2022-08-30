package com.example.genericsandvariancedemo.lambdaexpression


// with type annotation in lambda expression
val sum1 = { a: Int, b: Int -> a + b }

// without type annotation in lambda expression
val sum2:(Int,Int)-> Int  = { a , b -> a + b}

val numbers = arrayOf(1,-2,3,-4,5)

val find = fun(num: Int): String{
     return if(num % 2==0 && num < 0) {
         "Number is even and negative"
     }
     else if (num %2 ==0 && num >0){
         "Number is even and positive"
     }
     else if(num %2 !=0 && num < 0){
         "Number is odd and negative"
     }
     else {
         "Number is odd and positive"
     }
}

fun main(args: Array<String>) {
    val company = { println("GeeksforGeeks")}
    // invoking function method1
    company()
    // invoking function method2
    company.invoke()

    val result1 = sum1(2,3)
    val result2 = sum2(3,4)
    println("The sum of two numbers is: $result1")
    println("The sum of two numbers is: $result2")

    // directly print the return value of lambda
    // without storing in a variable.
    println(sum1(5,7))

    println(numbers.filter {  it > 0 }) // shorthand form of lambda function
    println(numbers.filter {  item -> item > 0 })//  Longhand form of lambda function

    println("Result = ${find(12)}")
}