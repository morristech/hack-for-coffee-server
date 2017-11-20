package ch.unstable.hackforcoffee.data

import ch.unstable.hackforcoffee.Challenge
import org.springframework.data.repository.Repository

interface ChallengeRepository: Repository<Challenge, Long> {
    val getCurrentChallenge: Challenge
}