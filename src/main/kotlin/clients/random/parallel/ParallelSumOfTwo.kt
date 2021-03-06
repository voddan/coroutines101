package clients.random.parallel.sumoftwo

import clients.random.parallel.loopsum.ParallelLoopSum
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * We start 2 coroutines simultaneously,
 * then await for their results.
 *
 * How long does it take?
 * */
object ParallelSumOfTwo


private val URL = "http://127.0.0.1:8080/random/slow"

private val client = HttpClient(Apache)


suspend fun parallelSumOfTwo() = coroutineScope {
    val value1 = async {
        client.get<String>(URL).toInt()
    }

    val value2 = async {
        client.get<String>(URL).toInt()
    }

    val sum = value1.await() + value2.await()

    println("Parallel sum of values is $sum")
}




suspend fun main(args: Array<String>) {
    parallelSumOfTwo()


//    measureParallelSumOfTwo()
}















/**
 * Need `runBlocking` to start a chain of `suspend` functions
 *
 * fun <T> runBlocking(block: suspend CoroutineScope.() -> T): T
 *
 * */
private fun measureParallelSumOfTwo() {
    val mills = measureTimeMillis {
        runBlocking {
            parallelSumOfTwo()
        }
    }

    println("Execution time is $mills ms")
}







private val `next point` = ParallelLoopSum