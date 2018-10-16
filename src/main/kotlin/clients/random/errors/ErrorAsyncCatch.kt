package clients.random.errors

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.*
import java.lang.Exception


/**
 * What happens to errors that are thrown inside a `async`?
 */
object ErrorAsyncCatch



private val URL = "http://127.0.0.1:8080/random/error"

private val client = HttpClient(Apache)


suspend fun main(args: Array<String>) = coroutineScope {

    val deferred = GlobalScope.async {
        client.get<String>(URL).toInt()
    }

    try {

        val value = deferred.await()

        println("We got $value")

    } catch (ex: Exception) {
        println("Something went terribly wrong: $ex")
    }
}






private val `next point` = 0
