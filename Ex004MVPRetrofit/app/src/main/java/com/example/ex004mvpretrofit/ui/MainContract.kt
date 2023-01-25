package com.example.ex004mvpretrofit.ui

import org.json.JSONObject

interface MainContract {

    interface View {
        fun showInfo(info: JSONObject)
        fun showRemoteInfo(string: String)
    }

    interface Presenter {
        fun setInfo(info: JSONObject)

        fun setRemoteInfo(title: String, message: String)

        fun saveInfo(info: JSONObject)

        fun initInfo()
    }
}