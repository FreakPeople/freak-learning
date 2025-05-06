package yjh.ontongsal.application.out

import yjh.ontongsal.domain.Todo

interface SaveTodoPort {
    fun save(todo: Todo): Todo
} 