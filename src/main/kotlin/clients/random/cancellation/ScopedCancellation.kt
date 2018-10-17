package clients.random.cancellation

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.*

/**
 * We start 2 coroutines simultaneously, then wait for results
 * */
object ScopedCancellation


private val client = HttpClient(Apache)


suspend fun main(args: Array<String>) {
    coroutineScope {
        repeat(10) { i ->
            launch {
                delay(i * 1000L)
                val value = client.get<String>("http://127.0.0.1:8080/random/slow").toInt()
                println("Value #$i is $value")
            }
        }


        delay(2500)
        throw Exception()
    }

}






private val `next point` = ErrorParallelLaunches