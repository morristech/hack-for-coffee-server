package ch.unstable.hackforcoffee

import ch.unstable.hackforcoffee.di.DaggerHackForCoffeeServer
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import javax.inject.Inject

class ServerApp @Inject constructor() {

    fun run() {
        val server = embeddedServer(Netty, 8080) {
            routing {
                get("/") {
                    call.respondText("Hello, world!", ContentType.Text.Html)
                }
            }
        }
        server.start(wait = true)
    }
}

fun main(args: Array<String>) {
    DaggerHackForCoffeeServer.create().application().run()
}