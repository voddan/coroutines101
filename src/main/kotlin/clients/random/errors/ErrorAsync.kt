package clients.random.errors

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.RuntimeException


/**
 * What happens to errors that are thrown inside a `async`?
 */
object ErrorAsync



private val URL = "http://127.0.0.1:8080/random/error"

private val client = HttpClient(Apache)



suspend fun main(args: Array<String>) = GlobalScope.run {

    async {
        client.get<String>(URL).toInt()
        throw RuntimeException()
    }

    println("No hands, no cookies")
}






private val `next point` = ErrorAsyncUse
