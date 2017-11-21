package ch.unstable.hackforcoffee.di

import ch.unstable.hackforcoffee.data.ChallengeRepository
import ch.unstable.hackforcoffee.data.ChallengeRepositoryImpl
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

@Module
class JpaModule {

    @Provides
    @Singleton
    fun provideEntityManagerFactory(): EntityManagerFactory =
            Persistence.createEntityManagerFactory( "ch.unstable.hackforcoffee.data")!!

    @Provides
    @Singleton
    fun provideChallengeRepository(entityManagerFactory: EntityManagerFactory): ChallengeRepository =
            ChallengeRepositoryImpl(entityManagerFactory)
}
