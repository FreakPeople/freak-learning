package yjh.ontongsal.application.`in`.command

data class CreateTodoCommand(
    val title: String,
    val content: String
)

data class UpdateTodoCommand(
    val id: String,
    val title: String,
    val content: String
) 