package cyber.project.gobertaerodeliverybff.core.extensions

import cyber.project.gobertaerodeliverybff.core.exceptions.*
import reactor.core.publisher.Mono
import retrofit2.Call

inline fun <reified T> Call<T>.performRequest(): T? {
    val response = execute()
    when {
        response.isSuccessful -> return response.body()
        response.code() == 400 -> throw IllegalArgumentException()
        response.code() == 404 -> throw ResourceNotFoundException("Unable to find resource")
        response.code() == 409 -> throw ConflictException("Conflict occurred")
        response.code() == 500 -> throw ApiException(response.message())
        response.code() == 502 -> throw BadGatewayException(response.message())
    }
    throw ApiException(response.errorBody()?.string() ?: "")

}

inline fun <reified T> Call<T>.performRequestToMono(): Mono<T> = Mono.just(this)
    .map { it.execute() }
    .flatMap { response ->
        when {
            response.isSuccessful -> response.body()?.let { body -> Mono.just(body) } ?: Mono.empty()
            response.code() == 400 -> Mono.error(IllegalArgumentException())
            response.code() == 404 -> Mono.error(ResourceNotFoundException("Unable to find resource"))
            response.code() == 409 -> Mono.error(ConflictException("Conflict occurred"))
            response.code() == 500 -> Mono.error(ApiException(response.message()))
            response.code() == 502 -> Mono.error(BadGatewayException(response.message()))
            response.code() == 503 -> Mono.error(ServiceUnavailableException(response.message()))
            else -> Mono.error(ApiException(response.errorBody()?.string() ?: ""))
        }
    }
