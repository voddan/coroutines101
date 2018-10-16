package clients.random.errors

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.*
import clients.random.several.SumOfTwo


/**
 * Launch propagates errors strait to its coroutine scope
 */
object ErrorLaunchCatch



private val URL = "http://127.0.0.1:8080/random/error"

private val client = HttpClient(Apache)


suspend fun main(args: Array<String>) {
    try {

        coroutineScope {
            launch {

                val value = client.get<String>(URL).toInt()

                println("We got $value")
            }
        }

    } catch (ex: Exception) {
        println("Something went terribly wrong: $ex")
    }
}






private val `next point` = ErrorAsync
