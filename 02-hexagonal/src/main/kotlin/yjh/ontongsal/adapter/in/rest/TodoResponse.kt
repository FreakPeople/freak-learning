package yjh.ontongsal.adapter.`in`.restø

import java.time.LocalDateTime
import yjh.ontongsal.domain.Todo

data class TodoResponse(
    val id: String,
    val title: String,
    val content: String,
    val isCompleted: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(todo: Todo) = TodoResponse(
            id = todo.id,
            title = todo.title,
            content = todo.content,
            isCompleted = todo.isCompleted,
            createdAt = todo.createdAt,
            updatedAt = todo.updatedAt,
        )
    }
}

data class TodoListResponse(
    val total: Int,
    val result: List<TodoResponse>,
)