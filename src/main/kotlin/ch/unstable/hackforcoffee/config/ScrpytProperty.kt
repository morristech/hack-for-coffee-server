package ch.unstable.hackforcoffee.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties("hack-for-coffee.authentication.scrypt",
        ignoreInvalidFields = false,
        ignoreUnknownFields = false)
@Component
class ScrpytProperty {
    var cpuCost: Int = 16384
    var memoryCost: Int = 8
    var parallelization: Int = 1
    var keyLength: Int = 32
    var saltLength: Int = 64
}
