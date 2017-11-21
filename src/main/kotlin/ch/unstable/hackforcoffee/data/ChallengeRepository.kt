package ch.unstable.hackforcoffee.data

import ch.unstable.hackforcoffee.model.Challenge
import java.util.*

interface ChallengeRepository {
    fun findCurrentChallenge(): Challenge?

    fun activateNextChallenge(): Challenge?

    fun markActiveChallengeAsDone()

    fun createChallenge(challenge: Challenge)

    fun updateChallenge(challenge: Challenge)
}