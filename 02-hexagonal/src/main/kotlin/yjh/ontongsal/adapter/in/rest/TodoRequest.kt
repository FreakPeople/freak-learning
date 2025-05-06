package yjh.ontongsal.adapter.`in`.rest

import jakarta.validation.constraints.NotNull
import yjh.ontongsal.application.`in`.command.CreateTodoCommand
import yjh.ontongsal.application.`in`.command.UpdateTodoCommand

data class CreateTodoRequest(
    @NotNull(message = "제목은 필수입니다")
    val title: String,

    @NotNull(message = "내용은 필수입니다")
    val content: String
) {
    fun toCommand() = CreateTodoCommand(
        title = title,
        content = content
    )
}

data class UpdateTodoRequest(
    @NotNull(message = "제목은 필수입니다")
    val title: String,

    @NotNull(message = "내용은 필수입니다")
    val content: String,
) {
    fun toCommand(id: kotlin.String) = UpdateTodoCommand(
        id = id,
        title = title,
        content = content,
    )
}