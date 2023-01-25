package com.example.ex002mvp

import android.content.Context
import org.json.JSONObject

// Local 데이터 소스와 관련된 로직을 여기서 구현
class InfoLocalDataSource(context : Context) : InfoDataSource {

    private val sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE)

    override fun getInfo(callback: InfoDataSource.LoadInfoCallback) {
        // 데이터 소스에서 데이터 가져오기
        var info = sharedPreferences.getString("info", null)
        // 데이터 로드가 성공적이면...
        if(info != null){
            callback.onInfoLoaded(JSONObject(info))
        } else { // 데이터 로드가 성공적이지 못했으면
            callback.onDataNotAvailable()
        }
    }

    override fun saveInfo(info: JSONObject) {
        sharedPreferences.edit()
            .putString("info", info.toString())
            .commit()
    }


}