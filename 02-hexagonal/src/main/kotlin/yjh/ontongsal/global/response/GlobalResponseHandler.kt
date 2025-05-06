package yjh.ontongsal.global.response

import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import yjh.ontongsal.global.exception.BaseException
import yjh.ontongsal.global.exception.ErrorResponse

@RestControllerAdvice
class GlobalResponseHandler : ResponseBodyAdvice<Any> {

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return returnType.parameterType.isAssignableFrom(ResponseEntity::class.java)
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        return ApiResponse.success(body)
    }

    @ExceptionHandler(BaseException::class)
    fun handleException(e: BaseException): ApiResponse<ErrorResponse> {
        val errorCode = e.errorCode
        val errorResponse = ErrorResponse(
            code = errorCode.code,
            message = errorCode.message,
        )
        return ApiResponse.fail(errorResponse)
    }

    @ExceptionHandler(BindException::class)
    fun handleException(e: BindException): ApiResponse<ErrorResponse> {
        val errorResponse = ErrorResponse(
            code = HttpStatus.BAD_REQUEST.value(),
            message = e.bindingResult.allErrors.joinToString(", ")
        )
        return ApiResponse.fail(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ApiResponse<ErrorResponse> {
        val errorResponse = ErrorResponse(
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = e.message ?: "EMPTY ERROR MESSAGE",
        )
        return ApiResponse.fail(errorResponse)
    }
}