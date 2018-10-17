package clients.random.parallel.benchmark

import clients.measure
import clients.random.parallel.loopsum.ParallelLoopSum
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.coroutineScope

object Benchmark


private val URL = "http://127.0.0.1:8080/random/slow"

private val client = HttpClient(Apache)


suspend fun main(args: Array<String>) {
    measure {

        coroutineScope {
            var sum = 0

            repeat(10) {
                val value = client.get<String>(URL).toInt()

                sum += value
            }

            println("Sum of 10 random values is $sum")
        }

    }
}






private val `go back` = ParallelLoopSum