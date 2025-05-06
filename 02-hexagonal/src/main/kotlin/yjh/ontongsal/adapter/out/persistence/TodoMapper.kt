package yjh.ontongsal.adapter.out.persistence

import org.springframework.stereotype.Component
import yjh.ontongsal.domain.Todo

@Component
class TodoMapper {

    fun mapToEntity(todo: Todo) = TodoEntity(
        id = todo.id,
        title = todo.title,
        content = todo.content,
        isCompleted = todo.isCompleted,
        createdAt = todo.createdAt,
        updatedAt = todo.updatedAt,
        deletedAt = todo.deletedAt,
    )

    fun mapToDomain(todoEntity: TodoEntity) = Todo(
        id = todoEntity.id,
        title = todoEntity.title,
        content = todoEntity.content,
        isCompleted = todoEntity.isCompleted,
        createdAt = todoEntity.createdAt,
        updatedAt = todoEntity.updatedAt,
        deletedAt = todoEntity.deletedAt,
    )
}
