package ch.unstable.hackforcoffee.data

import ch.unstable.hackforcoffee.model.Challenge
import java.util.*
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

class ChallengeRepositoryImpl @Inject constructor(private val entityMangerFactory: EntityManagerFactory): ChallengeRepository {
    override fun markChallengeAsDone(challenge: Challenge) {
        val em = createEntityManager()
        em.transaction.begin()
        val query = em.createQuery("UPDATE Challenge SET active = false, solved = true WHERE id = :id")
        query.setParameter("id", challenge.id)
        val numberOfUpdated = query.executeUpdate()
        if(numberOfUpdated > 0) {
            em.transaction.commit()
        } else {
            em.transaction.rollback()
        }
    }

    private fun createEntityManager(): EntityManager = entityMangerFactory.createEntityManager()

    override fun findCurrentChallenge(): Optional<Challenge> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}