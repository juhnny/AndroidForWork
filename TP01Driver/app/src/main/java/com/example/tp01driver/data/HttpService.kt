package com.example.tp01driver.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HttpService {

    @POST("/pldsvc/driver/login")
    fun driverLogin(@Body params: Map<String, String?>?): Call<ResponseSingleData<UserVO>>

    @FormUrlEncoded
    @POST("/pldsvc/driver/login")
    fun driverLogin(@Field("userId") userId:String, @Field("password") password:String): Call<ResponseSingleData<UserVO>>
}