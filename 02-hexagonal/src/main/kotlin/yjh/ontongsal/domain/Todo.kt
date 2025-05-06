package yjh.ontongsal.domain

import java.time.LocalDateTime
import yjh.ontongsal.application.`in`.command.UpdateTodoCommand

class Todo(
    val id: String = "",
    var title: String,
    var content: String,
    var isCompleted: Boolean = false,
    val createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var deletedAt: LocalDateTime? = null,
) {

    fun complete() {
        this.isCompleted = true
        this.updatedAt = LocalDateTime.now()
    }

    fun update(command: UpdateTodoCommand) {
        this.title = command.title
        this.content = command.content
        this.updatedAt = LocalDateTime.now()
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }
}