package ch.unstable.hackforcoffee.model

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.security.core.userdetails.User
import java.util.*
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@javax.persistence.Entity
data class Challenge(@field:Id val id: Long,
                     @field:Column(columnDefinition = "TEXT") val instructions: String,
                     val solution: String,
                     val solved: Boolean,
                     @field:CreatedBy val user: User,
                     @field:LastModifiedBy val modifiedBy: User,
                     @field:CreatedDate val createdDate: Date,
                     @field:LastModifiedDate val lastModifiedDate: Date)