package ch.unstable.hackforcoffee.model

import javax.persistence.Column
import javax.persistence.Id

@javax.persistence.Entity
data class Challenge(@field:Id val id: Long,
                     @field:Column(columnDefinition = "TEXT") val instructions: String,
                     val solution: String,
                     val solved: Boolean)