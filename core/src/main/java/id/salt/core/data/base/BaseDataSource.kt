package id.salt.core.data.base

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import id.salt.core.data.remote.response.ErrorResponse
import id.salt.core.other.Resource
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getDataResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            val body = response.body()
            if (response.isSuccessful) {
                if (body != null) return Resource.success(body)
            }
            val errorBody = convertErrorBody(response.errorBody())
            if (errorBody != null) {
                return error(errorBody.message, false, response.code())
            }
            return error(response.message(), false, response.code())
        } catch (e: IOException) {
            e.printStackTrace()
            e.message?.let { msg ->
                if (msg.contains("unauthorized", true) ||
                    msg.contains("Too many follow-up requests: 21", true)
                )
                    return error(e.message ?: e.toString(), false, 401)
            }
            return error(e.message ?: e.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(
        message: String,
        networkError: Boolean = true,
        errorCode: Int = 1
    ): Resource<T> {
        return Resource.error(
            if (networkError) "Network call has failed for a following reason: $message"
            else message,
            errorCode
        )
    }

    private fun convertErrorBody(responseBody: ResponseBody?): ErrorResponse? {
        return try {
            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)

            responseBody?.let {
                jsonAdapter.fromJson(it.string())
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

}