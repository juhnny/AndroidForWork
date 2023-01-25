package com.example.tp01driver.data

import com.example.tp01driver.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

open class BaseDataInterface {

    interface ResponseCallback<T>{
        fun onSuccess(responseBody: T)
        fun onError(message: String?)
        fun onFailure(t: Throwable?)
    }

    val httpService: HttpService

    init {
        val retrofit = Retrofit.Builder().baseUrl(Constants.baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        httpService = retrofit.create(HttpService::class.java)
    }

}