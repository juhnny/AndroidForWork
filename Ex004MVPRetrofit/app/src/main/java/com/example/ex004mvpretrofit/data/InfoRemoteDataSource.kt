package com.example.ex004mvpretrofit.data

import android.util.Log
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class InfoRemoteDataSource : InfoDataSource {

    val httpService: HttpService

    init {
        var url = "http://iwibest.dothome.co.kr/03AndroidHttpRequest/"
        val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(ScalarsConverterFactory.create()).build()
        httpService = retrofit.create(HttpService::class.java)
        Log.e("InfoRemoteDataResponse", "URL: $url")
    }

    override fun getInfo(callback: InfoDataSource.LoadInfoCallback) {
        // Not required for the remote data source
    }

    override fun getRemoteInfo(
        title: String,
        message: String,
        callback: InfoDataSource.GetRemoteInfoCallback
    ) {
        Log.e("InfoRemoteDataSource", "getRemoteInfo()")
        val call = httpService.getMessage(title, message)
        // callback으로 뭘 써야 하는거야??
        call.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val str = response.body() ?: "NoData"
                callback.onInfoResponse(str)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("InfoRemoteDataSource Error", "${t.message}")
                callback.onInfoError()
            }

        })
    }

    override fun saveInfo(info: JSONObject) {
        // Not required for the remote data source
    }

}