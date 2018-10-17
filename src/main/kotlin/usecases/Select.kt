package usecases

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select
import servers.RandomServer

/**
 * Select expression makes it possible to
 * await multiple suspending functions simultaneously
 * and select the first one that becomes available.
 * */
object Select

val server1 = GlobalScope.produce<Int> {
    val client = HttpClient(Apache)

    while (true) {
        val response = client.get<String>("http://127.0.0.1:8080/random")
        val value = response.toIntOrNull() ?: 0
        channel.send(value)
    }
}

val server2 = GlobalScope.produce<Int> {
    val client = HttpClient(Apache)

    while (true) {
        val response = client.get<String>("http://127.0.0.1:8080/random")
        val value = response.toIntOrNull() ?: 0
        channel.send(value)
    }
}

suspend fun main(args: Array<String>) {
    repeat(10) {
        select<Unit> {
            server1.onReceive { value ->
                println("Server1 was faster and returned $value")
            }

            server2.onReceive { value ->
                println("Server2 was faster and returned $value")
            }
        }
    }
}



private val `go next` = RandomServer