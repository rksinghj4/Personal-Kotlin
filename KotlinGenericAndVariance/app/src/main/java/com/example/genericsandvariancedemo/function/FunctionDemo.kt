package com.example.genericsandvariancedemo.function

fun student(name: String="Praveen", marks: Float=99.8F, roll_no: Int=11 ) {
    println("Name of the student is: $name")
    println("Standard of the student is: $marks")
    println("Roll no of the student is: $roll_no")
}
//Note: In case of default arguments, we have to pass the actual arguments
// to formal arguments in the same order
// as defined during function declaration.
fun main(args: Array<String>) {
    val name_of_student = "Gaurav"
    val marks_of_student = 99.9F
    val roll_no_of_student = 25
    // passing the argument name_of_student to name
    // and roll_no_of_student to standard

    //Error: Kotlin: Type mismatch:
    // inferred type is Int but Flot was expected
    //student(name_of_student, roll_no_of_student) //Error

    student(name_of_student)
    //student(marks_of_student) //Type mismatch Error - In default - order must maintained from front and first
    // argument, without skipping any argument in the middle.
    //student(roll_no_of_student) //Type mismatch Error -  In default - order must maintained from front
    student(name_of_student, marks_of_student)
    //student(name_of_student, ,roll_no_of_student) //Error: In default - can't skip the middle arguments
    // but we can skip the last

    student(name_of_student, roll_no = roll_no_of_student)// Middle arguments can be skiped with
    // the help of named named arguments
    //student(marks_of_student, roll_no = roll_no_of_student)//Error: default argument must start from first
    student(name = name_of_student)
    student(name = name_of_student, roll_no = roll_no_of_student)

    student(roll_no = roll_no_of_student, marks = marks_of_student)
    //student(marks = marks_of_student, 101) //Error: Mixing named and positioned arguments is not allowed

}