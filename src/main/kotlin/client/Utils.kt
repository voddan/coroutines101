package client

import org.apache.commons.io.IOUtils
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.concurrent.FutureCallback
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient
import org.apache.http.impl.nio.client.HttpAsyncClients
import org.apache.http.nio.client.HttpAsyncClient
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


fun HttpAsyncClient.execute(request: HttpUriRequest, callback: (HttpResponse) -> Unit): Future<HttpResponse> =
    (this as CloseableHttpAsyncClient).execute(request,
        object : FutureCallback<HttpResponse> {
            override fun cancelled() = TODO("not implemented")
            override fun failed(ex: Exception) = throw ex

            override fun completed(result: HttpResponse) = callback(result)
        })