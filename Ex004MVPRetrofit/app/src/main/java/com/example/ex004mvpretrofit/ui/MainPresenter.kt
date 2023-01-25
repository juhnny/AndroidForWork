package com.example.ex004mvpretrofit.ui

import android.util.Log
import com.example.ex004mvpretrofit.data.InfoDataSource
import com.example.ex004mvpretrofit.data.InfoRepository
import org.json.JSONObject

class MainPresenter(val view: MainContract.View, val repository: InfoRepository) : MainContract.Presenter {
    // 데이터를 뷰에 보여주기
    override fun setInfo(info: JSONObject) {
        view.showInfo(info)
    }

    override fun setRemoteInfo(title: String, message: String) {
        Log.e("Presenter", "$title, $message")
        repository.getRemoteInfo(title, message, object : InfoDataSource.GetRemoteInfoCallback{
            override fun onInfoResponse(string: String) {
                Log.e("Presenter", "string")
                view.showRemoteInfo(string)
            }

            override fun onInfoError() {
                Log.e("Presenter", "Error")
                // Nothing
            }

        })
    }

    override fun saveInfo(info: JSONObject) {
        repository.saveInfo(info)
    }

    override fun initInfo() {
        repository.getInfo(object : InfoDataSource.LoadInfoCallback{
            override fun onInfoResponse(info: JSONObject) {
                view.showInfo(info)
            }

            override fun onInfoError() {
                Log.e("LoadInfoCallback", "Error")
            }
        })
    }

}