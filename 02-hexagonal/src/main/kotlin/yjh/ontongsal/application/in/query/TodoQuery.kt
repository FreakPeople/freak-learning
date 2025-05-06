package yjh.ontongsal.application.`in`.query


data class TodoQuery(
    val page: Int = 0,
    val size: Int = 10,
    val sortBy: String = "createdAt",
    val direction: String = "DESC",
    val isCompleted: Boolean? = null,
)