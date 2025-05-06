package yjh.ontongsal.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TodoJpaRepository : JpaRepository<TodoEntity, String> {

    @Query(
        """
        SELECT t 
        FROM TodoEntity t
        WHERE t.id = :id 
        AND t.deletedAt IS NULL
        """
    )
    fun findByIdOrNull(@Param("id") id: String): TodoEntity?

    @Query(
        """
        SELECT t 
        FROM TodoEntity t 
        WHERE t.deletedAt IS NULL
        """
    )
    override fun findAll(): List<TodoEntity>
}