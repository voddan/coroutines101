package servers

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.delay
import java.util.*

object RandomServer {
    val random = Random()

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

                        route("/error") {
                            get {
                                delay(1000)
                                if(Random().nextBoolean())
                                    error("Request fails with 50% probability")
                                call.respondText(random.nextInt(10000000).toString())
                            }
                        }
                    }

                    route("/error") {
                        get {
                            if(Random().nextBoolean())
                                error("Request fails with 50% probability")
                            call.respondText(random.nextInt(10000000).toString())
                        }
                    }
                }

            }
        }
        server.start(wait = true)
    }

}
