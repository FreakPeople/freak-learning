package yjh.ontongsal.adapter.out.persistence

import jakarta.persistence.*
import java.time.LocalDateTime
import org.hibernate.annotations.SQLRestriction


@Entity
@Table(name = "todos")
class TodoEntity(
    @Id
    val id: String = "",

    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "content", nullable = false)
    val content: String,

    @Column(name = "is_completed", nullable = false)
    val isCompleted: Boolean,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime,

    @Column(name = "deleted_at", nullable = true)
    val deletedAt: LocalDateTime?,
)