package clients.random.single

import clients.random.several.SumOfTwo
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


/**
 * How many coroutine requests can we launch simultaneously?
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




private val `alternatively` = MaxLaunchSum

private val `next point` = SumOfTwo
