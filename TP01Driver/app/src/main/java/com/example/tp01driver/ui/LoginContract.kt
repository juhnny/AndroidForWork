package com.example.tp01driver.ui

import android.content.Context
import com.example.tp01driver.data.UserVO

interface LoginContract {
    interface View{
        /**
         * 로그인 API 응답 성공 함수 정의
         */
        fun loginSuccess(userInfo: UserVO?)

        /**
         * 로그인 API 응답 실패 함수 정의
         */
        fun loginFail(message: String?)
    }

    interface Presenter {
        /**
         * 로그인 API 호출 함수 정의
         */
        fun driverLogin(context: Context, params: Map<String, String?>?)
        fun driverLogin(context: Context, userId: String, password: String)
    }
}