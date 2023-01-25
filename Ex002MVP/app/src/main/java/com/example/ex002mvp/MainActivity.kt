package com.example.ex002mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.json.JSONObject

// View를 구현하는 방법은 간단하다.
// Contract.View 인터페이스를 Activity에 implement하여 View 관련 함수들을 작성해주면 된다.
// 그 다음 onCreate에서 버튼에 리스너를 달아주거나 Presenter를 통해 초기화 시켜주는 코드를 작성한다.
class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var repository: InfoRepository
    private lateinit var presenter: Contract.Presenter

    val nameInput by lazy { findViewById<EditText>(R.id.name_input) }
    val emailInput by lazy { findViewById<EditText>(R.id.email_input) }
    val btnSubmit by lazy { findViewById<Button>(R.id.button_submit) }
    val nameOutput by lazy { findViewById<TextView>(R.id.name_output) }
    val emailOutput by lazy { findViewById<TextView>(R.id.email_output) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = InfoRepository(this)
        presenter = Presenter(this, repository)

        presenter.initInfo()
        initButtonListener()

        // Model이란 데이터 + 그 데이터를 저장하고, 가져오고 관리하는 로직을 포함한 것
        // Google에서는 이 Model에 Repository 패턴을 적용하고 있음

        // 그림 model.png를 참고하자.
        // Repository 는 loacl 과 remote 중 어느 곳의 Data Source 를 사용할지를 판단하고 접근하는 곳이다.
        // 만약 local 또는 remote 데이터에 대한 캐싱이 필요하다면, 캐시 처리 또한 Repository 에서 진행한다.
        // Local Data Source 는 말 그대로 안드로이드 기기에 저장된 데이터를 불러온다. Remote Data Source 는 서버를 통해 데이터를 불러온다.

        // Repository 패턴이란 데이터 소스의 액세스에 필요한 논리를 캡슐화하여 데이터 소스와 application을 분리하는 것
        // 데이터 소스가 local이든 remote이든 클라이언트에서는 다 알 필요가 없다.
        // 덕분에 다양한 소스의 데이터를 추가/수정하기 쉬워진다.

        // Model과 Presenter의 역할 분담이 나뉘는 지점은?
        // Model에서는 받아온 데이터를 가공해 callback 객체의 메소드로 넘겨주는 것까지 관여
        // callback.xxx(info)
        // "나는 데이터 받아와서, 다시 가공해서, 콜백 객체의 추상 메소드들에 다 넣어줬다. 어떻게 써먹을지 구현은 Presenter 니가 해"
        // Presenter에서 callback object를 만들면서(익명 객체) 각 추상 메소드들을 구현

    }

    override fun showInfo(info: JSONObject) {
        // Contract.View 인터페이스의 추상 메소드 구현
        nameOutput.text = info.getString("name")
        emailOutput.text = info.getString("email")
    }

    fun initButtonListener(){
        btnSubmit.setOnClickListener {
            var info = JSONObject()
            info.put("name", nameInput.text.toString())
            info.put("email", emailInput.text.toString())

            nameInput.text.clear()
            emailInput.text.clear()

            presenter.setInfo(info) //TextView에 보여주기
            presenter.saveInfo(info) //로컬 데이터 소스에 데이터 저장
        }
    }
}