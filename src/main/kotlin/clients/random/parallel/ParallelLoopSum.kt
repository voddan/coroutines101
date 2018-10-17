package clients.random.parallel.loopsum

import clients.random.errors.ErrorSuspend
import clients.random.parallel.benchmark.Benchmark
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * How much faster will the parallel execution be?
 * */
object ParallelLoopSum


private val URL = "http://127.0.0.1:8080/random/slow"

private val client = HttpClient(Apache)


suspend fun parallelLoopSum() = coroutineScope {
    val list = mutableListOf<Deferred<Int>>()

    repeat(10) {
        val deferred = async { client.get<String>(URL).toInt() }

        list += deferred
    }

    val sum = list.sumBy { it.await() }

    println("Sum of 10 random values is $sum")
}


suspend fun main(args: Array<String>) {
    parallelLoopSum()


//    measure {
//        parallelLoopSum()
//    }
}






private val benchmark = Benchmark

private val `next point` = ErrorSuspend