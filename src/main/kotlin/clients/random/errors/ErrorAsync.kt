package clients.random.errors

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.*


/**
 * What happens to errors that are thrown inside a `async`?
 */
object ErrorAsync



private val URL = "http://127.0.0.1:8080/random/error"

private val client = HttpClient(Apache)


suspend fun main(args: Array<String>) = coroutineScope {

    val deferred = GlobalScope.async {
        client.get<String>(URL).toInt()
    }

//     val value = deferred.await()

    println("No exception here")
}






private val `next point` = ErrorAsyncCatch
