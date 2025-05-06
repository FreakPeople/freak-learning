package yjh.ontongsal.adapter.`in`.rest

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import yjh.ontongsal.adapter.`in`.restø.TodoResponse
import yjh.ontongsal.application.`in`.TodoUseCase

@RestController
@RequestMapping("/api/v1/todos")
class TodoController(
    private val todoUseCase: TodoUseCase,
) {

    @PostMapping
    fun createTodo(
        @RequestBody @Valid request: CreateTodoRequest
    ): ResponseEntity<TodoResponse> {
        val createCommand = request.toCommand()
        val todo = todoUseCase.createTodo(createCommand)
        return ResponseEntity.ok(TodoResponse.from(todo))
    }

    @PutMapping("/{id}")
    fun updateTodo(
        @PathVariable id: String,
        @RequestBody @Valid request: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        val updateCommand = request.toCommand(id)
        val todo = todoUseCase.updateTodo(updateCommand)
        return ResponseEntity.ok(TodoResponse.from(todo))
    }

    @PutMapping("/{id}/complete")
    fun completeTodo(
        @PathVariable id: String
    ): ResponseEntity<TodoResponse> {
        val todo = todoUseCase.completeTodo(id)
        return ResponseEntity.ok(TodoResponse.from(todo))
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(
        @PathVariable id: String
    ): ResponseEntity<Unit> {
        todoUseCase.deleteTodo(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    fun getTodo(
        @PathVariable id: String
    ): ResponseEntity<TodoResponse> {
        val todo = todoUseCase.getTodo(id)
        return ResponseEntity.ok(TodoResponse.from(todo))
    }

    @GetMapping
    fun getAllTodos(): ResponseEntity<List<TodoResponse>> {
        val todos = todoUseCase.getAllTodos()
        return ResponseEntity.ok(todos.map { TodoResponse.from(it) })
    }
}