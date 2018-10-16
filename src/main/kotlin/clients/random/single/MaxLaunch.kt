package clients.random.single

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.*
import clients.random.several.SumOfTwo


/**
 * How many coroutines can we launch?
 */
object MaxLaunch



private val URL = "http://127.0.0.1:8080/random"

private val client = HttpClient(Apache)



suspend fun main(args: Array<String>) {
    coroutineScope {

        repeat(1000) { i ->
            launch {

                val value = client.get<String>(URL).toInt()

                println("Coroutine $i got $value")
            }
        }

    }
}






private val `next point` = SumOfTwo
