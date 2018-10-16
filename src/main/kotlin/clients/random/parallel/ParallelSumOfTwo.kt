package clients.random.parallel.sumoftwo

import clients.random.parallel.loopsum.ParallelLoopSum
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

object ParallelSumOfTwo


private val URL = "http://127.0.0.1:8080/random/slow"

private val client = HttpClient(Apache)


suspend fun main(args: Array<String>) = coroutineScope {
    val value1 = async {
        client.get<String>(URL).toInt()
    }

    val value2 = async {
        client.get<String>(URL).toInt()
    }

    val sum = value1.await() + value2.await()

    println("Parallel sum of values is $sum")
}



private object MeasureSum {
    @JvmStatic
    fun main(args: Array<String>) {
        val mills = measureTimeMillis {
            runBlocking {
                clients.random.parallel.sumoftwo.main(args)
            }
        }

        println("Execution time is $mills ms")
    }
}





private val `next point` = ParallelLoopSum