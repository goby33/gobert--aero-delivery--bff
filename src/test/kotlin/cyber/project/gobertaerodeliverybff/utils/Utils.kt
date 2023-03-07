package cyber.project.gobertaerodeliverybff.utils

import io.mockk.every
import io.mockk.mockk
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Call

fun <T> aSuccessfulCallWith(value: T?): Call<T> {
    val call = mockk<Call<T>>()
    every { call.execute() } returns retrofit2.Response.success(value)
    return call
}


inline fun <reified T> aNotFoundCall(): Call<T> {
    val call = mockk<Call<T>>()
    every { call.execute() } returns retrofit2.Response.error(
        404,
        "".toResponseBody("application/json".toMediaTypeOrNull())
    )
    return call
}

inline fun <reified T> aFailureCallWith(): Call<T> {
    val call = mockk<Call<T>>()
    every { call.execute() } returns retrofit2.Response.error(
        500,
        "".toResponseBody("application/json".toMediaTypeOrNull())
    )
    return call
}