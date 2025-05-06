package yjh.ontongsal.application.out

import yjh.ontongsal.domain.Todo

interface LoadTodoPort {
    fun findById(id: String): Todo?
    fun getTodo(id: String): Todo
    fun getAllTodo(): List<Todo>
}