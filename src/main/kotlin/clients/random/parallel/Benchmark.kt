package clients.random.parallel.benchmark

import clients.random.parallel.loopsum.ParallelLoopSum
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

object Benchmark


private val URL = "http://127.0.0.1:8080/random/slow"

private val client = HttpClient(Apache)


suspend fun main(args: Array<String>) = coroutineScope {
    var sum = 0

    repeat(10) {
        val value = client.get<String>(URL).toInt()

        sum += value
    }

    println("Sum of 10 random values is $sum")
}



private object Measure {
    @JvmStatic
    fun main(args: Array<String>) {
        val mills = measureTimeMillis {
            runBlocking {
                clients.random.parallel.benchmark.main(args)
            }
        }

        println("Execution time is $mills ms")
    }
}





private val `go back` = ParallelLoopSum