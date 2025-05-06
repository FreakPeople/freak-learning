package yjh.ontongsal.global.response

data class ApiResponse<T>(
    val result: String,
    val data: T
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> = ApiResponse("SUCCESS", data)
        fun <T> fail(data: T): ApiResponse<T> = ApiResponse("FAILURE", data)
    }
}