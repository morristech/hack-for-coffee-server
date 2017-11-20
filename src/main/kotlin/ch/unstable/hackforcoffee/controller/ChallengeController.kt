package ch.unstable.hackforcoffee.controller

import ch.unstable.hackforcoffee.model.Challenge
import ch.unstable.hackforcoffee.data.ChallengeRepository
import java.util.*

class ChallengeController(private val challengeRepository: ChallengeRepository) {

    fun getCurrentChallenge(): Optional<Challenge> = challengeRepository.findFirstCurrentChallenge()
}