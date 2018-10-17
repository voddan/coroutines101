package clients.random.errors

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import java.io.IOException


/**
 * try-catch is our best friend
 * */
object ErrorSuspendCatch


private val URL = "http://127.0.0.1:8080/random/error"

private val client = HttpClient(Apache)


suspend fun main(args: Array<String>) {
    try {
        val value = getInt(URL)
        println("We got $value")

    } catch (ex: Exception) {
        println("Something went terribly wrong: $ex")
    }
}


private suspend fun getInt(url: String): Int {
    val response = client.get<String>(URL)
    val num = response.toIntOrNull()

    if(num == null)
        throw IOException("Incorrect response `$response` for a GET to $url")

    return num
}








private val `next point` = ErrorLaunch