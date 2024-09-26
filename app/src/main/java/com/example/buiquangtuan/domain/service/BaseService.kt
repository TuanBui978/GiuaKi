package com.example.buiquangtuan.domain.service

import com.example.buiquangtuan.core.InternetResult
import retrofit2.Response

open class BaseService {
    suspend fun <T : Any> call(call: suspend () -> Response<T> ): InternetResult<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        }
        catch (e: Exception) {
            return InternetResult.Failed(e)
        }
        return if (response.isSuccessful) {
            if (response.body() == null) {
                InternetResult.Failed(Exception("Response success but empty body"))
            } else {
                InternetResult.Success(response.body()!!)
            }
        } else {
            val error = response.errorBody().toString()
            InternetResult.Failed(Exception(error))
        }
    }
}