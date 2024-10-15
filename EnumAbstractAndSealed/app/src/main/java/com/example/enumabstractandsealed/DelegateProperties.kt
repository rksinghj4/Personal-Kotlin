import kotlin.properties.Delegates

class User {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$prop : $old -> $new")
    }

    var age: Int by Delegates.vetoable(0) {
            prop, old, new ->
        println("$prop : $old -> $new")
        old < new
    }
}

/*private fun main() {
    val user = User()
    user.name = "first"
    user.name = "second"

    user.age = 1
    user.age = 2
    user.age = 0 //It's is not assign to old value
    user.age = 3
}*/

/**
 * The by-clause in the supertype list for Derived indicates that b will be stored internally
 * in objects of Derived and the compiler will generate all the methods of Base that forward to b
 */
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { println("Int x =  $x") }
}

class BaseImpl2(val x: Float) : Base {
    override fun print() { println("Float x =  $x") }
}

class Derived(val b: Base) : Base by b

private fun main() {
    val b = BaseImpl(10)
    Derived(b).print()

    val b2 = BaseImpl2(11.0f)
    Derived(b2).print()
}
