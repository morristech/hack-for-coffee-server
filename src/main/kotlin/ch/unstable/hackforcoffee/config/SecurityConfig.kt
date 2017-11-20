package ch.unstable.hackforcoffee.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.stereotype.Component


@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    private val _challengeUrl = "/challenge/**"
    private val _challengeSolveUrl = "/challenge/*/solve"
    private val _challengeSkipUrl = "/challenge/*/skip"


    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, _challengeUrl)
                    .hasRole(Roles.CHALLENGE_CREATOR)
                .antMatchers(HttpMethod.GET, _challengeUrl)
                    .hasAnyRole(Roles.CHALLENGE_VIEWER, Roles.CHALLENGE_ADMIN)
                .antMatchers(HttpMethod.PUT, _challengeSolveUrl)
                    .hasRole(Roles.CHALLENGE_SOLVER)
                .antMatchers(HttpMethod.PUT, _challengeSkipUrl)
                    .hasRole(Roles.CHALLENGE_ADMIN)
                .anyRequest()
                    .denyAll()

    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder, properties: ScrpytProperty) {
        auth
                .jdbcAuthentication()
                .withDefaultSchema()
                .passwordEncoder(SCryptPasswordEncoder())
                .withUser("viewer").password("").roles(Roles.CHALLENGE_VIEWER)
    }
}