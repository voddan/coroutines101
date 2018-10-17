package clients.random.cancellation

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.*

/**
 * We start 2 coroutines simultaneously, then wait for results
 * */
object ErrorParallelLaunches


private val client = HttpClient(Apache)


suspend fun main(args: Array<String>) = coroutineScope<Unit> {
    launch {
        repeat(10) { i ->
            val value = client.get<String>("http://127.0.0.1:8080/random/slow").toInt()
            println("Value #$i is $value")
        }
    }

    launch {
        delay(1500)
        val value = client.get<String>("http://127.0.0.1:8080/random/error").toInt()
        println("Second value is $value")
    }

}






private val `next point` = 0