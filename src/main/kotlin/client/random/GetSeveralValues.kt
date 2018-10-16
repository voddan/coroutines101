package client.random

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get

/**
 * How do we combine results of multiple requests?
 * */
object GetSeveralValues


object Sum_Of_Two {
    val client = HttpClient(Apache)

    @JvmStatic
    suspend fun main(args: Array<String>) {
        val value1 = client.get<String>(URL).toInt()
        val value2 = client.get<String>(URL).toInt()

        val sum = value1 + value2

        println("$value1 + $value2 = $sum")
    }
}





object Requests_In_A_Loop