package com.example.mvp_my_first_mvp_example.data

import org.json.JSONObject

interface InfoDataSource {

    interface LoadInfoCallback {
        fun onInfoLoaded(info: JSONObject)
        fun onDataNotAvailable()
    }

    fun getInfo(callback: LoadInfoCallback)
    fun saveInfo(info: JSONObject)

}