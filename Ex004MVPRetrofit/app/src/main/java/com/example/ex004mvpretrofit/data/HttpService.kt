package com.example.ex004mvpretrofit.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HttpService {

    //@Query - 서버에서 인식할 식별자(GET 방식으로 날아갈 Key값)
    @GET("getResponse.php")
    fun getMessage(@Query("title") title:String,
                   @Query("msg") message:String) : Call<String>
}