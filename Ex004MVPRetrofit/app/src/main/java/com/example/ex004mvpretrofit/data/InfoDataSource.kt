package com.example.ex004mvpretrofit.data

import org.json.JSONObject

interface InfoDataSource {

    interface LoadInfoCallback{
        fun onInfoResponse(info: JSONObject)
        fun onInfoError()
    }

    interface GetRemoteInfoCallback{
        fun onInfoResponse(string: String)
        fun onInfoError()
    }

    // local data source에서 사용할 메소드
    fun getInfo(callback: LoadInfoCallback)
    fun saveInfo(info: JSONObject)
    // remote data source에서 사용할 메소드
    fun getRemoteInfo(title: String, message: String, callback: GetRemoteInfoCallback)
    // 메소드 하나를 local, remote에서 동시에 사용해도 상관없음
}