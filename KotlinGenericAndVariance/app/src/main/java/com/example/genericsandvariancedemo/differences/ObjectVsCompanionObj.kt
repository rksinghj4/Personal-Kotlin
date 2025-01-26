package com.example.genericsandvariancedemo.differences

/**
 * object keyword - tells that it is singleton.
 * companion object - it is also singleton but always associated with class
 * (out side class companion is not allowed)
 * 1. Constructors are not allowed for object and companion object.
 * 2. object can be declare at the top level and it can be declare inside a class.
 * 3. One class can have only one companion but it can have multiple objects.
 * 4. object is final it can't be extended by other class.
 * 5. Modifier 'open' is not applicable to 'standalone object' or companion
 */

/**
 * https://medium.com/swlh/singleton-design-pattern-with-kotlin-2e6c8d42fc11
 */
final object UniversityAddress {// University is common for all college
    const val UniversityName = "UPTU"
    const val UniversityPhone = "23451 333333"
    const val universityVC = "YOGI JI"
}

class College(val name: String) {
    val listOfCourse: List<String> = listOf("CSE, MCA, ECE")

    object Principle {
        val name: String = "RAJ"
        val addressInCollege: String by lazy { "Room number, NOIDA, UP" }
    }

    final object CollegeAddress {
        val address: String = "NITC"
        val zipCode = "202280"
    }

    //Modifier 'open' is not applicable to 'standalone object' or companion
    object Contacts {
        val phone: String = "571 4242422"
        val adminDepartmentPhone = "571 5455558"
    }
    //object and companion object are by default final.
    final companion object UniversityDetails {
        final const val licenseExpiration = "31-Dec-2030"
        val adminInUniversityForMyCollege = "Krishna"
         fun displayCourseList() {
            //Container class members are not directly accessible inside companion
            // but with object we can access.
            College("JSS").listOfCourse.forEach { println(it) }
        }
    }

}