package clients.random.several

import clients.random.parallel.sumoftwo.ParallelSumOfTwo
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get

/**
 * How do we combine results of multiple requests?
 *
 * Use any loops, if expressions, whatever you like.
 * */
object RequestsInALoop



private val URL = "http://127.0.0.1:8080/random"

private val client = HttpClient(Apache)

suspend fun main(args: Array<String>) {
    var sum = 0

    repeat(10) {
        val value = client.get<String>(URL).toInt()

        sum += value
    }


    println("Sum of 10 random values is $sum")
}







private val `next point` = ParallelSumOfTwo