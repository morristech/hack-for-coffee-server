package ch.unstable.hackforcoffee

import ch.unstable.hackforcoffee.controller.ChallengeController
import ch.unstable.hackforcoffee.di.DaggerHackForCoffeeServer
import ch.unstable.hackforcoffee.json.ChallengeDeserializer
import ch.unstable.hackforcoffee.model.Challenge
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.text.DateFormat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerApp @Inject constructor(private val challengeController: ChallengeController) {

    fun run() {
        embeddedServer(Netty, 8080) {
            install(ContentNegotiation) {
                gson {
                    setDateFormat(DateFormat.LONG)
                    setPrettyPrinting()
                    registerTypeAdapter(Challenge::class.java, ChallengeDeserializer())
                }
            }

            routing {
                route("/challenge", build = challengeController.buildRoute)
            }
        }.start(wait = true)
    }
}

fun main(args: Array<String>) {
    DaggerHackForCoffeeServer.create().application().run()
}
