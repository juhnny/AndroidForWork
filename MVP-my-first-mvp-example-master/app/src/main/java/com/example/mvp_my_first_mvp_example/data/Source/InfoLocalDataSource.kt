package com.example.mvp_my_first_mvp_example.data.Source

import android.content.Context
import com.example.mvp_my_first_mvp_example.data.InfoDataSource
import org.json.JSONObject

class InfoLocalDataSource(context: Context) :
    InfoDataSource {

    private val sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    override fun getInfo(callback: InfoDataSource.LoadInfoCallback) {
        var info = sharedPreferences.getString("info", null)
        if(info != null) {
            callback.onInfoLoaded(JSONObject(info))
        }else {
            callback.onDataNotAvailable()
        }
    }

    override fun saveInfo(info: JSONObject) {
        editor.putString("info", info.toString())
        editor.commit()
    }
}