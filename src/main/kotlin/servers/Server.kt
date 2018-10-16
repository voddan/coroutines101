package servers

import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.call
import io.ktor.request.receiveText
import io.ktor.response.respondText
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.util.pipeline.ContextDsl
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
                    delaysAndErrors {
                        get {
                            call.respondText(random.nextInt(10000000).toString())
                        }
                    }
                }

                route("/sum") {
                    delaysAndErrors {
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
        }
        server.start(wait = true)
    }

}


@ContextDsl
fun Route.slow(path: String = "slow",
               timeMillis: Long = 1000,
               build: Route.() -> Unit): Route {

    val slowRoute = this.createChild(object : RouteSelector(1.0) {
        override fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation =
            RouteSelectorEvaluation.Constant
    })

    slowRoute.intercept(ApplicationCallPipeline.Features) {
        delay(timeMillis)
        proceed()
    }

    slowRoute.build()

    return slowRoute
}

@ContextDsl
fun Route.error(path: String = "error",
                build: Route.() -> Unit): Route {

    val erroredRoute = this.createChild(object : RouteSelector(1.0) {
        override fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation =
            RouteSelectorEvaluation.Constant
    })

    erroredRoute.intercept(ApplicationCallPipeline.Features) {
        if(Random().nextBoolean())
            error("Request fails with 50% probability")
        proceed()
    }

    erroredRoute.build()

    return erroredRoute
}

@ContextDsl
fun Route.delaysAndErrors(build: Route.() -> Unit): Route {

    val route = this.createChild(object : RouteSelector(1.0) {
        override fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation =
            RouteSelectorEvaluation.Constant
    })

    route {
        build()

        route("slow") {
            slow {
                build()

                route("error") {
                    error {
                        build()
                    }
                }
            }
        }

        route("error") {
            error {
                build()
            }
        }
    }

    return route
}