package ch.unstable.hackforcoffee.controller

import ch.unstable.hackforcoffee.model.Challenge
import ch.unstable.hackforcoffee.data.ChallengeRepository
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class ChallengeController(private val challengeRepository: ChallengeRepository) {

    fun getCurrentChallenge(): Optional<Challenge> = challengeRepository.findFirstCurrentChallenge()
}