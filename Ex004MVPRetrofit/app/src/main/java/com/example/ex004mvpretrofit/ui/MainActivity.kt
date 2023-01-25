package com.example.ex004mvpretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ex004mvpretrofit.data.InfoLocalDataSource
import com.example.ex004mvpretrofit.data.InfoRemoteDataSource
import com.example.ex004mvpretrofit.data.InfoRepository
import com.example.ex004mvpretrofit.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var repository: InfoRepository
    lateinit var presenter: MainPresenter

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.e("onCreate", "")

        repository = InfoRepository(InfoLocalDataSource(this), InfoRemoteDataSource())
        presenter = MainPresenter(this, repository)

        presenter.initInfo()

        binding.buttonSubmit.setOnClickListener {
            var info = JSONObject()
            info.put("name", binding.nameInput.text.toString())
            info.put("email", binding.emailInput.text.toString())

            presenter.setInfo(info)
            presenter.saveInfo(info)

            binding.nameInput.text.clear()
            binding.emailInput.text.clear()
        }

        binding.buttonGetResponse.setOnClickListener {
            Log.e("MainAct", "Btn!")
            var title = binding.nameInput.text.toString()
            var message = binding.emailInput.text.toString()
            presenter.setRemoteInfo(title, message)
        }
    }

    override fun showInfo(info: JSONObject) {
        binding.nameOutput.text = info.getString("name")
        binding.emailOutput.text = info.getString("email")
    }

    override fun showRemoteInfo(string: String) {
        binding.nameOutput.text = string
    }


}