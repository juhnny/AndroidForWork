package com.example.tp01driver.ui

import android.content.Context
import android.widget.Toast
import com.example.tp01driver.common.BasePresenter
import com.example.tp01driver.data.BaseDataInterface
import com.example.tp01driver.data.DataInterface
import com.example.tp01driver.data.ResponseSingleData
import com.example.tp01driver.data.UserVO

class LoginPresenter(val view:LoginContract.View): BasePresenter(), LoginContract.Presenter {

    override fun driverLogin(context: Context, params: Map<String, String?>?) {
        Toast.makeText(context, "로그인 시도 중", Toast.LENGTH_SHORT).show()
        DataInterface.instance?.driverLogin(params, object : BaseDataInterface.ResponseCallback<ResponseSingleData<UserVO>>{
            // Repo에서 Presenter에게 데이터를 전달해오는 경우들
            override fun onSuccess(responseBody: ResponseSingleData<UserVO>) {
                view.loginSuccess(responseBody.body)
            }

            override fun onError(message: String?) {
                view.loginFail(message)
            }

            override fun onFailure(t: Throwable?) {
                view.loginFail(t?.message)
            }

        })
    }

    override fun driverLogin(context: Context, userId: String, password: String) {
        Toast.makeText(context, "로그인 시도 중", Toast.LENGTH_SHORT).show()
        DataInterface.instance?.driverLogin(userId, password, object : BaseDataInterface.ResponseCallback<ResponseSingleData<UserVO>>{
            // Repo에서 Presenter에게 데이터를 전달해오는 경우들
            override fun onSuccess(responseBody: ResponseSingleData<UserVO>) {
                view.loginSuccess(responseBody.body)
            }

            override fun onError(message: String?) {
                view.loginFail(message)
            }

            override fun onFailure(t: Throwable?) {
                view.loginFail(t?.message)
            }

        })
    }

}