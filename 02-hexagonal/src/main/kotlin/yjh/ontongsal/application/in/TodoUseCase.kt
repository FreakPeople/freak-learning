package yjh.ontongsal.application.`in`

import yjh.ontongsal.application.`in`.command.CreateTodoCommand
import yjh.ontongsal.application.`in`.command.UpdateTodoCommand
import yjh.ontongsal.domain.Todo

interface TodoUseCase {
    fun createTodo(createTodoCommand: CreateTodoCommand): Todo
    fun updateTodo(updateTodoCommand: UpdateTodoCommand): Todo
    fun completeTodo(id: String): Todo
    fun deleteTodo(id: String)
    fun getTodo(id: String): Todo
    fun getAllTodos(): List<Todo>
} 