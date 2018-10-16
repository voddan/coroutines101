package client.random

import client.execute
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import khttp.get
import kotlinx.coroutines.*
import org.apache.commons.io.IOUtils
import org.apache.http.HttpResponse
import org.apache.http.impl.nio.client.HttpAsyncClients
import org.apache.http.client.methods.HttpGet
import org.apache.http.concurrent.FutureCallback
import java.lang.Exception


/**
 * Our server at http://127.0.0.1:8080/random
 * returns an integer value on GET
 * and that's it.
 */
object GetSingleValue

val URL = "http://127.0.0.1:8080/random"



/**
 * import khttp.get
 * */
object As_Usual {
    @JvmStatic
    fun main(args: Array<String>) {
        val value = get(URL).content.toString(Charsets.UTF_8).toInt()

        println("We got $value!")
    }
}






/**
 * import io.ktor.client.HttpClient
 * import io.ktor.client.engine.apache.Apache
 * */
object Lets_Suspend {
    val client = HttpClient(Apache)

    @JvmStatic
    suspend fun main(args: Array<String>) {
        val value = client.get<String>(URL).toInt()

        println("We got $value")
    }
}







/**
 * Continuation is a callback that contains the rest of the execution
 * */
object Same_Thing_With_Callbacks {
    val client = HttpAsyncClients.createDefault()

    @JvmStatic
    fun main(args: Array<String>) {
        client.start()

        val future = client.execute(HttpGet(URL)) { result ->
            val value = IOUtils.toString(result.entity.content, Charsets.UTF_8).toInt()

            println("We got $value")
        }

        future.get()
        client.close()
    }

}







/**
 * Keep calm and use a thread pool
 * */
object Launching_Another_Coroutine {
    val client = HttpClient(Apache)

    @JvmStatic
    suspend fun main(args: Array<String>) {
        coroutineScope {
            println("World!")

            launch {

                val value = client.get<String>(URL).toInt()

                println("We got $value")
            }

            println("Hello!")
        }

    }
}


