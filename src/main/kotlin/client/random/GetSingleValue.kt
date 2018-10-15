package client.random

import client.veryBusyCpuTasks
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import khttp.get
import khttp.post
import kotlinx.coroutines.*
import org.apache.http.impl.nio.client.HttpAsyncClients
import org.apache.http.nio.client.methods.HttpAsyncMethods
import java.util.*
import rx.apache.http.ObservableHttp;
import rx.apache.http.ObservableHttpResponse

/**
 * Our server at http://127.0.0.1:8080/random
 * returns an integer value on GET
 * and that's it.
 */
object GetSingleValue

val URL = "http://127.0.0.1:8080/random"


/**
 * Nobody cares about threads
 * */
object Blocking_The_Main_Thread {
    @JvmStatic fun main(args: Array<String>) {
        val value = get(URL)
            .content.toString(Charsets.UTF_8).toInt()

        println("We got $value!")
    }
}


object Using_RxJava {
    @JvmStatic
    fun main(args: Array<String>) {
        val client = HttpAsyncClients.createDefault()
        client.start()

        ObservableHttp
            .createRequest(HttpAsyncMethods.createGet(URL), client)
            .toObservable()
            .flatMap { response -> response.content }
            .map { content -> content.toString(Charsets.UTF_8).toInt() }
            .subscribe { value ->
                println(value)
            }
    }
}


/**
 * import io.ktor.client.HttpClient
 * import io.ktor.client.engine.apache.Apache
 * */
object Using_A_Coroutine {
    @JvmStatic fun main(args: Array<String>) {
        runBlocking {

            val client = HttpClient(Apache)

            val value = client.get<String>(URL).toInt()

            println("We got $value")
        }

    }
}



/**
 * Keep calm and use a thread pool
 * */
object Dispatching_To_A_Pool {
    @JvmStatic fun main(args: Array<String>) {
        runBlocking {
            println("World!")

            launch {
                val client = HttpClient(Apache)

                val value = client.get<String>(URL).toInt()

                println("We got $value")
            }

            println("Hello!")
        }

    }
}


