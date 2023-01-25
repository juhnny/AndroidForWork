package com.example.mvp_my_first_mvp_example.data

import android.content.Context
import com.example.mvp_my_first_mvp_example.data.Source.InfoLocalDataSource
import org.json.JSONObject

class InfoRepository(context: Context) : InfoDataSource{

    private val infoLocalDataSource =
        InfoLocalDataSource(context)

    override fun getInfo(callback: InfoDataSource.LoadInfoCallback) {
        infoLocalDataSource.getInfo(callback)
    }

    override fun saveInfo(info: JSONObject) {
        infoLocalDataSource.saveInfo(info)
    }
}