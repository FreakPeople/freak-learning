package yjh.ontongsal.application

import java.time.LocalDateTime
import java.util.UUID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import yjh.ontongsal.application.`in`.TodoUseCase
import yjh.ontongsal.application.`in`.command.CreateTodoCommand
import yjh.ontongsal.application.`in`.command.UpdateTodoCommand
import yjh.ontongsal.application.out.LoadTodoPort
import yjh.ontongsal.application.out.SaveTodoPort
import yjh.ontongsal.domain.Todo

@Service
@Transactional(readOnly = true)
class TodoService(
    private val saveTodoPort: SaveTodoPort,
    private val loadTodoPort: LoadTodoPort,
) : TodoUseCase {

    @Transactional
    override fun createTodo(command: CreateTodoCommand): Todo {
        val now = LocalDateTime.now()
        val todo = Todo(
            id = UUID.randomUUID().toString(),
            title = command.title,
            content = command.content,
            createdAt = now,
            updatedAt = now,
        )
        return saveTodoPort.save(todo)
    }

    @Transactional
    override fun updateTodo(command: UpdateTodoCommand): Todo {
        val todo = getTodo(command.id)
        todo.update(command)
        return saveTodoPort.save(todo)
    }

    @Transactional
    override fun completeTodo(id: String): Todo {
        val todo = getTodo(id)
        todo.complete()
        return saveTodoPort.save(todo)
    }

    @Transactional
    override fun deleteTodo(id: String) {
        val todo = getTodo(id)
        todo.delete()
        saveTodoPort.save(todo)
    }

    @Transactional(readOnly = true)
    override fun getTodo(id: String): Todo {
        return loadTodoPort.getTodo(id)
    }

    @Transactional(readOnly = true)
    override fun getAllTodos(): List<Todo> {
        return loadTodoPort.getAllTodo()
    }
} 