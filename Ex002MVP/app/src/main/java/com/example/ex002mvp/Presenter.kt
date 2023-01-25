package com.example.ex002mvp

import org.json.JSONObject

// 기능 명세서인 Contract.Presenter 인터페이스를 상속받아 Presenter 클래스를 만들 차례

// Presenter의 생성자에는 View와 Model이 들어가며, Contract에 정의했던 함수들을 작성해주면 된다.
// 생성자의 View, Model, 상속받는 Presenter 셋 다 인터페이스 타입이다.
// interface Presenter를 상속받는 건 그 기능을 구현하기 위함이고
// View와 Model의 인터페이스 타입을 파라미터로 받는 건 그 구현체들을 주입받아 사용하기 위함

// 그러고보니 여기서 M, V, P가 다 만나네! MVP의 중심은 Presenter구나.
class Presenter(val view: Contract.View, val repository: InfoRepository) : Contract.Presenter {

    // 3) 화면 초기화 시, 저장된 값이 있다면 TextView로 보여주는 기능
    override fun initInfo() {
        repository.getInfo(object : InfoDataSource.LoadInfoCallback{
            override fun onInfoLoaded(info: JSONObject) {
                view.showInfo(info)
            }

            override fun onDataNotAvailable() {
                // 아무것도 하지 않음
            }

        })
    }

    // 2) EditText에 입력된 값을 TextView로 보여주는 기능
    override fun setInfo(info: JSONObject) {
        view.showInfo(info)
    }
    // 근데 저 info 데이터는 View에서 바로 넣어준 것. 이렇게 Model을 거치지 않을 거라면 그냥 View 안에서 처리할 수 있지 않았을까?

    // 1) EditText에 입력된 값을 DB에 저장하는 기능
    override fun saveInfo(info: JSONObject) {
        repository.saveInfo(info)
    }

}