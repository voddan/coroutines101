package theory

import clients.random.single.AsUsual

/**
 * Coroutines are computer-program components that
 * generalize subroutines for non-preemptive multitasking
 * by allowing multiple entry points
 * for suspending and resuming execution at certain locations.
 *
 *   - [wikipedia](https://en.wikipedia.org/wiki/Coroutine)
 *
 * The term appeared in 1958, first paper was published in 1963
 * */
object SuspendableComputation













/**
 * Let's follow a very weird execution flow:
 * */
object MagicSequence {
    val fibonachi = sequence {
        var a = 1
        var b = 1

        while(true) {
            yield(a)

            val t = b
            b = a + b
            a = t
        }
    }


    @JvmStatic fun main(args: Array<String>) {

        val iter = fibonachi.iterator()

        println("Elements of the magic sequence:")

        repeat(10) {
            println(iter.next())
        }
    }
}











/**
 * State machine under the hood!
 * */
object SameWithIterators {
    val fibonachi = object : Sequence<Int> {
        override fun iterator() = object : Iterator<Int> {
            var a = 1
            var b = 1

            override fun next(): Int {
                val result = a

                val t = b
                b = a + b
                a = t

                return result
            }

            override fun hasNext() = true
        }
    }


    @JvmStatic fun main(args: Array<String>) {

        val iter = fibonachi.iterator()

        println("Elements of a normal sequence with an iterator:")

        repeat(10) {
            println(iter.next())
        }
    }
}










private val `next point` = AsUsual
