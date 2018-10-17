package usecases

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * A Channel is conceptually very similar to BlockingQueue.
 * Instead of a blocking put operation it has a suspending send,
 * and instead of a blocking take operation it has a suspending receive.
 * */
object Channels



suspend fun main(args: Array<String>) = coroutineScope<Unit> {
    val list = listOf("Hi!", "5", "38", "-6", "How are you?")

    launch {
        for(element in list)
            parser.send(element)
    }

    launch {
        for(e in output)
            println(e)
    }
}

val output = Channel<Int>()

//-------

val parser = GlobalScope.actor<String> {
    for(str in channel) {
        val integer = str.toIntOrNull()

        if(integer != null)
            obfuscator.send(integer)
        else
            logger.send(str)
    }
}


val logger = GlobalScope.actor<String> {
    while(true) {
        val msg1 = channel.receive()
        val msg2 = channel.receive()
        println("First you said `$msg1`, and then you said `$msg2`, and I was completely lost")
    }
}


val obfuscator = GlobalScope.actor<Int> {
    for(number in channel) {
        val salted = number + salt.receive()
        output.send(salted)
    }
}


val salt = GlobalScope.produce<Int> {
    val client = HttpClient(Apache)

    while (true) {
        val response = client.get<String>("http://127.0.0.1:8080/random")
        val value = response.toIntOrNull() ?: 0
        channel.send(value)
    }
}









private val `go next` = Select