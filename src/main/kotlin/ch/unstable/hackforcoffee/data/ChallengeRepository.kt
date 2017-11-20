package ch.unstable.hackforcoffee.data

import ch.unstable.hackforcoffee.model.Challenge
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.stereotype.Component
import java.util.*

@Component
interface ChallengeRepository: Repository<Challenge, Long> {
    @Query("SELECT c FROM Challenge c WHERE solved = false ORDER BY id ASC")
    fun findFirstCurrentChallenge(): Optional<Challenge>
}