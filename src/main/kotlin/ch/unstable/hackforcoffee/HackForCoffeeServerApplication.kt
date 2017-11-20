package ch.unstable.hackforcoffeeserver

import ch.unstable.hackforcoffee.config.Config
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.ImportResource

@SpringBootApplication
@ImportResource(locations = arrayOf("classpath:/spring.xml"))
@Import(Config::class)
class HackForCoffeeServerApplication

fun main(args: Array<String>) {
    runApplication<HackForCoffeeServerApplication>(*args)
}
