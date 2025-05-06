package yjh.ontongsal.global.exception

class BaseException(
    val errorCode: ErrorCode,
    cause: Throwable? = null
) : RuntimeException(errorCode.message, cause)