package com.example.mvp_my_first_mvp_example

import MainPresenter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvp_my_first_mvp_example.data.InfoRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), MainContract.View{

    private lateinit var presenter: MainPresenter
    private lateinit var repository: InfoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = InfoRepository(this)
        presenter = MainPresenter(this@MainActivity, repository)

        presenter.initInfo()
        initButtonListener()
    }

    override fun showInfo(info: JSONObject) {
        name_output.text = info.getString("name")
        email_output.text = info.getString("email")
    }

    fun initButtonListener() {
        button_submit.setOnClickListener {

            var info = JSONObject()
            info.put("name", name_input.text.toString())
            info.put("email", email_input.text.toString())

            name_input.text.clear()
            email_input.text.clear()

            presenter.setInfo(info)
            presenter.saveInfo(info)
        }
    }
}
