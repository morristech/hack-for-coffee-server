package ch.unstable.hackforcoffee.data

import ch.unstable.hackforcoffee.model.Challenge
import java.util.*

interface ChallengeRepository {
    fun findFirstCurrentChallenge(): Optional<Challenge>
}