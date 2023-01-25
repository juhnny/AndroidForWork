package com.example.ex002mvp

import android.content.Context
import org.json.JSONObject

// Repository 는 보통은 object class 를 사용하여 싱글톤으로 생성한다. 여기서는 싱글톤으로 만들지 않고 Repository 를 작성했다.
// Repository 또한 위에서 정의했던 DataSource 인터페이스를 implement 하여 구현해준다.

// Repository 생성자를 통해 Local/Remote DataSource 를 생성하여 사용할 수도 있다.
class InfoRepository(context: Context) : InfoDataSource {

    private val infoLocalDataSource = InfoLocalDataSource(context)

    override fun getInfo(callback: InfoDataSource.LoadInfoCallback) {
        infoLocalDataSource.getInfo(callback)
    }

    override fun saveInfo(info: JSONObject) {
        infoLocalDataSource.saveInfo(info)
    }

}

// 이렇게 DataSource 인터페이스와, Local DataSource, Repository 를 작성하여 MVP 의 Model 을 만들어보았다.
// 이제 만들어진 Model 을 토대로 Presenter 와 View 를 만들고, 이들을 연결하여 사용하면 된다.