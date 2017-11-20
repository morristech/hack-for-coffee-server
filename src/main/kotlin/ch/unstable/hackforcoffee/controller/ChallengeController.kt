package ch.unstable.hackforcoffee.controller

import ch.unstable.hackforcoffee.Challenge
import ch.unstable.hackforcoffee.data.ChallengeRepository
import org.springframework.stereotype.Controller

@Controller
class ChallengeController(private val challengeRepository: ChallengeRepository) {

    fun getCurrentChallenge(): Challenge = challengeRepository.getCurrentChallenge
}