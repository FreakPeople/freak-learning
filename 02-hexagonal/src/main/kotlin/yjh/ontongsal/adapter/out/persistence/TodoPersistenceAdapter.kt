package yjh.ontongsal.adapter.out.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import yjh.ontongsal.application.out.LoadTodoPort
import yjh.ontongsal.application.out.SaveTodoPort
import yjh.ontongsal.domain.Todo

@Repository
class TodoPersistenceAdapter(
    private val todoJpaRepository: TodoJpaRepository,
    private val mapper: TodoMapper
) : SaveTodoPort, LoadTodoPort {

    override fun save(todo: Todo): Todo {
        val entity = mapper.mapToEntity(todo)
        val savedEntity = todoJpaRepository.save(entity)
        return mapper.mapToDomain(savedEntity)
    }

    override fun findById(id: String): Todo? {
        return todoJpaRepository.findByIdOrNull(id)
            ?.let { mapper.mapToDomain(it) }
    }

    /**
     * @throws NoSuchElementException Todo 엔티티를 주어진 id로 찾을 수 없을 때 발생.
     */
    override fun getTodo(id: String): Todo {
        return todoJpaRepository.findByIdOrNull(id)
            ?.let { mapper.mapToDomain(it) }
            ?: throw NoSuchElementException("Todo 엔티티를 찾을 수 없습니다. id: $id")
    }

    override fun getAllTodo(): List<Todo> {
        return todoJpaRepository.findAll()
            .map { mapper.mapToDomain(it) }
    }
} 