package ch.unstable.hackforcoffee.model

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id

@javax.persistence.Entity
data class Challenge(@field:GeneratedValue @field:Id
                     val id: Long? = null,
                     @field:Column(columnDefinition = "TEXT") val instructions: String,
                     val solution: String,
                     val solved: Boolean = false,
                     val active: Boolean = false)