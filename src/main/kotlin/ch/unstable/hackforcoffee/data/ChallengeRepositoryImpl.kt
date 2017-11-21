package ch.unstable.hackforcoffee.data

import ch.unstable.hackforcoffee.model.Challenge
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.NoResultException

class ChallengeRepositoryImpl @Inject constructor(private val entityMangerFactory: EntityManagerFactory) : ChallengeRepository {

    override fun updateChallenge(challenge: Challenge) {
        val em = createEntityManager()
        em.transaction.begin()
        em.merge(challenge)
        em.transaction.commit()
    }

    override fun createChallenge(challenge: Challenge) {
        if(challenge.id != null) throw IllegalArgumentException("challenge already persisted")
        val em = createEntityManager()
        em.transaction.begin()
        em.persist(challenge)
        em.transaction.commit()
    }

    override fun markActiveChallengeAsDone() {
        val em = createEntityManager()
        em.transaction.begin()
        val query = em.createQuery("UPDATE Challenge SET active = false, solved = true WHERE active = true")
        query.executeUpdate()
        em.transaction.commit()
    }

    private fun createEntityManager(): EntityManager = entityMangerFactory.createEntityManager()

    override fun findCurrentChallenge(): Challenge? = findCurrentChallenge(createEntityManager())

    private fun findCurrentChallenge(em: EntityManager): Challenge? {
        val query = em.createQuery("SELECT c FROM Challenge c WHERE active = true", Challenge::class.java)
        return try {
            query.singleResult
        } catch (e: NoResultException) {
            null
        }
    }

    override fun activateNextChallenge(): Challenge? {
        val em = createEntityManager()
        val transaction = em.transaction
        transaction.begin()
        val updateQuery = em.createQuery("UPDATE Challenge SET active = true WHERE id = (SELECT min(c2.id) FROM Challenge c2 WHERE c2.active = false AND c2.solved = false) AND 1 NOT IN (SELECT 1 FROM Challenge c3 WHERE c3.active = true)")
        val updateCount = updateQuery.executeUpdate()
        if(updateCount == 0) return null
        transaction.commit()
        return findCurrentChallenge(em)
    }
}