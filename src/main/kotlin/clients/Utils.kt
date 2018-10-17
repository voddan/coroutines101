package clients

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.concurrent.FutureCallback
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient
import org.apache.http.nio.client.HttpAsyncClient
import java.util.concurrent.Future
import kotlin.system.measureTimeMillis

fun veryBusyCpuTasks(times: Int = 10) {
    print("I am so busy..")
    repeat(times) {
        print(".")
        Thread.sleep(100)
    }
    println()
}


fun HttpAsyncClient.execute(request: HttpUriRequest, callback: (HttpResponse) -> Unit): Future<HttpResponse> =
    (this as CloseableHttpAsyncClient).execute(request,
        object : FutureCallback<HttpResponse> {
            override fun cancelled() = TODO("not implemented")
            override fun failed(ex: Exception) = throw ex

            override fun completed(result: HttpResponse) = callback(result)
        })



fun measure(block: suspend CoroutineScope.() -> Unit) {
    val mills = measureTimeMillis {
        runBlocking(block = block)
    }

    println("Execution time is $mills ms")
}