package com.example.studentapplication.data.remote

import com.example.studentapplication.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): State<T> {
    return withContext(Dispatchers.IO) {
        try {
            State.Loading
            State.Success(apiCall.invoke())

        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    State.Failure("حدث خطأ تأكد من اتصالك بالانترنت !")
                }

                is IOException -> {
                    State.Failure("حدث خطأ غير متوقع يرجي المحاولة لاحقا ")
                }

                else -> {
                    State.Failure("حدث خطأ غير متوقع يرجي المحاولة لاحقا")
                }
            }
        }
    }
}