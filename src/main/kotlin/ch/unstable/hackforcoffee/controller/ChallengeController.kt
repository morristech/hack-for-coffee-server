package ch.unstable.hackforcoffee.controller

import ch.unstable.hackforcoffee.data.ChallengeRepository
import ch.unstable.hackforcoffee.domain.usecases.SolveChallengeUseCase
import ch.unstable.hackforcoffee.model.Challenge
import com.google.gson.JsonParseException
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveOrNull
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject

class ChallengeController @Inject constructor(private val challengeRepository: ChallengeRepository,
                                              private val solveChallengeUseCase: SolveChallengeUseCase) {

    private val logger: Logger = LoggerFactory.getLogger(ChallengeController::class.java)

    val buildRoute: Route.() -> Unit = {
        get("current") {
            call.respondNullable(getCurrentChallenge(), "Challenge not found")
        }

        post("solve") {
            val solution = call.receiveParameters()["solution"]
            when {
                solution == null -> call.respond(HttpStatusCode.BadRequest, BadRequestMessage("parameter `solution` not provided"))
                solveChallengeUseCase.solveChallenge(solution) -> call.respond(HttpStatusCode.OK, SuccessMessage("Solution accepted"))
                else -> call.respond(HttpStatusCode.Forbidden, ErrorMessage("Wrong solution"))
            }
        }

        post {
            val challenge = try {
                call.receiveOrNull(Challenge::class)
            } catch (e: JsonParseException) {
                call.respond(HttpStatusCode.BadRequest, BadRequestMessage(e.message!!))
                return@post
            }

            if(challenge != null) {
                createChallenge(challenge)
                call.respond(HttpStatusCode.OK, challenge)
            } else {
                call.respond(HttpStatusCode.BadRequest, BadRequestMessage("Invalid challenge"))
            }
        }
    }

    private fun createChallenge(challenge: Challenge) {
        challengeRepository.createChallenge(challenge)
    }

    fun getCurrentChallenge(): Challenge? {
        var challenge = challengeRepository.findCurrentChallenge()
        if(challenge == null) {
            logger.debug("No active challenge found. Activating next")
            challenge = challengeRepository.activateNextChallenge()
            logger.debug("Activating next failed")
        }
        return challenge
    }

}


private suspend fun ApplicationCall.respondNullable(message: Any?, errorMessage: String) {
    if(message == null) {
        respond(HttpStatusCode.NotFound, NotFoundMessage(errorMessage))
    } else {
        respond(HttpStatusCode.OK, message)
    }
}

data class SuccessMessage(val message: String) {
    @Suppress("unused")
    val error: Boolean = false
}

open class ErrorMessage(val message: String) {
    @Suppress("unused")
    val error: Boolean = true
}

class NotFoundMessage(message: String): ErrorMessage(message) {

}

class BadRequestMessage(message: String): ErrorMessage(message) {

}