package clients.random.cancellation

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.*
import usecases.Actors

/**
 * We can cancel any coroutine cooperatively
 * */
object SimpleCancellation


private val client = HttpClient(Apache)


suspend fun main(args: Array<String>) = coroutineScope<Unit> {
    val job: Job = launch {
        repeat(10) { i ->
            val value = client.get<String>("http://127.0.0.1:8080/random/slow").toInt()
            println("Value #$i is $value")
        }
    }

    delay(2500)
    job.cancel()

}






private val `next point` = Actors