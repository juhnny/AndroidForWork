package com.example.ex004mvpretrofit.data

import android.content.Context
import org.json.JSONObject

class InfoLocalDataSource(context: Context) : InfoDataSource {

    val sharedPreferences by lazy { context.getSharedPreferences("info", Context.MODE_PRIVATE) }

    override fun getInfo(callback: InfoDataSource.LoadInfoCallback) {
        var info = sharedPreferences.getString("info", null)
        if(info != null){
            callback.onInfoResponse(JSONObject(info))
        } else {
            callback.onInfoError()
        }
    }

    override fun saveInfo(info: JSONObject) {
        sharedPreferences.edit().putString("info", info.toString()).commit()
    }

    override fun getRemoteInfo(
        title: String,
        message: String,
        callback: InfoDataSource.GetRemoteInfoCallback
    ) {
        // local data source 에서는 사용하지 않음
    }
}