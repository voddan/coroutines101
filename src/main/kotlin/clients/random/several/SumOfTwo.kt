package clients.random.several

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get

/**
 * How do we combine results of multiple requests?
 * */
object SumOfTwo



private val URL = "http://127.0.0.1:8080/random"

private val client = HttpClient(Apache)

suspend fun main(args: Array<String>) {
    val value1 = client.get<String>(URL).toInt()
    val value2 = client.get<String>(URL).toInt()

    val sum = value1 + value2

    println("$value1 + $value2 = $sum")
}






private val `next point` = RequestsInALoop