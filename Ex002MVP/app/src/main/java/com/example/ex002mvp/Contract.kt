package com.example.ex002mvp

import org.json.JSONObject

// MVP 패턴은 Model과 View를 완전히 분리 시키는데 초점을 맞춘 패턴이다.
// Model과 View 가 직접 주고 받는다면 뷰가 모델의 역활을 거의 흡수하게 된다.
//출처: https://forliver.tistory.com/7 [개발자 놀이터:티스토리]

/**
 * Contract는 View와 Presenter가 기능을 어떻게 분담할 지 미리 계획한 내용을 명세해주는 인터페이스
 *
 * 본 예제에서는 아래와 같은 기능들이 필요
 *
 * 1) EditText에 입력된 값을 DB에 저장하는 기능
 * 2) EditText에 입력된 값을 TextView로 보여주는 기능
 * 3) 화면 초기화 시, 저장된 값이 있다면 TextView로 보여주는 기능
 */
interface Contract {
    // Presenter에서 View한테 UI 작업을 시킬 때 사용하는 View의 기능
    interface View {
        //TextView에 info 데이터를 보여준다.
        fun showInfo(info: JSONObject)
    }

    // View에서 Presenter에게 이벤트 처리를 시킬 때 사용하는 Presenter의 기능
    interface Presenter{
        // 3) 화면 초기화 시, 저장된 데이터가 있는지 Model에서 확인하고, 있다면 TextView로 보여주는 기능
        fun initInfo()

        // 2) EditText에 입력된 값을 TextView로 보여주는 기능
        fun setInfo(info:JSONObject)

        // 1) EditText에 입력된 값을 DB에 저장하는 기능
        fun saveInfo(info:JSONObject)
        // Model에서는
        //{ "name" : "olaf", "email" : "olaf@naver.com" }
        // 이런 형태의 JSONObject 로 만들어서 관리하는 방법을 사용하려 함

    }
}