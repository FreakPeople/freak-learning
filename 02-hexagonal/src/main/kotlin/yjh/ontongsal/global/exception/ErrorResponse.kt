package yjh.ontongsal.global.exception

import java.time.LocalDateTime

data class ErrorResponse(
    val code: Int,
    val message: String,
    val time: LocalDateTime = LocalDateTime.now(),
)