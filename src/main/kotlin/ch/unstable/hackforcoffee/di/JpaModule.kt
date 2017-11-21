package ch.unstable.hackforcoffee.di

import ch.unstable.hackforcoffee.model.Challenge
import dagger.Module
import dagger.Provides
import org.hibernate.cfg.Configuration
import java.util.*
import javax.inject.Singleton
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

@Module
class JpaModule {

    internal fun c3p0Properties(): Properties {
        val props = Properties()
        props.put("hibernate.c3p0.min_size", 10)
        props.put("hibernate.c3p0.max_size", 50)
        props.put("hibernate.c3p0.timeout", 1800)
        props.put("hibernate.c3p0.max_statements", 10)
        return props
    }

    @Provides
    @Singleton
    fun provideHibernateConfiguration(): Configuration {
        return Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgres://localhost/test")
                .setProperty("hibernate.connection.username", "username")
                .setProperty("hibernate.connection.password", "username")
                .setProperty("cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider")
                .setProperty("show_sql", "true")
                .setProperty("hbm2ddl.auto", "update")
                .addProperties(c3p0Properties())
                .addAnnotatedClass(Challenge::class.java)
    }

    @Provides
    @Singleton
    fun provideEntityManagerFactory(): EntityManagerFactory =
            Persistence.createEntityManagerFactory( "ch.unstable.hackforcoffee.data")!!

}
