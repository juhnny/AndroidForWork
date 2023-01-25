package com.example.tp01driver.data

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataInterface : BaseDataInterface() {

    companion object{
        var instance: DataInterface? = null
            private set
            get(){
                if(field == null){
                    synchronized(DataInterface::class.java){
                        if(field == null){
                            field = DataInterface()
                        }
                    }
                }
                return field
            }
    }

    // 왜 String?이지?
    fun driverLogin(params: Map<String, String?>?, callback: ResponseCallback<ResponseSingleData<UserVO>>){
        Log.e("DataInterface driverLogin()", "")
        httpService.driverLogin(params).enqueue(object : Callback<ResponseSingleData<UserVO>>{
            override fun onResponse(call: Call<ResponseSingleData<UserVO>>, response: Response<ResponseSingleData<UserVO>>) {
                Log.e("DataInterface driverLogin()", "onResponse(), ${call.request().url().toString()}")
                var responseBody = response.body()
                Log.e("DataInterface driverLogin()", "$responseBody")
                if(responseBody == null){
                    Log.e("DataInterface driverLogin()", "noDataError")
                    //noDataErrorDialog
                } else {
                    if(responseBody.head?.code == 200){
                        callback.onSuccess(responseBody)
                    } else {
                        callback.onError("http error")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSingleData<UserVO>>, t: Throwable) {
                Log.e("DataInterface driverLogin()", "onFailure()")
                callback.onFailure(t)
            }

        })
    }

    fun driverLogin(userId:String, password:String, callback: ResponseCallback<ResponseSingleData<UserVO>>){
        Log.e("DataInterface driverLogin(userId, password)", "")
        httpService.driverLogin(userId, password).enqueue(object : Callback<ResponseSingleData<UserVO>>{
            override fun onResponse(call: Call<ResponseSingleData<UserVO>>, response: Response<ResponseSingleData<UserVO>>) {
                Log.e("DataInterface driverLogin()", "onResponse(), ${call.request().url().toString()}")
                var responseBody = response.body()
                Log.e("DataInterface driverLogin()", "$responseBody")
                if(responseBody == null){
                    Log.e("DataInterface driverLogin()", "noDataError")
                    //noDataErrorDialog
                } else {
                    if(responseBody.head?.code == 200){
                        callback.onSuccess(responseBody)
                    } else {
                        callback.onError("http error")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSingleData<UserVO>>, t: Throwable) {
                Log.e("DataInterface driverLogin()", "onFailure()")
                callback.onFailure(t)
            }

        })
    }


}