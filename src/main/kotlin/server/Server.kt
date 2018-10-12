package server

import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.request.receiveText
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.delay
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.absoluteValue

object Server {
    val random = Random()

    var sum = AtomicInteger(0)

    @JvmStatic
    fun main(args: Array<String>) {
        val server = embeddedServer(Netty, port = 8080) {
            routing {
                route("/random") {

                    get {
                        call.respondText(random.nextInt(10000000).toString())
                    }

                    route("/slow") {

                        get {
                            delay(1000)
                            call.respondText(random.nextInt(10000000).toString())
                        }

                        get("/error") {
                            delay(1000)
                            if(random.nextBoolean())
                                call.respondText(random.nextInt(10000000).toString())
                            else
                                error("Something went terribly wrong :(")
                        }
                    }
                }

                route("/sum") {
                    get() {
                        call.respondText(sum.toString())
                    }

                    post() {
                        val text = call.receiveText()
                        val value = text.toIntOrNull() ?: error("Can't parse $text to Int")

                        val oldSum = sum.get()
                        val newSum = oldSum + value
                        val sign = if(value >= 0) "+" else "-"

                        call.respondText("$sum $sign ${value.absoluteValue} = $newSum")
                        sum.addAndGet(value)
                    }
                }
            }
        }
        server.start(wait = true)
    }

}