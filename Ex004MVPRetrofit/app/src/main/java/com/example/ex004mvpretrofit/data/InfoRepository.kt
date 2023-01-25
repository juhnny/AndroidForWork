package com.example.ex004mvpretrofit.data

import android.content.Context
import org.json.JSONObject

class InfoRepository(val infoLocalDataSource: InfoLocalDataSource,
                     val infoRemoteDataSource: InfoRemoteDataSource): InfoDataSource {

    //static처럼 InfoRepository.INSTANCE.xxx 이렇게 쓰고 싶은건데...
//    companion object{
//        var INSTANCE: InfoRepository? = null
//            get() {
//                if(field == null){
//                    synchronized(InfoRepository::class.java){
//                        if(field == null){
//                            field = InfoRepository()
//                        }
//                    }
//                }
//                return field
//            }
//            private set
//    }

    override fun getInfo(callback: InfoDataSource.LoadInfoCallback) {
        infoLocalDataSource.getInfo(callback)
    }

    override fun saveInfo(info: JSONObject) {
        infoLocalDataSource.saveInfo(info)
    }

    override fun getRemoteInfo(
        title: String,
        message: String,
        callback: InfoDataSource.GetRemoteInfoCallback
    ) {
        infoRemoteDataSource.getRemoteInfo(title, message, callback)
    }


}