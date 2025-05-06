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
class LogFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    private val log = KotlinLogging.logger {}

    private val LINE_BREAK_REGEX = Regex("[\r\n]+")

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val req = ContentCachingRequestWrapper(request)
        val res = ContentCachingResponseWrapper(response)

        filterChain.doFilter(req, res)

        // req 로그 출력
        val reqJson = String(req.contentAsByteArray)
        val compactJson = try {
            objectMapper.readTree(reqJson).toString()
        } catch (e: Exception) {
            reqJson // JSON 형식이 아니면 원본 그대로 출력
        }
        log.info { "[LogFilter] req : $compactJson" }

        // res 로그 출력
        val resJson = String(res.contentAsByteArray)
        log.info { "[LogFilter] res : $resJson" }

        res.copyBodyToResponse()
    }

}