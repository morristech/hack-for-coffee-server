package ch.unstable.hackforcoffeeserver

import ch.unstable.hackforcoffee.config.Config
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.ImportResource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ImportResource(locations = arrayOf("classpath:/repositories.xml"))
@Import(Config::class)
@EnableJpaRepositories
@ComponentScan("ch.unstable.hackforcoffee")
@EntityScan("ch.unstable.hackforcoffee.model")
class HackForCoffeeServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(HackForCoffeeServerApplication::class.java, *args)
}
