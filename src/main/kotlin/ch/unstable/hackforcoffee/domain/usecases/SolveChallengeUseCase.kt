package ch.unstable.hackforcoffee.domain.usecases

import ch.unstable.hackforcoffee.data.ChallengeRepository
import javax.inject.Inject

class SolveChallengeUseCase @Inject constructor(private val challengeRepository: ChallengeRepository) {
    private fun activeChallengeHasSolution(solution: String): Boolean {
        val currentChallenge = challengeRepository.findCurrentChallenge()
        return currentChallenge?.solution?.toLowerCase() == solution.toLowerCase()
    }

    fun solveChallenge(solution: String): Boolean {
        return if(activeChallengeHasSolution(solution)) {
            challengeRepository.markActiveChallengeAsDone()
            challengeRepository.activateNextChallenge()
            true
        } else false
    }
}