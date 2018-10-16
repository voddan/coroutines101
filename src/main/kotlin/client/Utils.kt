package client

import org.apache.commons.io.IOUtils
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.concurrent.FutureCallback
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient
import java.lang.Exception
import java.util.concurrent.Future

fun veryBusyCpuTasks(times: Int = 10) {
    print("I am so busy..")
    repeat(times) {
        print(".")
        Thread.sleep(100)
    }
    println()
}


fun CloseableHttpAsyncClient.execute(request: HttpUriRequest, callback: (HttpResponse) -> Unit): Future<HttpResponse> =
    execute(request,
        object : FutureCallback<HttpResponse> {
            override fun cancelled() = TODO("not implemented")
            override fun failed(ex: Exception) = throw ex

            override fun completed(result: HttpResponse) = callback(result)
        })