package com.example.ex002mvp

import org.json.JSONObject

interface InfoDataSource {

    interface LoadInfoCallback {
        fun onInfoLoaded(info : JSONObject)

        fun onDataNotAvailable()
    }

    // Retrofit이라면 이 두 함수(데이터의 읽기/쓰기)는 RetrofitService 인터페이스의 GET/POST 함수가 대신하게 될 것
    fun getInfo(callback: LoadInfoCallback)
    fun saveInfo(info: JSONObject)
}