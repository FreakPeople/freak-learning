package yjh.ontongsal.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
class HttpLogFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    private val log = KotlinLogging.logger {}

    private val LINE_BREAK_REGEX = Regex("[\r\n]+")

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // 요청 정보 캐싱
        val req = ContentCachingRequestWrapper(request)
        val res = ContentCachingResponseWrapper(response)
        filterChain.doFilter(req, res)

        // param 정보 추출
        val queryParams = request.method
            .takeIf { it == "GET" }
            ?.let { getQueryParamsInfo(request) }
            ?: "N/A"

        // req body 정보 추출
        var reqJson = String(req.contentAsByteArray)
            .takeIf { it.isNotBlank() }
            ?: "N/A"
        val compactReqJson = try {
            objectMapper.readTree(reqJson).toString()
        } catch (e: Exception) {
            reqJson // JSON 형식이 아니면 원본 그대로 출력
        }

        // req body 정보 추출
        var resJson = String(res.contentAsByteArray)
            .takeIf { it.isNotBlank() }
            ?: "N/A"

        // request & response info 로그 출력
        log.info { "[LogFilter] ${request.method} ${request.requestURI}" }
        log.info { "[LogFilter] request body : $compactReqJson, params: $queryParams" }
        log.info { "[LogFilter] response body : $resJson" }

        res.copyBodyToResponse()
    }

    private fun getQueryParamsInfo(request: HttpServletRequest): String {
        return request.parameterMap
            .map { (key, values) -> "$key=${values.joinToString(",")}" }
            .joinToString(", ")
            .takeIf { it.isNotEmpty() }
            ?: "N/A"
    }
}