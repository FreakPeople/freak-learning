package yjh.ontongsal.global.exception

enum class ErrorCode(
    val code: Int,
    val message: String,
) {
    NOT_FOUND_TODO(1001, "TODO 를 찾을 수 없습니다.")
}