package usecases

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.produce
import java.util.*

/**
 * Actors interact with each other by exchanging messages
 * instead of calling methods on interfaces,
 * producing a system in which data flows through components
 * to meet the functional requirements of the system.
 *
 * Almost like microservices ;)
 * */
object Actors


sealed class Message {
    object GetReport : Message()
    class Add(val value: Int) : Message()
}


val summator = GlobalScope.actor<Message>() {
    var sum = 0L

    for(msg in channel)
        when(msg) {
            is Message.GetReport -> println("Sum is $sum")
            is Message.Add -> sum += msg.value
        }
}


suspend fun main(args: Array<String>) {
    summator.send(Message.GetReport)
    summator.send(Message.Add(100))
    summator.send(Message.Add(1))
    summator.send(Message.GetReport)
}




private val `go next` = Channels