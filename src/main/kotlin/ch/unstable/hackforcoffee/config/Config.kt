package ch.unstable.hackforcoffee.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@Configuration
@EnableJpaAuditing
@Import(SecurityConfig::class)
internal class Config
